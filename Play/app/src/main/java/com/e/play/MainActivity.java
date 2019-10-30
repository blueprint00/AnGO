package com.e.play;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = com.e.play.MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HttpAsyncTask().execute("http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=VvSYrDrc4pOEEbTc61UkhlbLjj9a1JCbxq3dRy24%2BRftOs9iNCGlQ%2F5W%2FkKBsW4PA6mBS%2BQ20fBc%2BjQWV7rabg%3D%3D&mapX=126.981611&mapY=37.568477&radius=1000&listYN=Y&arrange=A&MobileOS=ETC&MobileApp=AppTest&_type=json&numOfRows=500");
    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String > {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            String strUrl = params[0];

            try {
                Request request = new Request.Builder()
                        .url(strUrl)
                        .build();

                okhttp3.Response response = client.newCall(request).execute();

                Gson gson = new Gson();
                PlayVO playVO = gson.fromJson(response.body().string(), PlayVO.class);
                ArrayList<PlayObject> pob = new ArrayList<>();
                String parsingResult = null;

                com.e.play.Response response1 = playVO.response;
                com.e.play.Body body = response1.body;
                Items items = body.items;
                ArrayList<Item> itemss = items.item;

                for (int i = 0; i < itemss.size(); i++) {
                    Item it = itemss.get(i);

                    pob.add(new PlayObject(it.addr1, it.addr2, it.areacode, it.cat1, it.cat2, it.cat3,
                                            it.contentid, it.contenttypeid, it.createdtime, it.dist,
                                            it.firstimage2, it.title));

                    System.out.println("daily : {" +
                            "\nAddr1 : " + pob.get(i).getAddr1() +
                            "\nAddr2 : " + pob.get(i).getAddr2() +
                            "\nAreaCode : " + pob.get(i).getAreacode() +
                            "\nCat1 : " + pob.get(i).getCat1() +
                            "\nCat2 : " + pob.get(i).getCat2() +
                            "\nCat3: " + pob.get(i).getCat3() +
                            "\nContentid: " + pob.get(i).getContentid() +
                            "\nContentTypeid: " + pob.get(i).getContenttypeid() +
                            "\nCreatedtime : " + pob.get(i).getCreatedtime() +
                            "\nDis : " + pob.get(i).getDist() +
                            "\nFirstImage2 : " + pob.get(i).getFirstimage2() +
                            "\nTitle : " + pob.get(i).getTitle() + "}\n\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null) {
                Log.d(TAG, s);
            }

        }
    }
}