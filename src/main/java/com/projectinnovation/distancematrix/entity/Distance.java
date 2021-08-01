package com.projectinnovation.distancematrix.entity;

import com.google.maps.model.TravelMode;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "DISTANCE")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_Location1", nullable=false)
    private Location idPoint1;

    @ManyToOne
    @JoinColumn(name="id_Location2", nullable=false)
    private Location idPoint2;

    private Long duration;

    private Date startTime;

    private TravelMode travelMode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getIdPoint1() {
        return idPoint1;
    }

    public void setIdPoint1(Location idPoint1) {
        this.idPoint1 = idPoint1;
    }

    public Location getIdPoint2() {
        return idPoint2;
    }

    public void setIdPoint2(Location idPoint2) {
        this.idPoint2 = idPoint2;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public TravelMode getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }

    public Distance(){
        super();
    }

    public Distance(Location idPoint1, Location idPoint2, Long duration) {
        this.idPoint1 = idPoint1;
        this.idPoint2 = idPoint2;
        this.duration = duration;
    }
}
