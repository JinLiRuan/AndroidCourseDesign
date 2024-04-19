package rjl.com.androidcoursedesign;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;

public class StudentInfo extends AppCompatActivity {

    private StudentAdapter adapter;
    private ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);
        adapter = new StudentAdapter(this,R.layout.student_item,AdminMenuActivity.studentList);
        listView =findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }


    }

