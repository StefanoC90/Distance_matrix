package com.projectinnovation.distancematrix.utils;

import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<LatLng> generateMatrix(MatrixParameter parameter){
        return generate(getArray(parameter.getEndPoint().lng, parameter.getStartPoint().lng, parameter.getHorizontalSlit()),
                        getArray(parameter.getEndPoint().lat, parameter.getStartPoint().lat, parameter.getVerticalSplit()));
    }

    private static ArrayList<LatLng> generate(ArrayList<Double> horizontalPoints, ArrayList<Double> verticalPoints) {
        ArrayList<LatLng> points = new ArrayList();
        for (Double hpoint : horizontalPoints){
            for (Double vpoint : verticalPoints){
                points.add(new LatLng(hpoint, vpoint));
            }
        }
        return points;
    }

    private static ArrayList<Double> getArray(double init, double end, int slit) {
        double delta = (end - init) / slit;
        ArrayList<Double> list = new ArrayList();
        for (int i = 0 ; i < slit; i++){
            list.add(init + delta * i);
         }
        return list;
    }

}
