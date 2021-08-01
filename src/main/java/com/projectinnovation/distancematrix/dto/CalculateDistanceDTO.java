package com.projectinnovation.distancematrix.dto;

import com.google.maps.model.LatLng;

public class CalculateDistanceDTO {
    private long duration;
    private LatLng latLng;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public CalculateDistanceDTO(long duration, LatLng latLng) {
        this.duration = duration;
        this.latLng = latLng;
    }
}
