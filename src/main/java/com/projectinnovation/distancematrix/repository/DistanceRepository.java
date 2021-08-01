package com.projectinnovation.distancematrix.repository;

import com.projectinnovation.distancematrix.entity.Distance;
import com.projectinnovation.distancematrix.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistanceRepository extends CrudRepository<Distance, Long> {
    List<Distance> findByIdPoint1OrIdPoint2(Location location1, Location location2);
}
