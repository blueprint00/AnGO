package com.e.ango.API;

import android.os.AsyncTask;

import com.e.ango.API.Play.Item;
import com.e.ango.API.Play.Items;
import com.e.ango.API.Play.PlayObject;
import com.e.ango.API.Play.PlayDto;
import com.e.ango.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTaskPlay extends AsyncTask<Void, Void, String > {
    OkHttpClient client = new OkHttpClient();
    String singleparsed = "", dataParsed = "";

    String longitude = MainActivity.longti;
    String latitude = MainActivity.lati;

    public static ArrayList<PlayObject> pob = new ArrayList<>();

    @Override
    protected String doInBackground(Void... voids) {

        System.out.println(longitude + "/" + latitude);
        String result = null;
        String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=VvSYrDrc4pOEEbTc61UkhlbLjj9a1JCbxq3dRy24%2BRftOs9iNCGlQ%2F5W%2FkKBsW4PA6mBS%2BQ20fBc%2BjQWV7rabg%3D%3D&" + "mapX= " + longitude + "&mapY=" + latitude + "&radius=2000&&arrange=E&MobileOS=AND&MobileApp=AppTest&numOfRows=100&_type=json";
        //String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=VvSYrDrc4pOEEbTc61UkhlbLjj9a1JCbxq3dRy24%2BRftOs9iNCGlQ%2F5W%2FkKBsW4PA6mBS%2BQ20fBc%2BjQWV7rabg%3D%3D&mapX=126.981611&mapY=37.568477&radius=2000&&arrange=E&MobileOS=AND&MobileApp=AppTest&numOfRows=100&_type=json";

        try {
            okhttp3.Request request = new Request.Builder()
                    .url(url)
                    .build();

            okhttp3.Response response = client.newCall(request).execute();

            Gson gson = new Gson();
            PlayDto playDto = gson.fromJson(response.body().string(), PlayDto.class);

            com.e.ango.API.Play.Response response1 = playDto.response;
            com.e.ango.API.Play.Body body = response1.body;
            Items itemss = body.items;
            ArrayList<Item> items = itemss.item;

            for (int i = 0; i < items.size(); i++) {
                Item it = items.get(i);

                if(it.dist <= 2000) {
                    pob.add(new PlayObject(it.addr1, it.addr2, it.areacode, it.cat1, it.cat2, it.cat3,
                            it.contentid, it.contenttypeid, it.createdtime, it.dist,
                            it.firstimage2, it.title));
                }
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
        } catch(IllegalStateException e) {
            e.printStackTrace();
            System.out.println("??");
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //APIActivity.data.setText(this.dataParsed);

    }


    public ArrayList<PlayObject> getPob(){ return pob; }

}