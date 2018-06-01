package com.yonatanbetzer.imagesearch.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.yonatanbetzer.imagesearch.application.ImageSearchApplication;
import com.yonatanbetzer.imagesearch.controls.GifView;
import com.yonatanbetzer.imagesearch.server.AsyncImageSearchResultResponseHandler;
import com.yonatanbetzer.imagesearch.data_objects.ImageResult;
import com.yonatanbetzer.imagesearch.adapters.ImageSearchAdapter;
import com.yonatanbetzer.imagesearch.R;
import com.yonatanbetzer.imagesearch.server.PixabayAPI;
import com.yonatanbetzer.imagesearch.utils.Constants;

import java.util.ArrayList;

public class ImageSearchActivity extends Activity {
    private SearchView searchView;
    private String query;
    private RecyclerView imageGrid;
    private ViewGroup noResultsContainer;
    private ImageSearchAdapter imageGridAdapter;
    public ArrayList<ImageResult> results = new ArrayList<>();
    private ProgressBar progressBar;
    private View loadingMore;

    private int page = 1;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        progressBar = findViewById(R.id.progress_bar);
        GifView noResultsGif = findViewById(R.id.no_results);
        noResultsGif.setImageResource(R.drawable.vincent_vega);
        noResultsContainer = findViewById(R.id.no_results_container);
        loadingMore = findViewById(R.id.loading_more);
        imageGrid = findViewById(R.id.image_grid);
        imageGrid.setHasFixedSize(true);

        TextView loadingMoreTextView = findViewById(R.id.loading_more_text);
        loadingMoreTextView.setTypeface(Constants.openSansRegularHebrew);

        final FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        imageGrid.setLayoutManager(layoutManager);
        imageGridAdapter = new ImageSearchAdapter(results);
        imageGrid.setAdapter(imageGridAdapter);

        imageGrid.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int dataItemCount = layoutManager.getItemCount();
                int viewsPerPage = layoutManager.getChildCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((viewsPerPage + firstVisibleItemPosition) >= dataItemCount - 10
                            && firstVisibleItemPosition >= 0
                            && dataItemCount >= Constants.RESULTS_PER_PAGE) {
                        page++;
                        showLoadingMore();
                        search();
                    }
                }
            }
        });

        handleIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageSearchApplication.setCurrentActivity(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        createSearchView(menu);
        return true;
    }

    private void handleIntent(Intent intent) {
        if (intent != null && Intent.ACTION_SEARCH.equals(intent.getAction())) {
            resetSearchQuery();
            query = intent.getStringExtra(SearchManager.QUERY);
            search();
        }
    }

    private void showLoadingMore() {
        if(loadingMore != null) {
            loadingMore.setVisibility(View.VISIBLE);
        }
    }

    private void hideLoadingMore() {
        if(loadingMore != null) {
            loadingMore.setVisibility(View.GONE);
        }
    }

    private void createSearchView(Menu menu) {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(
                searchManager != null ? searchManager.getSearchableInfo(getComponentName()) : null);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setSubmitButtonEnabled(true);

        int searchTextId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = searchView.findViewById(searchTextId);
        if(searchText != null) {
            searchText.setTypeface(Constants.openSansRegularHebrew);
        }
        if(query != null) {
            searchView.setQuery(query, false);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String queryParam) {
                resetSearchQuery();
                query = queryParam;
                search();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        int goButtonId = searchView.getContext().getResources().getIdentifier("android:id/search_go_btn", null, null);
        ImageView goButton = searchView.findViewById(goButtonId);
        goButton.setImageResource(R.drawable.go_button);
    }

    private void resetSearchQuery() {
        isLastPage = false;
        page = 1;
        results.clear();
        imageGridAdapter.notifyDataSetChanged();
    }

    private void removeFocusFromSearchView() {
        if(searchView != null && searchView.hasFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm != null) {
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
            }
        }
    }

    private void search() {
        if(query != null && query.length() > 0) {
            if(page == 1) {
                progressBar.setVisibility(View.VISIBLE);
            }
            isLoading = true;
            removeFocusFromSearchView();
            PixabayAPI.search(query, page, Constants.RESULTS_PER_PAGE, new AsyncImageSearchResultResponseHandler() {
                @Override
                public void onSuccess(ArrayList<ImageResult> responseBody) {
                    results.addAll(responseBody);
                    if(responseBody.size() < Constants.RESULTS_PER_PAGE) {
                        isLastPage = true;
                    }
                    imageGridAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                    hideLoadingMore();
                    if(responseBody.size() == 0 && page == 1) {
                        imageGrid.setVisibility(View.GONE);
                        noResultsContainer.setVisibility(View.VISIBLE);
                    } else {
                        imageGrid.setVisibility(View.VISIBLE);
                        noResultsContainer.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(String error, int errorCode) {
                    progressBar.setVisibility(View.GONE);
                    isLastPage = true;
                    isLoading = false;
                    hideLoadingMore();
                    if(page == 1) {
                        imageGrid.setVisibility(View.GONE);
                        noResultsContainer.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
