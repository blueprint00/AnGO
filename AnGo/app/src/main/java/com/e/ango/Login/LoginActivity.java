package com.e.ango.Login;

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
import com.e.ango.R;
import com.e.ango.SelectActivity;
import com.e.ango.Survey;

public class LoginActivity extends AppCompatActivity {

    Button btn_login, btn_registerMain;
    EditText et_id, et_pass;
    Boolean flag;

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
                //new LoginTask(et_id.getText().toString(), et_pass.getText().toString(), "LoginAccount").execute();
                try {
                    flag = new LoginTask(et_id.getText().toString(), et_pass.getText().toString(), "LoginAccount").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                //로그인 실패
                if(flag == null) {
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }

                //설문조사 안 했을 때
                else if(!flag){
                    Intent intent = new Intent(LoginActivity.this, Survey.class);
                    startActivity(intent);
                }
                //로그인 성공 -> 메뉴 선택
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
