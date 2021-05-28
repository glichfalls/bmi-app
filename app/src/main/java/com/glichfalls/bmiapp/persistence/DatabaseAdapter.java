package com.glichfalls.bmiapp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.glichfalls.bmiapp.model.bmi.BMI;
import com.glichfalls.bmiapp.model.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private static final int databaseVersion = 1;
    private static final String databaseName = "data";
    private static final String tableName = "employee";
    private static final String bmiTable = "bmi";

    private final DatabaseHelper helper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context, databaseName, null, databaseVersion);
    }

    public void open() {
        database  = helper.getWritableDatabase();
    }

    public User insertUser(String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        long id = database.insert(tableName, null, values);
        if(id == -1) {
            throw new RuntimeException("failed to insert user");
        }
        return new User(id, name);
    }

    public void insertBmi(long userId, float height, float weight) {
        ContentValues values = new ContentValues();
        values.put("user", userId);
        values.put("timestamp", new Timestamp(System.currentTimeMillis()).getTime());
        values.put("height", height);
        values.put("weight", weight);
        database.insert(bmiTable, null, values);
    }

    public User selectUser(long id) {
        String[] columns = { "id", "name" };
        String selection = "id = ?";
        String[] args = { Long.toString(id) };
        Cursor result = database.query(tableName, columns, selection, args, null, null, null);
        if(result.getCount() != 1) {
            return null;
        }
        result.moveToFirst();
        User user = new User(
                result.getLong(0),
                result.getString(1)
        );
        result.close();
        return user;
    }

    public List<User> selectAllUser() {
        String[] columns = { "id", "name" };
        Cursor c = database.query(tableName, columns, null, null, null, null, null);
        List<User> results = new ArrayList<>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            results.add(new User(c.getLong(0), c.getString(1)));
            c.moveToNext();
        }
        c.close();
        return results;
    }

    public List<BMI> selectAllBmiByUser(long id) {
        String[] columns = { "id", "user", "timestamp", "height", "weight" };
        String[] args = { Long.toString(id) };
        Cursor c = database.query(bmiTable, columns, "user = ?", args, null, null, null);
        List<BMI> results = new ArrayList<>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            results.add(new BMI(
                c.getLong(0),
                c.getLong(1),
                c.getLong(2),
                c.getFloat(3),
                c.getFloat(4)
            ));
            c.moveToNext();
        }
        c.close();
        return results;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String createTablesQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);", tableName);
        private static final String createBmiTableQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY AUTOINCREMENT, user INTEGER, timestamp INTEGER, weight FLOAT, height FLOAT);", bmiTable);
        private static final String dropTablesQuery = String.format("DROP TABLE IF EXISTS %s;", tableName);
        private static final String dropBmiTableQuery = String.format("DROP TABLE IF EXISTS %s;", bmiTable);

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(createTablesQuery);
            database.execSQL(createBmiTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL(dropTablesQuery);
            database.execSQL(dropBmiTableQuery);
            onCreate(database);
        }
    }

}