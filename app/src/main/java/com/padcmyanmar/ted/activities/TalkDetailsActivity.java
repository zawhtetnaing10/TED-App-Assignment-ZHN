package com.padcmyanmar.ted.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.adapters.WatchNextAdapter;
import com.padcmyanmar.ted.data.models.TalksModel;
import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.delegates.TalksDelegate;
import com.padcmyanmar.ted.utils.TEDTalksConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TalkDetailsActivity extends BaseActivity implements TalksDelegate {

    @BindView(R.id.iv_talk_details_backdrop)
    ImageView ivTalksDetailsBackdrop;

    @BindView(R.id.tv_talk_details_speaker_name)
    TextView tvTalksDetailsSpeakerName;

    @BindView(R.id.tv_talk_details_title)
    TextView tvTalkDetailsTitle;

    @BindView(R.id.tv_talk_details_duration)
    TextView tvTalkDetailsDuration;

    @BindView(R.id.tv_talk_details_description)
    TextView tvTalkDetailsDescription;

    @BindView(R.id.tv_talk_details_speaker_bio_name)
    TextView tvTalkDetailsSpekaerBioName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_details);
        ButterKnife.bind(this);

        int talksId = getIntent().getIntExtra(TEDTalksConstants.TALK_ID, 0);
        TalksVO talks = TalksModel.getObjInstance().getTalksById(talksId);
        bindData(talks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        RecyclerView rvTalkDetailsWatchNext = findViewById(R.id.rv_talk_details_watch_next);
        WatchNextAdapter adapter = new WatchNextAdapter(this);

        Map<Integer, TalksVO> talksMap = TalksModel.getObjInstance().getAllTalks();
        List<TalksVO> watchNextTalks = new ArrayList<>();
        for (Map.Entry<Integer, TalksVO> entry : talksMap.entrySet()) {
            if (entry.getKey() != talksId) {
                watchNextTalks.add(entry.getValue());
            }
        }
        adapter.setTalks(watchNextTalks);

        rvTalkDetailsWatchNext.setAdapter(adapter);
        rvTalkDetailsWatchNext.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void bindData(TalksVO talks) {
        Glide.with(this).load(talks.getImageUrl()).into(ivTalksDetailsBackdrop);
        tvTalksDetailsSpeakerName.setText(talks.getSpeaker().getName());
        tvTalkDetailsTitle.setText(talks.getTitle());
        tvTalkDetailsDuration.setText(talks.getMinutesAndSecondsString());
        tvTalkDetailsDescription.setText(talks.getDescription());
        tvTalkDetailsSpekaerBioName.setText(talks.getSpeaker().getName());
    }

    @Override
    public void onTapTalks(TalksVO talks) {
        Intent intent = new Intent(getApplicationContext(), this.getClass());
        intent.putExtra(TEDTalksConstants.TALK_ID, talks.getTalkId());
        startActivity(intent);
    }
}
