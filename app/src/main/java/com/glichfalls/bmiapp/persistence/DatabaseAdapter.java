package com.glichfalls.bmiapp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private static final int databaseVersion = 1;
    private static final String databaseName = "data";
    private static final String tableName = "employee";

    private final DatabaseHelper helper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context, databaseName, null, databaseVersion);
    }

    public void open() {
        database  = helper.getWritableDatabase();
    }

    public void insertEmployee(String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        database.insert(tableName, null, values);
    }

    public String selectEmployee(int id) {
        String[] columns = { "name" };
        String selection = "id = ?";
        String[] args = { Integer.toString(id) };
        Cursor result = database.query(tableName, columns, selection, args, null, null, null);
        return result.getString(0);
    }

    public List<String> selectAll() {
        String[] columns = { "name" };
        Cursor c = database.query(tableName, columns, null, null, null, null, null);
        List<String> results = new ArrayList<>();
        c.moveToFirst();
        while(!c.isAfterLast()) {
            results.add(c.getString(0));
            c.moveToNext();
        }
        c.close();
        return results;
    }

    public void updateEmployee(int id, String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        String selection = "id = ?";
        String[] args = { Integer.toString(id) };
        database.update(tableName, values, selection, args);
    }

    public void deleteEmployee(int id) {
        String selection = "id = ?";
        String[] args = { Integer.toString(id) };
        database.delete(tableName, selection, args);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String createTablesQuery = String.format("CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);", tableName);
        private static final String dropTablesQuery = String.format("DROP TABLE IF EXISTS %s;", tableName);

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(createTablesQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL(dropTablesQuery);
            onCreate(database);
        }
    }

}