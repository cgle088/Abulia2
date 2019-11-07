package com.example.abulia2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EditListActivity extends AppCompatActivity implements MVPComponents.ViewContract {
    private static final String TAG = "EditListActivity";
    private MVPComponents.EditListPresenter p;
    private String listName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);
        listName = getIntent().getStringExtra("LIST_NAME");
        Log.d(TAG, "onCreate: called, this = " + this);
        p = new EditListPresenter(this, listName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.list_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_add_option){
            p.addOption();
        }else if(id == R.id.menu_save_list){
            p.updateList();

        }else{
            p.choose();
        }
        return super.onOptionsItemSelected(item);


    }
}
