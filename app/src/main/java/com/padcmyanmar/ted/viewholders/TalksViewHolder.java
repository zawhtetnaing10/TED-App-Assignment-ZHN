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


public class TalksViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate mtalksDelegate;
    private TalksVO mtalkData;

    @BindView(R.id.iv_background_image)
    ImageView ivBackgroundImage;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_speaker_name)
    TextView tvSpeakerName;

    @BindView(R.id.tv_duration)
    TextView tvDuration;

    public TalksViewHolder(View itemView, TalksDelegate talksDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mtalksDelegate=talksDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtalksDelegate.onTapTalks(mtalkData);
            }
        });
    }

    public void setMtalkData(TalksVO mtalkData) {
        this.mtalkData = mtalkData;

        Glide.with(ivBackgroundImage.getContext()).load(mtalkData.getImageUrl()).into(ivBackgroundImage);
        tvSpeakerName.setText(mtalkData.getSpeaker().getName());
        tvTitle.setText(mtalkData.getTitle());
        tvDuration.setText(mtalkData.getMinutesAndSecondsString());
    }
}
