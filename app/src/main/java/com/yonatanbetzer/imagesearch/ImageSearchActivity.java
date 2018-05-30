package com.yonatanbetzer.imagesearch;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

public class ImageSearchActivity extends Activity {

    private SearchView searchView;
    private String query;

    RecyclerView imageGrid;
    ImageSearchAdapter imageGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        imageGrid = findViewById(R.id.image_grid);
        imageGrid.setHasFixedSize(true);
        ArrayList<ImageResult> results = new ArrayList<>();
        ImageResult item = new ImageResult();
        item.setImageUrl("https://o.aolcdn.com/images/dims3/GLOB/crop/2133x1067+0+245/resize/630x315!/format/jpg/quality/85/http%3A%2F%2Fo.aolcdn.com%2Fhss%2Fstorage%2Fmidas%2F117113dd871a765ef87826ad13a1dd7%2F204359485%2FRTR3QGIE.jpeg");
        results.add(item);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.COLUMN);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        imageGrid.setLayoutManager(layoutManager);
        imageGridAdapter = new ImageSearchAdapter(results);
        imageGrid.setAdapter(imageGridAdapter);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent != null && Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            search();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        createSearchView(menu);
        return true;
    }

    private void createSearchView(Menu menu) {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setSubmitButtonEnabled(true);

        if(query != null) {
            searchView.setQuery(query, false);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String queryParam) {
                query = queryParam;
                search();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_go_btn", null, null);
        ImageView goButton = searchView.findViewById(id);
        goButton.setImageResource(R.drawable.go_button);
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
            removeFocusFromSearchView();
            Toast.makeText(ImageSearchActivity.this, query, Toast.LENGTH_LONG).show();
        }
    }
}
