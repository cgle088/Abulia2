package com.example.abulia2;

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
}
