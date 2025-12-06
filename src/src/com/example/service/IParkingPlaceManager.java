package com.example.service;

import com.example.entity.PlaceStatus;


import java.util.Date;

public interface IParkingPlaceManager {

    boolean isAvailable(int idPlace, Date startTime, Date endTime);

    void updateStatus(int idPlace, PlaceStatus placeStatus);
}
