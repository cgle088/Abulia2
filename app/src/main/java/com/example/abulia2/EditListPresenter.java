package com.example.abulia2;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditListPresenter implements MVPComponents.EditListPresenter {
    private static final String TAG = "EditListPresenter";
    private ArrayList<String> listOptions;
    private EditListActivity viewEditList;
    private EditListModel modelEditList;
    private RecyclerView recyclerView;
    private EditListAdapter adapter;
    private RecyclerView.LayoutManager manager;


    EditListPresenter(EditListActivity view, String listName){
        viewEditList = view;
        modelEditList = new EditListModel(this);
        listOptions = modelEditList.getList(listName);
        recyclerView = viewEditList.findViewById(R.id.edit_list_r_view);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(viewEditList);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter = new EditListAdapter(listOptions, viewEditList, this));

    }

    @Override
    public EditListActivity getView() {
        return viewEditList;
    }

    @Override
    public void removeOption(String option) {
        Log.d(TAG, "removeOption: called");
        listOptions.remove(option);
    }
}
