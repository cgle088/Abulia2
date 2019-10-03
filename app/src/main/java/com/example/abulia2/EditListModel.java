package com.example.abulia2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class EditListModel implements MVPComponents.EditListsModel {
    private static final String TAG = "EditListModel";
    private EditListPresenter p;
    private ListContract.List.ListDbHelper dbHelper;
    private SQLiteDatabase db;
    private ArrayList<String> listArray;

    EditListModel(EditListPresenter presenter){
        p = presenter;
        dbHelper = new ListContract.List.ListDbHelper(p.getView());
        listArray = new ArrayList<>();
    }
    public ArrayList<String> getList(String listName){
        Log.d(TAG, "getList: called");
        db = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.getTable(db, listName);
        if(cursor != null){
            if(cursor.moveToFirst()) {
                do {
                    Log.d(TAG, "getList: cursor");
                    listArray.add(cursor.getString(cursor.getColumnIndex("Option")));
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        return listArray;
    }

}
