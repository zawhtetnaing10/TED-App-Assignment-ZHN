package com.padcmyanmar.ted.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.delegates.TalksDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WatchNextViewHolder extends RecyclerView.ViewHolder {

    private TalksVO mTalksData;
    private TalksDelegate mTalksDelegate;

    @BindView(R.id.iv_watch_next_image)
    ImageView ivWatchNextImage;

    @BindView(R.id.tv_watch_next_title)
    TextView tvWatchNextTitle;

    @BindView(R.id.tv_watch_next_speaker_name)
    TextView tvWatchNextSpeakerName;

    @BindView(R.id.tv_watch_next_duration)
    TextView tvWatchNextDuration;

    public WatchNextViewHolder(View itemView, TalksDelegate talkDelegate) {
        super(itemView);
        mTalksDelegate = talkDelegate;
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTalksDelegate.onTapTalks(mTalksData);
            }
        });
    }

    public void setmTalksData(TalksVO mTalksData) {
        this.mTalksData = mTalksData;
        Glide.with(ivWatchNextImage.getContext()).load(mTalksData.getImageUrl()).into(ivWatchNextImage);
        tvWatchNextTitle.setText(mTalksData.getTitle());
        tvWatchNextSpeakerName.setText(mTalksData.getSpeaker().getName());
        tvWatchNextDuration.setText(mTalksData.getMinutesAndSecondsString());
    }
}
