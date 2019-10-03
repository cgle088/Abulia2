package com.example.abulia2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedListsAdapter extends RecyclerView.Adapter<SavedListsAdapter.viewHolder> {
    private static final String TAG = "SavedListsAdapter";
    private ArrayList<String> dataSet;
    private Context mContext;
    private static SavedListsPresenter presenter;

    public static class viewHolder extends RecyclerView.ViewHolder{
        LinearLayout parentLayout;
        private TextView tv;
        private Button cancelButton;
        private Button editButton;
        private Button chooseButton;

        private viewHolder(final View savedListView){
            super(savedListView);
            Log.d(TAG, "viewHolder created");
            tv = savedListView.findViewById(R.id.list_name);
            cancelButton = savedListView.findViewById(R.id.delete_list_button);
            editButton = savedListView.findViewById(R.id.edit_list_button);
            chooseButton = savedListView.findViewById(R.id.choose_from_list_button);
            cancelButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    presenter.dropListTable(tv.getText().toString());
                }
            });
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.editList(tv.getText().toString());
                }
            });
            chooseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.chooseFromList(tv.getText().toString());
                }
            });
            parentLayout = savedListView.findViewById(R.id.saved_list_parent);
        }
    }


    public SavedListsAdapter(ArrayList<String> myDataSet, Context context, SavedListsPresenter p){
        Log.d(TAG, "SavedListsAdapter created");
        dataSet = myDataSet;
        mContext = context;
        presenter = p;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.saved_list_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int pos){
        Log.d(TAG, "onBindViewHolder: called, adding " + dataSet.get(pos));
        holder.tv.setText(dataSet.get(pos));
    }

    @Override
    public int getItemCount(){
        Log.d(TAG, "getItemCount: called, count is "+ dataSet.size());
        return dataSet.size();
    }
}
