package rjl.com.androidcoursedesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StudentAdapter extends ArrayAdapter<Student> {
    private int resourceId;
    List<Student> studentList;

    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        resourceId = resource;
        studentList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = studentList.get(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.student_id = view.findViewById(R.id.id);
            viewHolder.student_name = view.findViewById(R.id.student_name);
            viewHolder.student_sno = view.findViewById(R.id.student_sno);
            viewHolder.student_major =  view.findViewById(R.id.student_major);

            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.student_id.setText(String.valueOf(student.getId()));
        viewHolder.student_name.setText(String.valueOf(student.getName()));
        viewHolder.student_sno.setText(String.valueOf(student.getSno()));
        viewHolder.student_major.setText(String.valueOf(student.getMajor()));

        return view;

    }

    class ViewHolder {
        TextView student_id;
        TextView student_name;
        TextView student_sno;
        TextView student_major;

    }

}
