package com.example.abulia2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

public class NewListModel implements MVPComponents.NewListModel {
    private ListContract.List.ListDbHelper dbHelper;
    public NewListModel(MVPComponents.NewListPresenterContract presenter){
       dbHelper  = new ListContract.List.ListDbHelper(presenter.getNewListView());
    }

    @Override
    public boolean saveListAsTable(String listName, ArrayList<EditText> optionList) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(dbHelper.newTable(db, listName)){
            Log.d(getClass().getName(), "dbHelper.newTables success");
        }
        for (int i = 0; i < optionList.size(); i++) {
            Log.d(getClass().getName(), "saveList() putting " + optionList.get(i));
            values.put("Option", optionList.get(i).getText().toString());
            long newRowId = db.insert(listName, null, values);
        }
        db.close();
        dbHelper.close();
        return false;

    }

}
