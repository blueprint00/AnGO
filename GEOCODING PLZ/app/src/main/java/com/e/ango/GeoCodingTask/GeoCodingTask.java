package com.e.ango.GeoCodingTask;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.e.ango.API.Play.PlayObject;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeoCodingTask {

    public static MarkerOptions setPlayLocationForMarker(ArrayList<PlayObject> userPreferencePlayObjects, Geocoder geocoder, String[] playLocation){

        System.out.println("///////////////////////////////////////////////2");

        List<Address> playCoordinates = null;

        //playLocation = new String[userPreferencePlayObjects.size()];
        System.out.println("놀거리 추천 4 1");
        try {
            for (int i = 0; i < playLocation.length; i++) {
                playCoordinates = geocoder.getFromLocationName(
                        playLocation[i], // 지역 이름
                        1); // 읽을 개수

                System.out.println("놀거리 추천 4 2");
                System.out.println("PlayCoordinates : " + playCoordinates.get(0).getLatitude());

                LatLng playMarkerLatLng = new LatLng(playCoordinates.get(0).getLatitude(), playCoordinates.get(0).getLongitude());

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(playMarkerLatLng);
                markerOptions.title(userPreferencePlayObjects.get(i).getTitle().toString());
                markerOptions.snippet(userPreferencePlayObjects.get(i).getCategoryName());
                markerOptions.draggable(true);

                //currentMarker = mMap.addMarker(markerOptions);
                return markerOptions;
            }

            //System.out.println("놀거리 추천 4 3");

        } catch(IOException e){
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        System.out.println("놀거리 추천 4 4");

        return null;
    }
}
