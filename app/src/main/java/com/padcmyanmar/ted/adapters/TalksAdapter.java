package com.padcmyanmar.ted.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.delegates.TalksDelegate;
import com.padcmyanmar.ted.viewholders.TalksViewHolder;

import java.util.ArrayList;
import java.util.List;


public class TalksAdapter extends RecyclerView.Adapter<TalksViewHolder> {

    private TalksDelegate mTalksDelegate;
    private List<TalksVO> mTalksList;

    public TalksAdapter(TalksDelegate talksDelegate) {
        mTalksDelegate = talksDelegate;
        mTalksList = new ArrayList<>();
    }

    @Override
    public TalksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talks, parent, false);
        return new TalksViewHolder(view, mTalksDelegate);
    }

    @Override
    public void onBindViewHolder(TalksViewHolder holder, int position) {
        holder.setMtalkData(mTalksList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTalksList.size();
    }

    public void setmTalksList(List<TalksVO> mTalksList) {
        this.mTalksList = mTalksList;
        notifyDataSetChanged();
    }
}
