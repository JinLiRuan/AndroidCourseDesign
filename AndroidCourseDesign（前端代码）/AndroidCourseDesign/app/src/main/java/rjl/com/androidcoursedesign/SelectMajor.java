package rjl.com.androidcoursedesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import rjl.com.androidcoursedesign.scorePage.BigDataPage;
import rjl.com.androidcoursedesign.scorePage.ComputePage;
import rjl.com.androidcoursedesign.scorePage.SoftwarePage;

public class SelectMajor extends AppCompatActivity {


    private RadioGroup sexRadioGroup ;
    private RadioButton  radioButton1 ;
    private RadioButton  radioButton2 ;
    private RadioButton  radioButton3;
    static Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_major);

        final Button yes = findViewById(R.id.yes);
        final  Button no = findViewById(R.id.no);

        sexRadioGroup = findViewById(R.id.group);
        radioButton1  = findViewById(R.id.software);
        radioButton2  = findViewById(R.id.bigData);
        radioButton3  = findViewById(R.id.compute);

        intent = new Intent();
         sexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == radioButton1.getId()){

                    intent.setClass(SelectMajor.this, SoftwarePage.class);
                    intent.putExtra("major","软件工程");
                    startActivity(intent);

                }else if(checkedId == radioButton2.getId()){

                    intent.setClass(SelectMajor.this, BigDataPage.class);
                    intent.putExtra("major","大数据");
                    startActivity(intent);

                }else if (checkedId == radioButton3.getId()){

                    intent.setClass(SelectMajor.this, ComputePage.class);
                    intent.putExtra("major","电子计算机工程");
                    startActivity(intent);

                }else {
                    Toast.makeText(SelectMajor.this, "你还没有选择！", Toast.LENGTH_LONG).show();
                }}
        });



//                radioButton1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.setClass(SelectMajor.this, SoftwarePage.class);
//
//                    }
//                });
//
//                radioButton2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.setClass(SelectMajor.this, BigDataPage.class);
//
//                    }
//                });
//
//                radioButton3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.setClass(SelectMajor.this, ComputePage.class);
//
//                    }
//                });
//
//        startActivity(intent);






        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(SelectMajor.this,AdminMenuActivity.class);
                startActivity(intent);

            }
        });




}


}
