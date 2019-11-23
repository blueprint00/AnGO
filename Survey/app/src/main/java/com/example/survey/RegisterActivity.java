package com.e.ango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

import com.e.ango.Login.RegisterTask;
import com.e.ango.Recommend.RecommendTask;
import com.e.ango.Response.ResponseDto;

import static com.e.ango.Login.LoginTask.token;

public class RegisterActivity  extends AppCompatActivity {

    EditText et_id, et_pass, et_name;
    Button btn_check, btn_register;
    Boolean flag = null;

    String toastMessage;
    ResponseDto registerResponse;

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

                    //RegisterTask registerTask = new RegisterTask(et_id.getText().toString(), "CheckAccount");
                    try {
                        registerResponse = new RegisterTask(et_id.getText().toString(), "CheckAccount").execute().get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //중복 아이디 검사
                    toastMessage = "사용 가능한 아이디 입니다.";
                    if(registerResponse.getResponse_msg().equals("CheckAccount_fail"))
                         toastMessage = "중복된 아이디 입니다.";
                    Toast.makeText(RegisterActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                }
            });

            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        registerResponse = new RegisterTask(et_id.getText().toString(), et_pass.getText().toString(), et_name.getText().toString(),"JoinAccount").execute().get();

                        if(registerResponse.getResponse_msg().equals("JoinAccount_success")) {
                            token = registerResponse.getToken();
                            if(token != null) {
                                Intent intent = new Intent(RegisterActivity.this, SurveyActivity.class);
                                startActivity(intent);
                            }
                        }
                        else toastMessage = "회원가입 실패";
                        Toast.makeText(RegisterActivity.this, toastMessage, Toast.LENGTH_SHORT).show();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
