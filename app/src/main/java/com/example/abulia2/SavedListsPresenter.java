package com.example.abulia2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    private SavedListsAdapter SavedListsAdapter;
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
        recyclerView.setAdapter(SavedListsAdapter = new SavedListsAdapter(listOfLists, viewSavedLists, this));
        addListsToView(listOfLists);
    }

    private void addListsToView(ArrayList<String> lists){
        Log.d(TAG, "addListsToView: called, lists size = " + lists.size());
       // SavedListsAdapter = new SavedListsAdapter(lists, viewSavedLists);
    }

    public void dropListTable(final String listName){
        AlertDialog.Builder alert = new AlertDialog.Builder(viewSavedLists);
        TextView alertTV = new TextView(viewSavedLists);
        alertTV.setTextSize(24);
        alertTV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        String confirm = "Delete list " + listName + "?";
        alertTV.setText(confirm);
        alertTV.setGravity(Gravity.CENTER_HORIZONTAL);
        alert.setView(alertTV);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Cancel
            }
        });
        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                modelSavedLists.dropTable(listName);
                int position = listOfLists.indexOf(listName);
                listOfLists.remove(position);
                recyclerView.removeViewAt(position);
                SavedListsAdapter.notifyItemRemoved(position);
                SavedListsAdapter.notifyItemRangeChanged(position, listOfLists.size());
                SavedListsAdapter.notifyDataSetChanged();
            }
        });

        final AlertDialog dialog = alert.create();
        dialog.show();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 5.0f;
        layoutParams.gravity = Gravity.CENTER; //this is layout_gravity
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setLayoutParams(layoutParams);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setLayoutParams(layoutParams);
    }

    @Override
    public void editList(String listName) {
        viewSavedLists.startEditActivity(listName);
    }

    @Override
    public void chooseFromList(String listName) {

    }

    @Override
    public SavedListsActivity getView() {
        return viewSavedLists;
    }
}
