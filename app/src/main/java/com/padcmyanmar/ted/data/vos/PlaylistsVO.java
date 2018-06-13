package com.padcmyanmar.ted.data.vos;

import java.util.List;


public class PlaylistsVO {
    private int playListId;
    private String title;
    private String imageUrl;
    private int totalTalks;
    private String description;
    private List<TalksVO> talksInPlaylist;

    public int getPlayListId() {
        return playListId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTotalTalks() {
        return totalTalks;
    }

    public String getDescription() {
        return description;
    }

    public List<TalksVO> getTalksInPlaylist() {
        return talksInPlaylist;
    }
}
