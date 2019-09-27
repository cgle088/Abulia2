package com.example.abulia2;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

public class rAdapter extends RecyclerView.Adapter<rAdapter.viewHolder> {
    private String[] dataSet;

    public static class viewHolder extends RecyclerView.ViewHolder{
        public EditText editText;
        public Button cButton;
        public viewHolder(EditText et, Button button){
            super(et);
            editText = et;
            cButton = button;
        }
    }

    public rAdapter(String[] myDataSet){
        dataSet = myDataSet;
    }

    public rAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        EditText et = (EditText) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_list_activity, parent, false);
        Button butt = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_list_activity, parent, false);

        viewHolder vh = new viewHolder(et, butt);
        return vh;
    }

    public void onBindViewHolder(viewHolder holder, int pos){

    }
    public int getItemCount(){
        return dataSet.length;
    }
}
