package com.example.abulia2;

import android.content.ContentValues;
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
        Log.d(TAG, "EditListModel: Created");
        p = presenter;
        p.getView();
        dbHelper = new ListContract.List.ListDbHelper(p.getView());
        listArray = new ArrayList<>();
    }

    public ArrayList<String> getList(String listName){
        Log.d(TAG, "getList: called, getting list " + listName + " dbhelper = " + dbHelper.toString());
        db = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.getTable(db, listName);
        if(cursor != null){
            if(cursor.moveToFirst()) {
                do {
                    Log.d(TAG, "getList: cursor, adding to listArray " + cursor.getString(cursor.getColumnIndex("Option")));
                    listArray.add(cursor.getString(cursor.getColumnIndex("Option")))
                    ;
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        return listArray;
    }

    public void updateList(ArrayList<String> listArray, String listName){
        Log.d(TAG, "updateList: called");
        try {
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        dbHelper.dropTableIfExists(db, listName);
        dbHelper.newTable(db, listName);
        for (int i = 0; i < listArray.size(); i++) {
            Log.d(getClass().getName(), "saveList() putting "
                    + listArray.get(i));
            values.put("Option", listArray.get(i));
            long newRowId = db.insert(listName, null, values);
        }
//        db.close();
        dbHelper.close();
    }

}
