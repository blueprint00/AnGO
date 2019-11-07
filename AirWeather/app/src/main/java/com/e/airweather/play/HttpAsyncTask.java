package com.e.airweather.play;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpAsyncTask extends AsyncTask<Void, Void, String > {
    OkHttpClient client = new OkHttpClient();
    String singleparsed = "", dataParsed = "";
    String mapX = "127.0273";
    String mapY = "36.1352";

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        String strUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=VvSYrDrc4pOEEbTc61UkhlbLjj9a1JCbxq3dRy24%2BRftOs9iNCGlQ%2F5W%2FkKBsW4PA6mBS%2BQ20fBc%2BjQWV7rabg%3D%3D&mapX=" + mapX + "&" + "mapY=" + mapY + "&radius=10000&&arrange=E&MobileOS=AND&MobileApp=AppTest&numOfRows=100&_type=json";

        try {
            Request requestP = new Request.Builder()
                    .url(strUrl)
                    .build();

            okhttp3.Response response = client.newCall(requestP).execute();

            Gson gson = new Gson();
            PlayVO playVO = gson.fromJson(response.body().string(), PlayVO.class);
            ArrayList<PlayObject> pob = new ArrayList<>();

            com.e.airweather.play.Response response1 = playVO.response;
            com.e.airweather.play.Body body = response1.body;
            Items items = body.items;
            ArrayList<Item> itemss = items.item;


            for (int i = 0; i < itemss.size(); i++) {
                Item it = itemss.get(i);

                pob.add(new PlayObject(it.addr1, it.addr2, it.areacode, it.cat1, it.cat2, it.cat3,
                        it.contentid, it.contenttypeid, it.createdtime, it.dist,
                        it.firstimage2, it.title));
            }

            for (int i = 0; i < pob.size(); i++) {

                singleparsed = "daily : {" +
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
                        "\nTitle : " + pob.get(i).getTitle() + "\n}\n";
                dataParsed = dataParsed + singleparsed;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("p");
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        com.e.airweather.MainActivity.data.setText(this.dataParsed);

    }
}