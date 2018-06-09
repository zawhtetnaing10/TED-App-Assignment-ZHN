package com.padcmyanmar.ted.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.adapters.TalksAdapter;
import com.padcmyanmar.ted.delegates.TalksDelegate;

public class TalkListActivity extends BaseActivity implements TalksDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_context_menu_large));

        RecyclerView rvTedTalks = findViewById(R.id.rv_ted_talks);
        TalksAdapter talksAdapter = new TalksAdapter(this);

        rvTedTalks.setAdapter(talksAdapter);
        rvTedTalks.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapTalks() {
        Intent intent = new Intent(getApplicationContext(), TalkDetailsActivity.class);
        startActivity(intent);
    }
}
