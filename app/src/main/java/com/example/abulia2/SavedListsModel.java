package com.example.abulia2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SavedListsModel implements MVPComponents.SavedListsModel {
    private MVPComponents.SavedListsPresenterContract savedListsPresenter;
    private ListContract.List.ListDbHelper dbHelper;
    private ArrayList<String> tablesList;
    private SQLiteDatabase db;

    SavedListsModel(MVPComponents.SavedListsPresenterContract presenter){
        savedListsPresenter = presenter;
        dbHelper  = new ListContract.List.ListDbHelper(presenter.getSavedListsView());
        db = dbHelper.getReadableDatabase();
        tablesList = new ArrayList<>();
    }

    public ArrayList<String> getTablesList(){
        Log.d(getClass().getName(), "In getTablesList");
        String selectionArgs[] = { "table", "android_metadata", "sqlite_sequence"};

        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type" + "= ?" + " " +
                "AND name " + "!= ?" + "AND name " + "!= ?" + " ;", selectionArgs);
        if(cursor != null){
            if(cursor.moveToFirst()) {
                do {
                    Log.d(getClass().getName(), "Tables are :" + cursor.getString(cursor.getColumnIndex("name")));
                    tablesList.add(cursor.getString(cursor.getColumnIndex("name")));
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        return tablesList;
    }
}



