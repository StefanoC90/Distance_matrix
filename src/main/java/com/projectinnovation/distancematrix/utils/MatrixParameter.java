package com.projectinnovation.distancematrix.utils;

import com.google.maps.model.LatLng;

public class MatrixParameter {
    private LatLng startPoint;
    private LatLng endPoint;
    private int horizontalSlit;
    private int verticalSplit;

    public LatLng getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(LatLng startPoint) {
        this.startPoint = startPoint;
    }

    public LatLng getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(LatLng endPoint) {
        this.endPoint = endPoint;
    }

    public int getHorizontalSlit() {
        return horizontalSlit;
    }

    public void setHorizontalSlit(int horizontalSlit) {
        this.horizontalSlit = horizontalSlit;
    }

    public int getVerticalSplit() {
        return verticalSplit;
    }

    public void setVerticalSplit(int verticalSplit) {
        this.verticalSplit = verticalSplit;
    }
}
