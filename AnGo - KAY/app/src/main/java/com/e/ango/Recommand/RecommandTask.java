package com.e.ango.Recommand;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class RecommandTask extends AsyncTask<Void, Void, Void> {

    public static String ip = "172.16.15.152"; //자신의 IP번호
    String serverip = "http://" + ip + ":8080/ango/Dispacher"; // 연결할 jsp주소
    Boolean flag = true; // 선호도 조사 했는지 안했는지

    RecommandTask() { }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String str;
            URL url = new URL(serverip);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST"); // GET??

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            Gson gson = new Gson();
            //osw.write(gson.toJson());
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                ServerResponse serverResponse = gson.fromJson(buffer.toString(), ServerResponse.class);

                if(serverResponse.response_msg.equals("RecommandCategory_success")){
                    ArrayList<Category_list> category_lists = serverResponse.category_list;
                }
            }

            return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
            //} catch (MalformedURLException e) {
            //    e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


        @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

}

