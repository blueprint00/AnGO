package com.e.ango.Recommend;

import android.drm.DrmStore;
import android.os.AsyncTask;

import com.e.ango.API.HttpAsyncTaskAirWeather;
import com.e.ango.API.HttpAsyncTaskPlay;
import com.e.ango.API.Play.PlayObject;
import com.e.ango.API.Type;
import com.e.ango.MainActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import static com.e.ango.Login.LoginTask.ip;
import static com.e.ango.Login.RegisterTask.token;


//서버
//"response_msg" : “RecommendCategory_fail”
//"category_list" :
//   "category_id"
//   "category_name"
//"total"

public class RecommendTask extends AsyncTask<Void, Void, ArrayList> {

    //OkHttpClient client = new OkHttpClient();
    //public static String ip = "192.168.1.108"; //자신의 IP번호
    String serverip = "http://" + ip + ":8080/ango/Dispacher"; // 연결할 jsp주소
    public ArrayList<PlayObject> playObjects;

    RecommendDto recommendDto;
    public RecommendTask(String weather_type){//String request_msg, String weather_type, String token) {
        recommendDto = new RecommendDto("RecommendCategory", weather_type, token);
    }

    @Override
    protected ArrayList<PlayObject> doInBackground(Void... voids) {
        try {
            String str;
            URL url = new URL(serverip);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); // GET??

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            Gson gson = new Gson();
            osw.write(gson.toJson(recommendDto));
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                RecommendResponse recommendResponse = gson.fromJson(buffer.toString(), RecommendResponse.class);

                if (recommendResponse.response_msg.equals("RecommendCategory_success")) {
                    ArrayList<Category_list> category_lists = recommendResponse.category_list;
                    playObjects = new ArrayList<PlayObject>();
                    ArrayList<PlayObject> pob = HttpAsyncTaskPlay.pob;

                    System.out.println(category_lists.size() + "/" + pob.size());

                    for (int i = 0; i < category_lists.size(); i++) {
                        for (int j = 0; j < pob.size(); j++) {
                            if (category_lists.get(i).category_id.equals(pob.get(j).getCat2())) {
                                playObjects.add(pob.get(j));

                                System.out.println("addr1 : " + playObjects.get(i).addr1 +
                                        "\naddr2 : " + playObjects.get(i).addr2 +
                                        "\ncat2 : " + playObjects.get(i).cat2 +
                                        "\ncontentid : " + playObjects.get(i).contentid +
                                        "\ncontenttypid : " + playObjects.get(i).contenttypeid +
                                        "\ndist" + playObjects.get(i).dist +
                                        "\nfirstimage2 : " + playObjects.get(i).firstimage2 +
                                        "\ntitle : " + playObjects.get(i).title + "\n\n");
                            }
                        }
                    }
                    //    public String addr1;
                    //    public String addr2;
                    //    public long areacode;
                    //    public String cat1;
                    //    public String cat2;
                    //    public String cat3;
                    //    public long contentid;
                    //    public long contenttypeid;
                    //    public long createdtime;
                    //    public long dist;
                    //    public String firstimage2;
                    //    public String title;
//                    for (int i = 0; i < playObjects.size(); i++) {
//                        dataParsed += //"addr1 : " + playObjects.get(i).addr1 +
//                                //"\naddr2 : " + playObjects.get(i).addr2 +
//                                //"\ncat2 : " + playObjects.get(i).cat2 +
//                                //"\ncontentid : " + playObjects.get(i).contentid +
//                                //"\ncontenttypid : " + playObjects.get(i).contenttypeid +
//                                //"\ndist" + playObjects.get(i).dist +
//                                //"\nfirstimage2 : " + playObjects.get(i).firstimage2 +
//                                "title : " + playObjects.get(i).title ;//+ "\n\n";
//                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playObjects;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}