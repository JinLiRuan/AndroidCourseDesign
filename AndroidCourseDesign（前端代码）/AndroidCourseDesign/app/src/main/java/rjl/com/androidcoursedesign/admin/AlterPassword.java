package rjl.com.androidcoursedesign.admin;

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

import java.util.HashMap;
import java.util.Map;

import rjl.com.androidcoursedesign.AdminMenuActivity;
import rjl.com.androidcoursedesign.ConnectionUtils;
import rjl.com.androidcoursedesign.R;

public class AlterPassword extends AppCompatActivity {

    private String result="";

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what == 0x116){
              if (msg.obj.toString().equals("ok")){

                  AlertDialog.Builder builder= new AlertDialog.Builder(AlterPassword.this);
                  builder.setTitle("更新通知");
                  builder.setMessage("密码修改成功！");
                  builder.setPositiveButton("确定",null);
                  builder.show();



              }else {
                  AlertDialog.Builder builder= new AlertDialog.Builder(AlterPassword.this);
                  builder.setTitle("更新通知");
                  builder.setMessage("抱歉，没有该用户！！！");
                  builder.setPositiveButton("确定",null);
                  builder.show();
              }

            }


            return true;
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alter_password);


        final Button yes = findViewById(R.id.ap_true);
        final Button back = findViewById(R.id.ap_back);
        final EditText name = findViewById(R.id.update_name);
        final EditText pass = findViewById(R.id.pass1);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String account = name.getText().toString();
                        String password = pass.getText().toString();
                        Log.i("=============","已发送修改密码信息请求！");
                        String target = "http://172.16.146.95:8080/test/userController/updateUser";
                        Map<String,String> params = new HashMap<>();
                        params.put("account",account);
                        params.put("password",password);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x116;
                        msg.obj = result;
                        handler.sendMessage(msg);
                        name.setText("");
                        pass.setText("");
                    }
                }).start();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlterPassword.this,AdminMenuActivity.class);
                startActivity(intent);
            }
        });



    }


}
