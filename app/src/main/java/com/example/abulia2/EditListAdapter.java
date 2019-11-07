package com.example.abulia2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EditListAdapter extends BaseAdapter{
    final static String TAG = "EditListAdapter";
    Context mContext;
    ArrayList<String> optionList;
    LayoutInflater inf;

    public EditListAdapter(Context context, ArrayList<String> options){
        this.mContext = context;
        this.optionList = options;
        inf = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return optionList.size();
    }

    @Override
    public Object getItem(int i) {
        return optionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inf.inflate(R.layout.option_layout, null);
        final EditText et = view.findViewById(R.id.option_text);
        ImageView iv = view.findViewById(R.id.remove_option_button);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: removing" + et.getText().toString()
                        +". Size is " + optionList.size() + ". New list is: ");
                et.setOnFocusChangeListener(null);
                optionList.remove(et.getText().toString());
                notifyDataSetChanged();
                for (int i=0; i < optionList.size(); i++){
                    Log.d(TAG, "onClick list size = "+ optionList.size() +" " +  optionList.get(i));
                }
            }
        });
        if(optionList.get(i)!=null) {
            et.setText(optionList.get(i));
            //final String origString = et.getText().toString();
        }

       // addTextChangedListener(et);
        addOnFocusChangeListener(et);
        return view;
    }
    private void addOnFocusChangeListener(final EditText et){
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            String origString;
            @Override
            public void onFocusChange(View view, boolean b) {
                String newString;
                /*
                * If the EditText is focused, save the string to remove it from ArrayList
                * */
                if(b){
                    origString = et.getText().toString();
                }
                /*
                * If EditText is unfocused, save the new text and remove the old from ArrayList
                * */
                if(!b){
                    if(optionList.contains(origString)){
                        newString = et.getText().toString();
                        int index = optionList.indexOf(origString);
                        optionList.set(index, newString);
                        //origString = et.getText().toString();
                    }else{
                        newString = et.getText().toString();
                        optionList.add(newString);
                    }
                    Log.d(TAG, "afterTextChanged: added " + newString + ". list is now ");
                    for (int i=0; i < optionList.size(); i++){
                        Log.d(TAG, " " + optionList.get(i));
                    }
//                            origString = et.getText().toString();
                    Log.d(TAG, "afterTextChanged focuListener: origString = " + origString);
                    et.setText(newString);
                }
            }
        });

    }

    private void addTextChangedListener(final EditText et){
        final String origString = et.getText().toString();
        et.addTextChangedListener(new TextWatcher() {
            boolean _ignore = false;
            String newString, oldString;

            //= et.getText().toString();

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                //origString = et.getText().toString();
                Log.d(TAG, "beforeTextChanged: origString = " + origString);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newString = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;

                _ignore = true;

//                et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View view, boolean b) {
////                        setOriginalString();
//                        if(!b){
//                            if(optionList.contains(origString)){
//                                int index = optionList.indexOf(origString);
//                                optionList.set(index, newString);
//                                //origString = et.getText().toString();
//                            }else{
//                                optionList.add(newString);
//                            }
//                            Log.d(TAG, "afterTextChanged: added " + newString + ". list is now ");
//                            for (int i=0; i < optionList.size(); i++){
//                                Log.d(TAG, optionList.get(i));
//                            }
////                            origString = et.getText().toString();
//                            Log.d(TAG, "afterTextChanged focuListener: origString = " + origString);
//                            et.setText(newString);
//
//                            //int index = optionList.indexOf(origString);
//                            //optionList.set(index, newString);
//                        }
//                    }
//                });
                _ignore = false;
            }

        });
    }

    public View newOptions(){
        View view = inf.inflate(R.layout.option_layout, null);
        final EditText et = view.findViewById(R.id.option_text);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                optionList.remove(et.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                optionList.add(et.getText().toString());
            }
        });
        ImageView iv = view.findViewById(R.id.remove_option_button);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionList.remove(et.getText().toString());
                notifyDataSetChanged();
            }
        });

        return view;
    }
}