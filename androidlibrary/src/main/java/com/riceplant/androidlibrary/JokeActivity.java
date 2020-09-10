package com.riceplant.androidlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @NonNull
    private static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView textViewJoke = findViewById(R.id.textview_joke);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(EXTRA_JOKE);

        if (joke == null || joke.isEmpty()) {
            textViewJoke.setText(R.string.joke_not_found);
        } else {
            textViewJoke.setText(joke);
        }

        ActionBar actionBar = this.getSupportActionBar();

        // Set the action bar back button to look like an up button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent jokeIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(EXTRA_JOKE, joke);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
