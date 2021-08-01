package com.projectinnovation.distancematrix.repository;

import com.projectinnovation.distancematrix.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    Location findByName(String name);
}
