package com.example.abulia2;

import android.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedListsPresenter implements MVPComponents.SavedListsPresenterContract {
    private static final String TAG = "SavedListsPresenter";
    private SavedListsActivity viewSavedLists;
    private MVPComponents.SavedListsModel modelSavedLists;
    private ArrayList<String> listOfLists;
    private RecyclerView recyclerView;
    private rAdapter rAdapter;
    private RecyclerView.LayoutManager rManager;

    SavedListsPresenter(SavedListsActivity view){
        viewSavedLists = view;
        modelSavedLists = new SavedListsModel(this);
        listOfLists = modelSavedLists.getTablesList();
        for (int i = 0; i < listOfLists.size(); i++){
            Log.d(TAG, "SavedListsPresenter: tables are " + listOfLists.get(i));
        }

        recyclerView = viewSavedLists.findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);
        rManager = new LinearLayoutManager(viewSavedLists);
        recyclerView.setLayoutManager(rManager);
        Log.d(TAG, "SavedListsPresenter: new adapter");
        recyclerView.setAdapter(rAdapter = new rAdapter(listOfLists, viewSavedLists, this));
        addListsToView(listOfLists);
    }

    private void addListsToView(ArrayList<String> lists){
        Log.d(TAG, "addListsToView: called, lists size = " + lists.size());
       // rAdapter = new rAdapter(lists, viewSavedLists);
    }

    public void dropListTable(String listName){
        AlertDialog.Builder alert = new AlertDialog.Builder(viewSavedLists);
        TextView alertTV = new TextView(viewSavedLists);
        alertTV.setTextSize(24);
        alertTV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        String confirm = "Delete list " + listName + "?";
        alertTV.setText(confirm);
        alertTV.setGravity(Gravity.CENTER_HORIZONTAL);
        alert.setView(alertTV);
        alert.show();
    }

    @Override
    public SavedListsActivity getSavedListsView() {
        return viewSavedLists;
    }
}
