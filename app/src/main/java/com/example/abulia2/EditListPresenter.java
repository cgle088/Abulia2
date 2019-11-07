package com.example.abulia2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditListPresenter implements MVPComponents.EditListPresenter {
    private static final String TAG = "EditListPresenter";
    private String listName;
    private int optionPosition;
    private ArrayList<String> listOptions;
    private EditListActivity viewEditList;
    private EditListModel modelEditList;
    private ListView listView;
    private EditListAdapter adapter;

    EditListPresenter(EditListActivity view, String name){
        Log.d(TAG, "EditListPresenter: created, this = " + this);
        viewEditList = view;
        modelEditList = new EditListModel(this);
        listOptions = modelEditList.getList(name);
        listView = view.findViewById(R.id.edit_list_view);
        adapter = new EditListAdapter(viewEditList, listOptions);
        listView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(viewEditList);
        final EditText input = new EditText(viewEditList);

        alert.setTitle("Save List As...");
        alert.setMessage("Enter new list name:");
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if(!input.getText().toString().isEmpty()) {
                    listName = input.getText().toString();
                    Log.d(getClass().getName(), "listName = " + listName);
                    removeEmptyOptions();
                    modelEditList.updateList(listOptions, listName);
                }else{
                    alert("Enter valid name");
                }
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Cancel
            }
        });
        alert.show();
    }
    private void removeEmptyOptions(){
        Log.d(TAG, "removeEmptyOptions() printing list before:");
        for(int i =0; i <listOptions.size(); i++) {
            Log.d(TAG, "removeEmptyOptions: " + listOptions.get(i));
        }
        for(int i = 0; i < listOptions.size(); i++){
            Log.d(TAG, "allOptionsFilled() optionList.toString = " + listOptions.get(i));
            if(TextUtils.isEmpty(listOptions.get(i)) ){
                String emptyOption = listOptions.get(i);
                listOptions.remove(i);
                i--;
            }
        }
    }

    @Override
    public void choose(){

    }

//    @Override
    public EditListActivity getView() {
        if(viewEditList!=null) {
            Log.d(TAG, "getView: called, viewEditList not null");
            return viewEditList;
        }
        else
            Log.d(TAG, "getView: called, viewEditList IS NULL");
            return null;
    }



    @Override
    public void removeOption(String option) {
        listOptions.remove(option);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addOption() {
        listOptions.add("");
        adapter.notifyDataSetChanged();
    }
    /**
     * Alerts the user if something is awry
     * @param alertMsg what the alert should say
     */
    private void alert(String alertMsg){
        AlertDialog.Builder aDialog = new AlertDialog.Builder(viewEditList);
        TextView alert = new TextView(viewEditList);
        alert.setTextSize(24);
        alert.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        alert.setText(alertMsg);
        alert.setGravity(Gravity.CENTER_HORIZONTAL);
        aDialog.setView(alert);
        aDialog.show();
    }
}
