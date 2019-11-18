package com.e.ango.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;
import com.e.ango.R;
import com.e.ango.Survey;

public class RegisterActivity  extends AppCompatActivity {

    EditText et_id, et_pass, et_name;
    Button btn_check, btn_register;
    Boolean flag = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        et_id = (EditText) findViewById(R.id.et_id);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_name = (EditText) findViewById(R.id.et_name);
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_register = (Button) findViewById(R.id.btn_register);

        try {

            btn_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new RegisterTask(et_id.getText().toString(), "CheckAccount").execute();

                    try {
                        flag = new RegisterTask(et_id.getText().toString(), "CheckAccount").execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //중복 아이디 검사
                    String toastMessage = "사용 가능한 아이디 입니다.";
                    if(!flag){ toastMessage = "중복된 아이디 입니다."; }
                    Toast.makeText(RegisterActivity.this, toastMessage, Toast.LENGTH_SHORT).show();


                }
            });

            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag == null || flag == false) {
                        Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                    }
                    else if(flag) {
                        new RegisterTask(et_id.getText().toString(), et_pass.getText().toString(), et_name.getText().toString(), "JoinAccount").execute();

                        //Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        //startActivity(intent);
                        Intent intent = new Intent(RegisterActivity.this, Survey.class);
                        startActivity(intent);
                    }
                }
            });
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
