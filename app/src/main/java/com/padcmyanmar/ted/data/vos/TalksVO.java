package com.padcmyanmar.ted.data.vos;

import java.util.List;

public class TalksVO {
    private int talkId;
    private String title;
    private SpeakerVO speaker;
    private String imageUrl;
    private int durationInSec;
    private String description;
    private List<TagVO> tags;

    public int getTalkId() {
        return talkId;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDurationInSec() {
        return durationInSec;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTags() {
        return tags;
    }
}
