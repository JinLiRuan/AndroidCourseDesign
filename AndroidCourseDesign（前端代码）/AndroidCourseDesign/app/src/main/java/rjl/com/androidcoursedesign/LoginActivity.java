package rjl.com.androidcoursedesign;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private String result = "";
    EditText username;
    EditText password;


        Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x111){

                if (msg.obj.toString().equals("抱歉，用户名或密码错误！")){
                    Toast.makeText(LoginActivity.this, "抱歉，用户名或密码错误！", Toast.LENGTH_SHORT).show();

                }else {

                    JSONObject jsonObject= null;
                    try {
                        jsonObject = new JSONObject(msg.obj.toString());
                        Log.i("============",msg.obj.toString());
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (jsonObject.opt("username").equals("admin")){
                        Toast.makeText(LoginActivity.this, "欢迎您，管理员！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this,AdminMenuActivity.class);
                        startActivity(intent);

                    }else if (jsonObject.opt("username") != null){

//                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                    builder.setTitle("登录提示" ) ;
//                    builder.setMessage("恭喜登录成功！欢迎"+jsonObject.opt("name")) ;
//                    builder.setPositiveButton("确定" ,  null );
//                    builder.show();

                        Toast.makeText(LoginActivity.this, "欢迎您,"+jsonObject.opt("username"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("user",msg.obj.toString());
                        intent.putExtra("sno",jsonObject.optString("username"));
                        intent.setClass(LoginActivity.this,StudentMenuActivity.class);
                        startActivity(intent);
                    }

                }



            }

            return true;
        }
    });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

       final Button res = findViewById(R.id.res);
       final Button login = findViewById(R.id.login);

        username = findViewById(R.id.name);
        password = findViewById(R.id.password);


       res.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.setClass(LoginActivity.this,RegisterActivity.class);
               startActivity(intent);
           }
       });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString();
                String pass = password.getText().toString();
                if (name.equals("")&& pass.equals("")){
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }else {


                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            String name = username.getText().toString();
                            String pass = password.getText().toString();

                            Log.i("=============",name+pass);
                            String target = "http://172.16.146.95:8080/test/controller/requestLogin";
                            Map<String,String> params = new HashMap<>();
                            params.put("name",name);
                            params.put("pass",pass);
                            String result = ConnectionUtils.postRequest(target,params);
                            Log.i("=======================",result);
                            Message msg = new Message();
                            msg.what = 0x111;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    }).start();

                }


            }
        });



        }


    }





