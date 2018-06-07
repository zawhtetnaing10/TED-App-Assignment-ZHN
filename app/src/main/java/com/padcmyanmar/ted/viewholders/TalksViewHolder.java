package com.padcmyanmar.ted.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.ted.delegates.TalksDelegate;

/**
 * Created by User on 6/2/2018.
 */

public class TalksViewHolder extends RecyclerView.ViewHolder {
    private TalksDelegate mtalksDelegate;

    public TalksViewHolder(View itemView, TalksDelegate talksDelegate) {
        super(itemView);
        mtalksDelegate=talksDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtalksDelegate.onTapTalks();
            }
        });
    }
}
