package com.padcmyanmar.ted.events;


import com.padcmyanmar.ted.data.vos.TalksVO;

import java.util.List;

public class SuccessGetTalksEvent {
    private List<TalksVO> talks;

    public SuccessGetTalksEvent(List<TalksVO> talks) {
        this.talks = talks;
    }

    public List<TalksVO> getTalks() {
        return talks;
    }
}
