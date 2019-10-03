package com.example.abulia2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class EditListActivity extends AppCompatActivity implements MVPComponents.ViewContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        String listName = getIntent().getStringExtra("LIST_NAME");
        MVPComponents.EditListPresenter p = new EditListPresenter(this, listName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.list_toolbar, menu);
        return true;
    }
}
