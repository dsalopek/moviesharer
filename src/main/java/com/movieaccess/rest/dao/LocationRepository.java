package com.movieaccess.rest.dao;

import com.movieaccess.rest.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    public List<Location> findAll();
    public Location findByLocationId(long locationId);
}
