package rjl.com.androidcoursedesign;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rjl.com.androidcoursedesign.student.AlterPassword;

public class StudentMenuActivity extends AppCompatActivity {


    private String result="";

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            JSONObject jsonObject = null;
            JSONObject jsonObject2 = null;

            if (msg.what==0x117){

                if (msg.obj.toString().equals("抱歉，查询个人用户信息失败！")){
                    Toast.makeText(StudentMenuActivity.this,"抱歉，目前无您的详细信息！",Toast.LENGTH_LONG);
                }else {

                    try{
                        jsonObject = new JSONObject(msg.obj.toString());

                        Intent intent = getIntent();
                        String user = intent.getStringExtra("user");
                        jsonObject2 = new JSONObject(user);

                        AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                        builder.setTitle("个人信息");
                        StringBuilder sb = new StringBuilder();
                        sb.append("姓名：" + jsonObject.optString("name") + "\n");
                        sb.append("学号：" + jsonObject.optString("sno")  + "\n");
                        sb.append("专业：" + jsonObject.optString("major")  + "\n");
                        sb.append("账号：" + jsonObject2.optString("username")+ "\n");
                        sb.append("密码：" + jsonObject2.optString("password") + "\n");
                        builder.setMessage(sb.toString());
                        builder.create().show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }


            }else if (msg.what==0x118){

                if (msg.obj.toString().equals("no")){
                    Toast.makeText(StudentMenuActivity.this,"抱歉，目前没有您的科目成绩！",Toast.LENGTH_LONG);
                }else {
                    JSONObject jsonObject3 = null;

                    try{
                        jsonObject3 = new JSONObject(msg.obj.toString());


                        if (jsonObject3.optString("major").equals("软件工程")){

                            AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                            builder.setTitle("成绩信息");
                            StringBuilder sb = new StringBuilder();
                            sb.append("姓名：" + jsonObject3.optString("name") + "\n");
                            sb.append("学号：" + jsonObject3.optString("sno")  + "\n");
                            sb.append("专业：" + jsonObject3.optString("major")  + "\n");
                            sb.append("Spring架构与设计：" + jsonObject3.optString("score1")+ "\n");
                            sb.append("Android开发：" + jsonObject3.optString("score2") + "\n");
                            sb.append("Hibernate实战：" + jsonObject3.optString("score3") + "\n");
                            builder.setMessage(sb.toString());
                            builder.create().show();

                        }else if (jsonObject3.optString("major").equals("大数据")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                            builder.setTitle("成绩信息");
                            StringBuilder sb = new StringBuilder();
                            sb.append("姓名：" + jsonObject3.optString("name") + "\n");
                            sb.append("学号：" + jsonObject3.optString("sno")  + "\n");
                            sb.append("专业：" + jsonObject3.optString("major")  + "\n");
                            sb.append("Hadoop大数据处理：" + jsonObject3.optString("score1")+ "\n");
                            sb.append("Android开发：" + jsonObject3.optString("score2") + "\n");
                            sb.append("Spark技术：" + jsonObject3.optString("score3") + "\n");
                            builder.setMessage(sb.toString());
                            builder.create().show();
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                            builder.setTitle("成绩信息");
                            StringBuilder sb = new StringBuilder();
                            sb.append("姓名：" + jsonObject3.optString("name") + "\n");
                            sb.append("学号：" + jsonObject3.optString("sno")  + "\n");
                            sb.append("专业：" + jsonObject3.optString("major")  + "\n");
                            sb.append("JavaWeb开发：" + jsonObject3.optString("score1")+ "\n");
                            sb.append("Android开发：" + jsonObject3.optString("score2") + "\n");
                            sb.append("单片机入门：" + jsonObject3.optString("score3") + "\n");
                            builder.setMessage(sb.toString());
                            builder.create().show();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }

            return true;
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_menu);

        final Button studentInfo = findViewById(R.id.PersonInfo);
        final  Button course = findViewById(R.id.course);
        final Button score =findViewById(R.id.score);
        final Button teacher = findViewById(R.id.teacher);
        final Button password = findViewById(R.id.updatePassword);
        final Button exit = findViewById(R.id.exit);


        studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        String no =intent.getStringExtra("sno");
                        Log.i("=============","已发送用户信息请求！");
                        String target = "http://172.16.146.95:8080/test/userController/user";
                        Map<String,String> params = new HashMap<>();
                        params.put("sno",no);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x117;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }
                }).start();

            }
        });


        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentMenuActivity.this,CourseActivity.class);
                startActivity(intent);
            }
        });


        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = getIntent();
                        String no =intent.getStringExtra("sno");
                        Log.i("=============","已发送成绩信息请求！");
                        String target = "http://172.16.146.95:8080/test/scoreController/getScore";
                        Map<String,String> params = new HashMap<>();
                        params.put("sno",no);
                        result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x118;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }
                }).start();


            }
        });


        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentMenuActivity.this);
                builder.setTitle("辅导员信息");
                StringBuilder sb = new StringBuilder();
                sb.append("姓名：" + "黄金飞" + "\n");
                sb.append("电话：" + "13104925611" + "\n");
                sb.append("部门：" + "学生处" + "\n");
                sb.append("办公室：" + "综合楼411" + "\n");

                builder.setMessage(sb.toString());
                builder.create().show();
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentMenuActivity.this,AlterPassword.class);
                startActivity(intent);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
    }

