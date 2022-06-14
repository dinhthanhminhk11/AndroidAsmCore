package com.example.asmandroidcore.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asmandroidcore.R;
import com.example.asmandroidcore.dao.ClassroomDao;
import com.example.asmandroidcore.dao.StudentDao;
import com.example.asmandroidcore.model.Student;
import com.example.asmandroidcore.ui.adapter.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity implements StudentAdapter.Callback {
    private EditText nameStudent;
    private EditText birth;
    private EditText phone;
    private Spinner listClassroom;
    private Button add;
    private Button edit;
    private ListView listview;
    private List<Student> list;
    private StudentDao dao;
    private ClassroomDao daoClassroom;
    private StudentAdapter adapter;
    private String nameClassroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        getSupportActionBar().setTitle("Quan li Sinh vien");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        nameStudent = (EditText) findViewById(R.id.nameStudent);
        birth = (EditText) findViewById(R.id.birth);
        phone = (EditText) findViewById(R.id.phone);
        listClassroom = (Spinner) findViewById(R.id.listClassroom);
        add = (Button) findViewById(R.id.add);
        edit = (Button) findViewById(R.id.edit);
        listview = (ListView) findViewById(R.id.listview);
        dao = new StudentDao(this);
        daoClassroom = new ClassroomDao(this);
        list = new ArrayList<>();
        iniSpinner();

        add.setOnClickListener(v -> {
            String name = nameStudent.getText().toString();
            String brith = birth.getText().toString();
            String sdt = phone.getText().toString();
            Student student = new Student();
            student.setName(name);
            student.setPhone(sdt);
            student.setBirth(brith);
            student.setNameClassroom(nameClassroom);
            if (dao.addStudent(student) == true) {
                Toast.makeText(this, "Them thanh Cong", Toast.LENGTH_SHORT).show();
                showData();
                return;
            } else {
                Toast.makeText(this, "Them Bai", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    @Override
    public void delete(Student student) {
        dao.deleteStudent(student.getId());
        showData();
        Toast.makeText(this, "xoa thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void edit(Student student) {
        nameStudent.setText(student.getName());
        birth.setText(student.getBirth());
        phone.setText(student.getPhone());
        edit.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
        edit.setOnClickListener(v -> {
            String name = nameStudent.getText().toString();
            String brith = birth.getText().toString();
            String sdt = phone.getText().toString();

            Student student1 = new Student(student.getId(), name, brith, sdt, nameClassroom);
            if (dao.editStudent(student1) == true) {
                Toast.makeText(this, "Edit thanh Cong", Toast.LENGTH_SHORT).show();
                showData();
                edit.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                nameStudent.setText("");
                birth.setText("");
                phone.setText("");
                return;
            } else {
                Toast.makeText(this, "Edit Bai", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void call(Student student) {
        String mobilephone = student.getPhone();
        String call = "tel:" + mobilephone.trim();
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse(call));
        startActivity(i);
    }

    public void iniSpinner() {
        listClassroom.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, daoClassroom.getListNameClassroom()));
        listClassroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameClassroom = listClassroom.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void showData() {
        list = dao.getListAllStudent();
        adapter = new StudentAdapter(list, this);
        listview.setAdapter(adapter);
    }
}