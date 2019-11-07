package com.example.abulia2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SavedListsModel implements MVPComponents.SavedListsModel {
    private static final String TAG = "SavedListsModel";
    private MVPComponents.SavedListsPresenterContract savedListsPresenter;
    private ListContract.List.ListDbHelper dbHelper;
    private ArrayList<String> tablesList;
    private SQLiteDatabase db;

    SavedListsModel(MVPComponents.SavedListsPresenterContract presenter){
        savedListsPresenter = presenter;
        dbHelper  = new ListContract.List.ListDbHelper(presenter.getView());
        tablesList = new ArrayList<>();
    }

    public ArrayList<String> getTablesList(){
        Log.d(TAG, "getTablesList: called, db is null");
        db = dbHelper.getReadableDatabase();
        Log.d(TAG, "getTablesList: called");
        Cursor cursor = dbHelper.getAllTables(db);
        if(cursor != null){
            if(cursor.moveToFirst()) {
                do {
                    Log.d(TAG, "Tables are :" + cursor.getString(cursor.getColumnIndex("name")));
                    tablesList.add(cursor.getString(cursor.getColumnIndex("name")));
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
//        db.close();
////        dbHelper.close();
        return tablesList;
    }

    public void dropTable(String tableName){
        dbHelper.dropTable(db, tableName);
    }
}



