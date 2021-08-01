package com.projectinnovation.distancematrix.controller;

import com.projectinnovation.distancematrix.entity.Location;
import com.projectinnovation.distancematrix.repository.LocationRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/locations")
public class RepositoryController {
    @Autowired
    private LocationRepository locationRepository;

    @GetMapping(path = "/")
    List<Location>  locationList(){
        return StreamSupport.stream(locationRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/save")
    String save(){
        Location location = new Location();
        location.setName("sad");
        location.setPoint(new GeometryFactory().createPoint(new Coordinate(2.0,3.9)));
        locationRepository.save(location);
        return "ok";
    }
}
