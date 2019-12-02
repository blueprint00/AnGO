package com.e.ango.CurrentLocation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import android.widget.ListView;

import com.e.ango.API.AirWeatherTask;
import com.e.ango.API.Play.PlayObject;
import com.e.ango.API.PlayTask;
import com.e.ango.CurrentLocationActivity;
import com.e.ango.ListView.ListAdapter;
import com.e.ango.ListView.ListData;
import com.e.ango.Recommend.RecommendTask;
import com.e.ango.SurveyConnection.LoadImage;

import com.e.ango.R;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.e.ango.CurrentLocationActivity.iv_weatherIcon;


public class CurrentLocation {

    private String TAG;
    CurrentLocationActivity currentLocationActivity;

//    public static TextView tv_Humidity, tv_Weather, tv_Temperature, tv_Air;
//    public static ImageView iv_weatherIcon; // 날씨 아이콘 출력 뷰
    private int drawableIcon = R.drawable.untitled3; // 날씨 아이콘 지

    public String currentLatitude, currentLongiutde; // 현재 위치 위도, 경도

    private String cityName; // 지오코더로 건너받은 영어 전체 주소
    private String weather_type; // 날씨 타입

    private ArrayList<PlayObject> originalPlayObjects; // 전체 파싱 리스트
    private ArrayList<PlayObject> userPreferencePlayObjects; // 유저에게 제공할 추천 리스트

    //추천 놀거리 띄워줄 리스트 뷰
    private static ListView printList = null;

    private boolean flag_coordinate = false; // 좌표 받아 오면 true
    private int weatherTypeInt;
    private Bitmap bitmap;

    boolean flag_userPreferencePlayObjects;///////////////////////////////////////
    ArrayList<ListData> arrListData;
    LatLng playLatLng;
    public GoogleMap mMap;
    private Marker playMarker;

    public CurrentLocation(CurrentLocationActivity currentLocationActivity) {
        this.currentLocationActivity = currentLocationActivity;

    }

    public ArrayList<PlayObject> getOriginalPlayObjects(String currentLatitude, String currentLongitude) {

        try {
            originalPlayObjects = new PlayTask(currentLatitude, currentLongitude).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return originalPlayObjects;
    }

    public String getWether_type(String currentLatitude, String currentLongiutde, String fullCityName) {

        try {
            weather_type = new AirWeatherTask(currentLatitude, currentLongiutde, fullCityName).execute().get();

            System.out.println("WEATHERTYPE : " + weather_type);
            //weather_type이 계산 됐을 경우
            if (weather_type != null) {
                //날씨 아이콘 이미지뷰 설정
                weatherTypeInt = setDrawableIcon(weather_type);
                Drawable drawable = currentLocationActivity.getResources().getDrawable(weatherTypeInt);
                iv_weatherIcon.setImageDrawable(drawable);
                setWeatherIcon(weather_type);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return weather_type;
    }


    public ArrayList<PlayObject> getUserPreferncePlayObjects(String weather_type, ArrayList<PlayObject> originalPlayObjects) {
        try {
            userPreferencePlayObjects = new RecommendTask(weather_type, originalPlayObjects).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userPreferencePlayObjects;

    }

    public ArrayList<ListData> printListData(ArrayList<PlayObject> userPreferencePlayObjects) {
        int size = userPreferencePlayObjects.size();
        int idx = 0;

        ArrayList<ListData> arrListData = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ListData listData = new ListData();
            try {
                if (userPreferencePlayObjects.get(i).getFirstimage2() == null) {
                    bitmap = BitmapFactory.decodeResource(currentLocationActivity.getResources(), R.drawable.no_image);
                } else {
                    bitmap = new LoadImage().execute(userPreferencePlayObjects.get(i).getFirstimage2()).get();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (userPreferencePlayObjects.size() == 0) {
                listData.setTitle("추천 놀거리가 없습니다.");
            } else {
                listData.setTitle(userPreferencePlayObjects.get(i).title);
                listData.setSummary(userPreferencePlayObjects.get(i).categoryName);
                listData.setContentId(userPreferencePlayObjects.get(i).contentid);
                listData.setCategoryId(userPreferencePlayObjects.get(i).preferCategoryId);

                if (userPreferencePlayObjects.get(i).addr2 == null)
                    listData.setAddress(userPreferencePlayObjects.get(i).addr1);
                else
                    listData.setAddress(userPreferencePlayObjects.get(i).addr1 + " " + userPreferencePlayObjects.get(i).addr2);

                listData.setBitmap(bitmap);

                //류저 추천 놀거리가 존재한다
                flag_userPreferencePlayObjects = true;

            }
            arrListData.add(listData);
            ListAdapter listAdapter = new ListAdapter(arrListData);
        }

        System.out.println("flag_userPreferenceObjects : " + flag_userPreferencePlayObjects);
        return arrListData;
    }


    public void setWeatherIcon(String weather_type) {
        weatherTypeInt = setDrawableIcon(weather_type);
        Drawable drawable = currentLocationActivity.getResources().getDrawable(weatherTypeInt);
        iv_weatherIcon.setImageDrawable(drawable);
    }

    public void setCurrentLocation(Location location) {

        System.out.println("///////////////////////////////////////////////1");
        Log.d(TAG ,"setCurrentLocation");
        //   if (currentMarker != null) currentMarker.remove();

        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mMap.moveCamera(cameraUpdate);

        if (flag_userPreferencePlayObjects) {
            for (int i = 0; i < userPreferencePlayObjects.size(); i++) {
                setPlayLocation(userPreferencePlayObjects.get(i));
            }
        }
    }

    public LatLng getPlayLocation(PlayObject userPreferncePlayObjects){

        LatLng playLatLng = new LatLng(userPreferncePlayObjects.getMapy(), userPreferncePlayObjects.getMapx());

        return playLatLng;
    }

    ////////////////////////////////////놀거리 추천 4
    public MarkerOptions setPlayLocation(PlayObject userPreferencePlayObjects){

        System.out.println("///////////////////////////////////////////////2");
        Log.d(TAG ,"setPlayLocation");

        System.out.println("놀거리 추천 4 1");
        System.out.println("놀거리 추천 4 2");
        System.out.println("PlayCoordinates : " + userPreferencePlayObjects.getMapx());

//        LatLng playMarkerLatLng = new LatLng(userPreferencePlayObjects.getMapy(), userPreferencePlayObjects.getMapx());
        LatLng playMarkerLatLng = getPlayLocation(userPreferencePlayObjects);

        System.out.println("GETMAPX : " + userPreferencePlayObjects.getMapx());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(playMarkerLatLng);
        markerOptions.title(userPreferencePlayObjects.getTitle());
        markerOptions.snippet(String.valueOf(userPreferencePlayObjects.getDist()) + "m");
        markerOptions.draggable(true);

        playMarker = mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));


        System.out.println("놀거리 추천 4 3");


        System.out.println("놀거리 추천 4 4");
        return markerOptions;
    }

    public int setDrawableIcon(String weather_type) {
        int weatherInt = 0;
        if(weather_type != null) {
            switch (weather_type) {
                case "type_0":
                    weatherInt = R.drawable.medical_mask;
                    //return R.drawable.medical_mask;
                    break;
                case "type_1":
                    weatherInt = R.drawable.snowflake;
                    //return R.drawable.snowflake;
                case "type_2":
                case "type_3":
                case "type_4":
                    weatherInt = R.drawable.rain;
                    //return R.drawable.rain;
                    break;
                case "type_5":
                case "type_6":
                case "type_7":
                case "type_8":
                case "type_9":
                case "type_10":
                    weatherInt = R.drawable.cloud;
                    //return R.drawable.cloud;
                    break;
                case "type_11":
                case "type_12":
                case "type_13":
                case "type_14":
                case "type_15":
                case "type_16":
                    weatherInt = R.drawable.sun;
                    break;
                //return R.drawable.sun;
            }
            System.out.println("MAIN WEATHERINT : " + weatherInt);

        }
        return weatherInt;
    }
}
