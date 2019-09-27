package com.example.abulia2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MVPComponents.ViewContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToNewListView(View view){
        Intent intent = new Intent(this, NewListActivity.class);
        startActivity(intent);
    }

    public void switchToSavedListsView(View view){
        Intent intent = new Intent(this, SavedListsActivity.class);
        startActivity(intent);
    }

    public void switchToFiftyFiftyView(View view){
        Intent intent = new Intent(this, FiftyFiftyActivity.class);
        startActivity(intent);
    }

}
