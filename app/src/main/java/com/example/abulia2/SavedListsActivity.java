package com.example.abulia2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SavedListsActivity extends AppCompatActivity implements MVPComponents.ViewContract {
    private MVPComponents.SavedListsPresenterContract savedListsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_lists_activity);
        setupPresenter();
    }

    private void setupPresenter(){
        savedListsPresenter = new SavedListsPresenter(this);
    }

    public void startEditActivity(String listName){
        Intent intent = new Intent(this, EditListActivity.class);
        intent.putExtra("LIST_NAME", listName);
        startActivity(intent);
    }
}
