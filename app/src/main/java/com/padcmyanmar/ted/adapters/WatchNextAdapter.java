package com.padcmyanmar.ted.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.ted.R;
import com.padcmyanmar.ted.data.vos.TalksVO;
import com.padcmyanmar.ted.delegates.TalksDelegate;
import com.padcmyanmar.ted.viewholders.WatchNextViewHolder;

import java.util.ArrayList;
import java.util.List;


public class WatchNextAdapter extends RecyclerView.Adapter<WatchNextViewHolder> {

    private List<TalksVO> talks;
    private TalksDelegate mTalksDelegate;

    public WatchNextAdapter(TalksDelegate talksDelegate) {
        mTalksDelegate = talksDelegate;
        talks = new ArrayList<>();
    }

    @Override
    public WatchNextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view_holder_watch_next,parent,false);
        return new WatchNextViewHolder(view, mTalksDelegate);
    }

    @Override
    public void onBindViewHolder(WatchNextViewHolder holder, int position) {
        holder.setmTalksData(talks.get(position));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void setTalks(List<TalksVO> talks) {
        this.talks = talks;
        notifyDataSetChanged();
    }
}
