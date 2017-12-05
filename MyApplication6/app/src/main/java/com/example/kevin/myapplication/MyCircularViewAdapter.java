package com.example.kevin.myapplication;

/**
 * Created by Kevin on 9/15/2015.
 */

import android.util.Log;
import android.widget.Button;

import com.sababado.circularview.Marker;
import com.sababado.circularview.SimpleCircularViewAdapter;

import java.util.Random;

public class MyCircularViewAdapter extends SimpleCircularViewAdapter {

public int i=0;
    private int[] array = new int[]{R.drawable.buttonshape,R.drawable.buttonshape2,R.drawable.buttonshape3,R.drawable.buttonshape4,R.drawable.buttonshape5,R.drawable.buttonshape6,R.drawable.buttonshape7,R.drawable.buttonshape8,R.drawable.buttonshape9};
    @Override
        public int getCount(){
            return 9;
        }

        public void setupMarker (final  int position, final Marker marker) {
            if (i < 9){
                marker.setSrc(array[i]);
        }
            marker.setFitToCircle(true);
            marker.setRadius(75);
            i++;

        }

    }





