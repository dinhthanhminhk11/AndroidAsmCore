package com.example.asmandroidcore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    public static String TABLE_NAME_STUDENT = "Student";
    public static String TABLE_NAME_CLASSROOM = "Classroom";


    public MyHelper(Context context) {
        super(context, "data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE_NAME_CLASSROOM + "( " +
                "idClassroom integer primary key autoincrement ," +
                "codeClassroom text not null , " +
                "nameClassroom text not null" +
                ")";
        sqLiteDatabase.execSQL(sql);
        sql = "create table " + TABLE_NAME_STUDENT + "( " +
                "idStudent integer primary key autoincrement ," +
                "nameStudent text not null ," +
                "birth text not null ," +
                "phone text not null ," +
                "nameClassroom text not null" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME_CLASSROOM);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME_STUDENT);
        onCreate(sqLiteDatabase);
    }
}
