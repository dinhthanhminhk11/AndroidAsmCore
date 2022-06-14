package com.example.asmandroidcore.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asmandroidcore.R;
import com.example.asmandroidcore.dao.ClassroomDao;
import com.example.asmandroidcore.model.Classroom;
import com.example.asmandroidcore.ui.adapter.ClassroomAdapter;

import java.util.List;

public class ShowListClassroomActivity extends AppCompatActivity implements ClassroomAdapter.Callback {
    private ListView listview;
    private ClassroomDao dao;
    private List<Classroom> data;
    private ClassroomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_classroom);

        getSupportActionBar().setTitle("Danh Sách Lớp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        dao = new ClassroomDao(this);
        listview = (ListView) findViewById(R.id.listview);
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

    public void showData(){
        data = dao.getListAllClassroom();
        adapter = new ClassroomAdapter(data , this, this);
        listview.setAdapter(adapter);
    }

    @Override
    public void delete(Classroom classroom) {
        dao.deleteClassroom(classroom.getId());
        Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
        showData();
    }
}