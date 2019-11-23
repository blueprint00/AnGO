package com.e.ango.API;

import android.os.AsyncTask;

import com.e.ango.API.Play.Item;
import com.e.ango.API.Play.Items;
import com.e.ango.API.Play.PlayObject;
import com.e.ango.API.Play.PlayDto;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlayTask extends AsyncTask<Void, Void, ArrayList<PlayObject>> {
    OkHttpClient client = new OkHttpClient();

    String xCoordinate;
    String yCoordinate;

    public ArrayList<PlayObject> originalPlayObjects = null;

    public PlayTask(String xCoordinate, String yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    protected ArrayList<PlayObject> doInBackground(Void... voids) {

        String result = null;
        String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=VvSYrDrc4pOEEbTc61UkhlbLjj9a1JCbxq3dRy24%2BRftOs9iNCGlQ%2F5W%2FkKBsW4PA6mBS%2BQ20fBc%2BjQWV7rabg%3D%3D&" +
                "mapX=" + yCoordinate + "&mapY=" + xCoordinate + "&radius=2000&&arrange=E&MobileOS=AND&MobileApp=AppTest&numOfRows=100&_type=json";
        //String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=w%2BvIOXxlMW05eJcl2Fw894grerR3LUGL1LepRRDEjPN1ntgk2i2%2FV00sSzbn7QZAnF5iqz2WG%2BiDxWnf2tdy4A%3D%3D&mapX=126.981611&mapY=37.568477&radius=10000&&arrange=E&MobileOS=AND&MobileApp=AppTest&numOfRows=100&_type=json";

        try {
            okhttp3.Request request = new Request.Builder()
                    .url(url)
                    .build();

            okhttp3.Response response = client.newCall(request).execute();

            Gson gson = new Gson();
            PlayDto playDto = gson.fromJson(response.body().string(), PlayDto.class);

            com.e.ango.API.Play.Response response1 = playDto.response;
            com.e.ango.API.Play.Body body = response1.body;
            Items items = body.items;
            ArrayList<Item> item = items.item;

            originalPlayObjects = new ArrayList<>();
            for (int i = 0; i < item.size(); i++) {
                Item it = item.get(i);

                //이거를 사용자가 지정할 수 있게 할까...? 그럼 안되겠지...?
                //일단 거리 제한 두지 말고 original로 저장해놨다가 recommend에서 처리해주면 되지않나
                if(it.dist <= 2000) {
                    originalPlayObjects.add(new PlayObject(it.addr1, it.addr2, it.areacode, it.cat1, it.cat2, it.cat3,
                            it.contentid, it.contenttypeid, it.createdtime, it.dist,
                            it.firstimage2, it.title));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("play1");
        } catch(IllegalStateException e) {
            e.printStackTrace();
            System.out.println("play2");
        }
        return originalPlayObjects;
    }

    @Override
    protected void onPostExecute(ArrayList<PlayObject> s) {
        super.onPostExecute(s);
    }
}