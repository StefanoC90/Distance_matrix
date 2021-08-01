package com.projectinnovation.distancematrix.serviceImpl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.time.Instant;
import java.time.ZoneOffset;



public class CallDataMatrix {

    private static final String API_KEY = "----";

    public static DistanceMatrixRow[] calculateDistance(LatLng startPoint, LatLng... endMatrix) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);

        DistanceMatrix await = null;
        try {
            Instant instant1 = Instant
                    .now()
                    .atZone(ZoneOffset.UTC)
                    .withHour(8)
                    .withMinute(00)
                    .withSecond(00)
                    .withNano(00)
                    .toInstant();
            await = req
                    .origins(startPoint)
                    .destinations(endMatrix)
                    .mode(TravelMode.TRANSIT)
                    .departureTime(instant1)
                    .await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return await.rows;
    }
}
