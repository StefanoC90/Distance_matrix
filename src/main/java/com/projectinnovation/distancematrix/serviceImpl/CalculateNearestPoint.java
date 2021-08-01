package com.projectinnovation.distancematrix.serviceImpl;

import com.google.maps.model.LatLng;
import com.projectinnovation.distancematrix.entity.Location;
import com.projectinnovation.distancematrix.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.StreamSupport;

import static java.lang.StrictMath.abs;

@Service
public class CalculateNearestPoint {

    @Value("${point.start.lat}")
    private Double startLat;

    @Value("${point.start.lng}")
    private Double startLng;

    @Value("${point.end.lat}")
    private Double endLat;

    @Value("${point.end.lng}")
    private Double endLng;

    @Autowired
    LocationRepository locationRepository;

    Location returnNearMatrixPoint(LatLng point){
        checkPointisInside(point);
        return StreamSupport.stream(locationRepository.findAll().spliterator(), false)
                .min(Comparator.comparingDouble(coordinate ->
                    abs(point.lat - coordinate.getPoint().getY()) + abs(point.lng - coordinate.getPoint().getX())))
                .get();
    }

    private void checkPointisInside(LatLng point) {
        if (startLng < point.lng
            && startLat > point.lat
            && endLng > point.lng
            && startLat > point.lat) {
            return;
        }
        throw new IllegalArgumentException("Point not included on the rectangle Area");
    }
}
