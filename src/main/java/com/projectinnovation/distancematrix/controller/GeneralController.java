package com.projectinnovation.distancematrix.controller;

import com.google.maps.model.LatLng;
import com.projectinnovation.distancematrix.dto.CalculateDistanceDTO;
import com.projectinnovation.distancematrix.dump.FilledMatrix;
import com.projectinnovation.distancematrix.entity.Location;
import com.projectinnovation.distancematrix.repository.LocationRepository;
import com.projectinnovation.distancematrix.serviceImpl.CalculateDistance;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;


@RestController
public class GeneralController {

    @Autowired
    private FilledMatrix filledMatrix;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    CalculateDistance calculateDistance;

    @Value("${point.test.lat}")
    private Double testLat;

    @Value("${point.test.lng}")
    private Double testLng;

    @GetMapping(path = "/createPoint")
    public String createPoint(){
        filledMatrix.fullMatrix().stream()
                .flatMap(array -> Stream.of(array))
                .forEach(latLang ->{
                    Location location = new Location();
                    location.setPoint(new GeometryFactory().createPoint(
                                            new Coordinate(latLang.lat,latLang.lng)));
                    locationRepository.save(location);
                });
        return "ok";
    }

    @GetMapping(path = "/distancefrompoint")
    public List<CalculateDistanceDTO> distanceFromPoint(
            @RequestParam(required = false) Double x,
            @RequestParam(required = false) Double y){
        if (x==null || y == null ){
            y = testLat;
            x = testLng;
        }
        return calculateDistance.calculate(new LatLng(y, x));
    }
}
