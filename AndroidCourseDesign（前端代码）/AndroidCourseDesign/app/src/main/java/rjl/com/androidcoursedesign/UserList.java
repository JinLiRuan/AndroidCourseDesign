package rjl.com.androidcoursedesign;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserList extends AppCompatActivity {

         private String result = "";
        private ListView mListView;
        //需要适配的数据
        private String[] id = new String[10];
        private String[] account = new String[10];
        private String[] pass =new String[10];



    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0x115) {
                try {

                    JSONArray jsonArray = new JSONArray(msg.obj.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {  //通过for循环遍历JSON数据
                        Log.i("================",i+"");
                        JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据

                        id[i] = String.valueOf(jsonObject.optInt("id"));
                        account[i] = jsonObject.optString("username");
                        pass[i] = jsonObject.optString("password");
                    }
                    Toast.makeText(UserList.this, "数据已更新", Toast.LENGTH_LONG);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            if (msg.what == 0x116) {
                JSONObject jsonObject =null;

                if (msg.obj.toString().equals("抱歉，查询个人用户信息失败！")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                    builder.setTitle("查询结果");
                    builder.setMessage("抱歉，没有该用户信息！");
                    builder.setPositiveButton("确定", null);
                    builder.show();

                } else {

                    try {
                        jsonObject = new JSONObject(msg.obj.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(UserList.this);
                    builder.setTitle("查询结果：");
                    StringBuilder sb = new StringBuilder();
                    sb.append("姓名：" + jsonObject.optString("name")+"\n");
                    sb.append("学号:" +jsonObject.optString("sno")+ "\n");
                    sb.append("专业:" + jsonObject.optString("major")+"\n");
                    sb.append("辅导员:" + jsonObject.optString("teacher")+"\n");
                    builder.setMessage(sb.toString());
                    builder.create().show();
                }
            }
            return true;
            }

    });



    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_list);
            //初始化ListView控件
            mListView = (ListView) findViewById(R.id.lv);
            //创建一个Adapter的实例
            MyBaseAdapter mAdapter = new MyBaseAdapter();
            //设置Adapter
            mListView.setAdapter(mAdapter);

            final Button all = findViewById(R.id.user_all);
            final Button user = findViewById(R.id.user_select);
            final EditText sno = findViewById(R.id.user_sno);


            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Log.i("=============","已发送请求所有用户信息请求！");
                            String target = "http://172.16.146.95:8080/test/userController/allUser";
                            Map<String,String> params = new HashMap<>();
                            params.put("id","all");
                            result = ConnectionUtils.postRequest(target,params);
                            Log.i("=======================",result);
                            Message msg = new Message();
                            msg.what = 0x115;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            });


            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String no = sno.getText().toString();
                            Log.i("=============","已发送用户信息请求！");
                            String target = "http://172.16.146.95:8080/test/userController/user";
                            Map<String,String> params = new HashMap<>();
                            params.put("sno",no);
                            result = ConnectionUtils.postRequest(target,params);
                            Log.i("=======================",result);
                            Message msg = new Message();
                            msg.what = 0x116;
                            msg.obj = result;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            });



        }
        //创建一个类继承BaseAdapter
        class MyBaseAdapter extends BaseAdapter {
            //得到item的总数
            @Override
            public int getCount() {
                //返回ListView Item条目的总数
                return id.length;
            }
            //得到Item代表的对象
            @Override
            public Object getItem(int position) {
                //返回ListView Item条目代表的对象
                return id[position];
            }
            //得到Item的id
            @Override
            public long getItemId(int position) {
                //返回ListView Item的id
                return position;
            }
            //得到Item的View视图
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHolder holder = null;
                if(convertView == null){
                    //将list_item.xml文件找出来并转换成View对象
                    convertView  = View.inflate(UserList.this, R.layout.user_item, null);
                    //找到list_item.xml中创建的TextView
                    holder = new ViewHolder();
                    holder.user_id = (TextView) convertView.findViewById(R.id.user_id);
                    holder.user_account = (TextView) convertView.findViewById(R.id.user_account);
                    holder.user_pass = (TextView) convertView.findViewById(R.id.user_pass);

                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.user_id.setText(id[position]);
                holder.user_account.setText(account[position]);
                holder.user_pass.setText(pass[position]);

                return convertView;


            }

        }
        static class ViewHolder{
            TextView user_id;
            TextView user_account;
            TextView user_pass;

        }
    }



