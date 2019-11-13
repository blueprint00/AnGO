package com.e.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    Button btn_login, btn_registerMain;
    EditText et_id, et_pass;
    Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = (EditText) findViewById(R.id.et_id);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_registerMain = (Button) findViewById(R.id.btn_registerMain);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new LoginTask(et_id.getText().toString(), et_pass.getText().toString(), "LoginAccount").execute();
                try {
                    flag = new LoginTask(et_id.getText().toString(), et_pass.getText().toString(), "LoginAccount").execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //로그인 실패
                if(flag == null) {
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }

                //설문조사 안 했을 때
                //else if(!flag){
                //    Intent intent = new Intent(MainActivity, 설문조사.class);
                //    startActivity(intent);
                //}
                else {
                    Intent intent = new Intent(LoginActivity.this, SelectActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_registerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.i("?g", e.toString());
                }

            }
        });
    }


}
