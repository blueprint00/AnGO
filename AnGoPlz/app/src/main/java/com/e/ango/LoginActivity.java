package com.e.ango;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

import com.e.ango.Login.LoginTask;

import com.e.ango.Recommend.RecommendTask;
import com.e.ango.Response.ResponseDto;

public class LoginActivity extends AppCompatActivity {

    Button btn_login, btn_registerMain;
    EditText et_id, et_pass;
    Boolean flag;
    ResponseDto loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = (EditText) findViewById(R.id.userID);
        et_pass = (EditText) findViewById(R.id.userPW);
        btn_login = (Button) findViewById(R.id.loginBT);
        btn_registerMain = (Button) findViewById(R.id.signupBT);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = et_id.getText().toString();
                String pass = et_pass.getText().toString();
                if (ID.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
                else {
                    LoginTask loginTask = new LoginTask(ID, pass, "LoginAccount");
                    try {
                        loginResponse = loginTask.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    System.out.println("LoginResponse : " + loginResponse.getResponse_msg());
                    if(loginResponse.getResponse_msg().equals("LoginAccess_success")){
                        Intent intent = new Intent(LoginActivity.this, SelectActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    else if(loginResponse.getResponse_msg().equals("LoginAccount_fail")){
                        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT);
                    }

                    else if(loginResponse.getAvailability() == 0){
                        Intent intent = new Intent(LoginActivity.this, SurveyActivity.class);
                        startActivity(intent);
                        finish();
                    }
//                    //로그인 성공
//                     if (flag) {
//                        Intent intent = new Intent(LoginActivity.this, SelectActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                     }
//                     //로그인 싫패
//                     else if (!flag) {
//                        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
//                    }
//
//                    //설문조사
//                    else if(flag == null){
//                        Intent intent = new Intent(LoginActivity.this, SurveyActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                     }
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
                finish();
            }
        });


    }


}