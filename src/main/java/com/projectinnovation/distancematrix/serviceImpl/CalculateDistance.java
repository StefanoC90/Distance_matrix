package com.projectinnovation.distancematrix.serviceImpl;

import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.google.maps.model.LatLng;
import com.projectinnovation.distancematrix.dto.CalculateDistanceDTO;
import com.projectinnovation.distancematrix.entity.Distance;
import com.projectinnovation.distancematrix.entity.Location;
import com.projectinnovation.distancematrix.repository.DistanceRepository;
import com.projectinnovation.distancematrix.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CalculateDistance {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CalculateNearestPoint calculateNearestPoint;

    @Autowired
    private DistanceRepository distanceRepository;

    public List<CalculateDistanceDTO> calculate(LatLng start) {
        List<Location> listcoorindates = StreamSupport.stream(locationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Location startLocation = calculateNearestPoint.returnNearMatrixPoint(start);
        List<Distance> distanceSaved = distanceRepository.findByIdPoint1OrIdPoint2(startLocation, startLocation);
        addSameDistance(distanceSaved, startLocation);
        Location[] locationNotSaved = getLocationNotSaved(listcoorindates, distanceSaved, startLocation);

        if(locationNotSaved.length > 0){
            DistanceMatrixElement[] sistanceMatrixElementNotSaved =
                    splitLocation(locationNotSaved).stream().map(loc ->
                        CallDataMatrix.calculateDistance(convertLatLang(startLocation),
                                Arrays.stream(loc).filter(Objects::nonNull).map(this::convertLatLang).toArray(LatLng[]::new))[0].elements)
                            .flatMap(Arrays::stream)
                        .toArray(DistanceMatrixElement[]::new);
            saveDistance(startLocation, locationNotSaved, sistanceMatrixElementNotSaved, distanceSaved);
        }
        return generateOutput(startLocation, distanceSaved);
    }

    private void addSameDistance(List<Distance> distanceSaved, Location startLocation) {
        distanceSaved.add(new Distance(startLocation, startLocation, 0L));
    }

    private ArrayList<Location[]> splitLocation(Location[] locationNotSaved) {
        ArrayList<Location[]> locationSplitted = new ArrayList<>();
        new ArrayList();
        int n = 10;
        int r = 0;
        Location[] locationSp = new Location[n];
        for(int i = 0; i < locationNotSaved.length; i++){
            r = i%n;
            locationSp[r] = locationNotSaved[i];
            if( r == (n-1)) {
                locationSplitted.add(locationSp);
                locationSp = new Location[n];
            }
        }
        if (r != (n-1)){
            locationSplitted.add(locationSp);
        }
        return locationSplitted;
    }

    private Location[] getLocationNotSaved(List<Location> listcoorindates, List<Distance> distanceSaved, Location startLocation) {
    return listcoorindates
                .stream()
                .filter(saved -> !getLocationSaved(distanceSaved, startLocation).contains(saved.getId()) && startLocation.getId() != saved.getId())
                .toArray(Location[]::new);
    }

    private List<Long> getLocationSaved(List<Distance> distanceSaved, Location startLocation) {
        return distanceSaved.stream()
                .map(distance -> returnOtherPoint(distance, startLocation))
                .map(dist -> dist.getId())
                .collect(Collectors.toList());
    }

    private Location returnOtherPoint(Distance distance, Location startLocation) {
        return distance.getIdPoint1().getId().equals(startLocation.getId()) ? distance.getIdPoint2() : distance.getIdPoint1();
    }

    private void saveDistance(Location startLocation, Location[] locationNotSaved, DistanceMatrixElement[] elements, List<Distance> distanceSaved) {
        for(int i=0; i< locationNotSaved.length; i++){
            Distance save = distanceRepository.save(new Distance(startLocation, locationNotSaved[i], calculateDuration(elements[i])));
            distanceSaved.add(save);
        }
    }

    private Long calculateDuration(DistanceMatrixElement element) {
        return element.status == DistanceMatrixElementStatus.OK ? element.duration.inSeconds : 5400L;
    }

    private List<CalculateDistanceDTO> generateOutput(Location start, List<Distance> distanceSaved) {
        return distanceSaved.stream()
            .map(distance -> new CalculateDistanceDTO(distance.getDuration(), convertLatLang(returnOtherPoint(distance, start))))
            .collect(Collectors.toList());
    }

    private LatLng convertLatLang(Location location){
        return new LatLng(location.getPoint().getY(), location.getPoint().getX());
    }
}
