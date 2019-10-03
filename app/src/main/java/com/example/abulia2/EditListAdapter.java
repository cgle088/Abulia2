package com.example.abulia2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditListAdapter extends RecyclerView.Adapter<EditListAdapter.viewHolder>{
    private static final String TAG = "EditListAdapter";
    private Context mContext;
    private ArrayList<String>dataSet;
    private static EditListPresenter presenter;

        public static class viewHolder extends RecyclerView.ViewHolder{
            LinearLayout parentLayout;
            private EditText et;
            private ImageView xButton;

            private viewHolder(final View editListActivity){
                super(editListActivity);
                et = editListActivity.findViewById(R.id.optionText);
                xButton = editListActivity.findViewById(R.id.removeButton);
                xButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.removeOption(et.getText().toString());
                    }
                });
            }
        }
    EditListAdapter(ArrayList<String> listSet, Context context, EditListPresenter p){
            dataSet = listSet;
            mContext = context;
            presenter = p;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.option_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.et.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}