package com.projectinnovation.distancematrix.dump;

import com.google.maps.model.LatLng;
import com.projectinnovation.distancematrix.utils.MatrixParameter;
import com.projectinnovation.distancematrix.utils.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilledMatrix {

    @Value("${point.start.lat}")
    private Double startLat;

    @Value("${point.start.lng}")
    private Double startLng;

    @Value("${point.end.lat}")
    private Double endLat;

    @Value("${point.end.lng}")
    private Double endLng;

    @Value("${split.horizontal}")
    private int horizonalSplit;

    @Value("${split.vertical}")
    private int verticallSplit;

    public List<LatLng> fullMatrix() {
        MatrixParameter matrix = new MatrixParameter();
        matrix.setStartPoint(new LatLng(startLat, startLng));
        matrix.setEndPoint(new LatLng(endLat, endLng));
        matrix.setHorizontalSlit(horizonalSplit);
        matrix.setVerticalSplit(verticallSplit);
        return Util.generateMatrix(matrix);
    }
}
