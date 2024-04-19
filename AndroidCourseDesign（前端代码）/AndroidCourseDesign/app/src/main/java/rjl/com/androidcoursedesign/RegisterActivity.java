package rjl.com.androidcoursedesign;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText registerName;
    EditText password;
    private String result = "";


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x112){

                if (msg.obj.toString().equals("no")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("注册提示" ) ;
                    builder.setMessage("抱歉，你不是本校学生不能注册！") ;
                    builder.setPositiveButton("确定" ,  null );
                    builder.show();
                }else {

                    JSONObject jsonObject= null;
                    try {
                        jsonObject = new JSONObject(msg.obj.toString());
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (jsonObject.opt("name") != null){

                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("注册提示" ) ;
                        builder.setMessage("注册成功！账号为学号，密码默认为test") ;
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.setClass(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.create().show();


                    }else{
                        Toast.makeText(RegisterActivity.this,"功能暂不支持！"+jsonObject.opt("name"),Toast.LENGTH_LONG);
                    }


                }

            }

            return true;
        }
    });



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        final Button register = findViewById(R.id.register);
        final Button back = findViewById(R.id.back);
        registerName = findViewById(R.id.name);
        password = findViewById(R.id.password);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name = registerName.getText().toString();
                        String pass = password.getText().toString();

                        Log.i("=============","姓名："+name+"学号:"+pass);
                        String target = "http://172.16.146.95:8080/test/controller/requestRegister";
                        Map<String,String> params = new HashMap<>();
                        params.put("name",name);
                        params.put("sno",pass);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x112;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
