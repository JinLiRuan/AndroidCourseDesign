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
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rjl.com.androidcoursedesign.admin.AlterPassword;

public class AdminMenuActivity extends AppCompatActivity {


    public static List<Student> studentList = new ArrayList<Student>();
    private StudentAdapter adapter;
    private ListView listView;
    private String result = "";


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x112) {
                try {

                    JSONArray jsonArray = new JSONArray(msg.obj.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                        //利用while循环，当有数据都添加到数组
                        Student student =new Student();
                        student.setId(jsonObject.optInt("id"));
                        student.setSno(jsonObject.optString("sno"));
                        student.setName(jsonObject.optString("name"));
                        student.setMajor(jsonObject.optString("major"));

                        studentList.add(student);

                    }


                    Intent intent = new Intent();
                    intent.setClass(AdminMenuActivity.this,StudentInfo.class);
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            return true;
        }

    });




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);


        final Button studentInfo = findViewById(R.id.studentInfo);
        final  Button course = findViewById(R.id.major_course);
        final Button score =findViewById(R.id.score_update);
        final Button user = findViewById(R.id.selectUser);
        final Button password = findViewById(R.id.adminPassword);
        final Button exit = findViewById(R.id.admin_exit);



        studentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("=============","已发送学生信息请求！");
                        String target = "http://172.16.146.95:8080/test/studentController/allStudent";
                        Map<String,String> params = new HashMap<>();
                        params.put("id","all");
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




        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminMenuActivity.this);
                builder.setTitle("本学期各专业必修课程");
                StringBuilder sb = new StringBuilder();
                sb.append("软件工程：" + "\n");
                sb.append("1.Spring架构与设计"+ "\n");
                sb.append("2.Android开发" + "\n");
                sb.append("3.Hibernate实战"+ "\n\n");

                sb.append("大数据：" + "\n");
                sb.append("1.Hadoop大数据处理"+ "\n");
                sb.append("2.Android开发" + "\n");
                sb.append("3.Spark技术"+ "\n\n");

                sb.append("电子与计算机工程：" + "\n");
                sb.append("1.JavaWeb开发"+ "\n");
                sb.append("2.Android开发" + "\n");
                sb.append("3.单片机入门"+ "\n");


                builder.setMessage(sb.toString());
                builder.create().show();
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,SelectMajor.class);
                startActivity(intent);

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,UserList.class);
                startActivity(intent);
            }
        });


        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,AlterPassword.class);
                startActivity(intent);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(AdminMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
