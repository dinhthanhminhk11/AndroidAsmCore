package com.example.asmandroidcore.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asmandroidcore.R;
import com.example.asmandroidcore.model.Student;

import org.w3c.dom.Text;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<Student> data;

    private LinearLayout contentStudent;
    private TextView idStudent;
    private TextView nameStudent;
    private TextView phone;
    private TextView birth;
    private TextView nameClassroom;
    private Button call;
    private Button delete;
    private Button edit;
    private Callback callback;

    public StudentAdapter(List<Student> data, Callback callback) {
        this.data = data;
        this.callback = callback;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewStudent;

        if (view == null) {
            viewStudent = View.inflate(viewGroup.getContext(), R.layout.item_student_listview, null);
        } else {
            viewStudent = view;
        }


        contentStudent = (LinearLayout) viewStudent.findViewById(R.id.content_student);
        idStudent = (TextView) viewStudent.findViewById(R.id.idStudent);
        nameStudent = (TextView) viewStudent.findViewById(R.id.nameStudent);
        phone = (TextView) viewStudent.findViewById(R.id.phone);
        birth = (TextView) viewStudent.findViewById(R.id.birth);
        nameClassroom = (TextView) viewStudent.findViewById(R.id.nameClassroom);
        call = (Button) viewStudent.findViewById(R.id.call);
        delete = (Button) viewStudent.findViewById(R.id.delete);
        edit = (Button) viewStudent.findViewById(R.id.edit);


        Student student = (Student) getItem(i);

        nameStudent.setText(student.getName());
        phone.setText(student.getPhone());
        birth.setText(student.getBirth());
        nameClassroom.setText(student.getNameClassroom());
        call.setOnClickListener(v -> {
            callback.call(student);
        });
        edit.setOnClickListener(v -> {
            callback.edit(student);
        });
        delete.setOnClickListener(v -> {
            callback.delete(student);
        });

        return viewStudent;
    }

    public interface Callback {
        void delete(Student student);

        void edit(Student student);

        void call(Student student);
    }
}
