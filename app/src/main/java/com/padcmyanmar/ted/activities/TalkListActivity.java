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
import com.padcmyanmar.ted.data.models.TalksModel;
import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.delegates.TalksDelegate;
import com.padcmyanmar.ted.events.SuccessGetTalksEvent;
import com.padcmyanmar.ted.utils.TEDTalksConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalkListActivity extends BaseActivity implements TalksDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_ted_talks)
    RecyclerView rvTedTalks;

    private TalksAdapter talksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_context_menu_large));
        talksAdapter = new TalksAdapter(this);

        rvTedTalks.setAdapter(talksAdapter);
        rvTedTalks.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        TalksModel.getObjInstance().loadTalksList();
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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapTalks(TalksVO talks) {
        Intent intent = new Intent(getApplicationContext(), TalkDetailsActivity.class);
        intent.putExtra(TEDTalksConstants.TALK_ID,talks.getTalkId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetTalks(SuccessGetTalksEvent event){
            talksAdapter.setmTalksList(event.getTalks());
    }
}
