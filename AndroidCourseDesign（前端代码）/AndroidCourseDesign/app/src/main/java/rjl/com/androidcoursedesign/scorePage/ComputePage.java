package rjl.com.androidcoursedesign.scorePage;

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

import java.util.HashMap;
import java.util.Map;

import rjl.com.androidcoursedesign.AdminMenuActivity;
import rjl.com.androidcoursedesign.ConnectionUtils;
import rjl.com.androidcoursedesign.R;
import rjl.com.androidcoursedesign.SelectMajor;

public class ComputePage extends AppCompatActivity {


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x115){
                if (msg.obj.toString().equals("成绩添加成功！")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ComputePage.this);
                    builder.setTitle("操作提示" ) ;
                    builder.setMessage("数据已添加成功了！") ;
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setClass(ComputePage.this,AdminMenuActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();

                }else {
                    Toast.makeText(ComputePage.this, "添加出错了！", Toast.LENGTH_SHORT).show();
                }

            }

            return true;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_score_compute);
        final Button yes = findViewById(R.id.cp_yes);
        final  Button no = findViewById(R.id.cp_no);
        final EditText sno = findViewById(R.id.cp_sno);
        final EditText name = findViewById(R.id.cp_name);
        final EditText score1 = findViewById(R.id.cp_score1);
        final EditText score2 = findViewById(R.id.cp_score2);
        final EditText score3 = findViewById(R.id.cp_score3);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String no = sno.getText().toString();
                        String sname = name.getText().toString();
                        Intent intent = getIntent();
                        String major = intent.getStringExtra("major");
                        String sc1 = score1.getText().toString();
                        String sc2 = score2.getText().toString();
                        String sc3 = score3.getText().toString();
                        String target = "http://172.16.146.95:8080/test/scoreController/insertScore";
                        Map<String,String> params = new HashMap<>();
                        params.put("no",no);
                        params.put("sname",sname);
                        params.put("major",major);
                        params.put("sc1",sc1);
                        params.put("sc2",sc2);
                        params.put("sc3",sc3);
                        String result = ConnectionUtils.postRequest(target,params);
                        Log.i("=======================",result);
                        Message msg = new Message();
                        msg.what = 0x115;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();



            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(ComputePage.this,SelectMajor.class);
                startActivity(intent);

            }
        });
    }
}