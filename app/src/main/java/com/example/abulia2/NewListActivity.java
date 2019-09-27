package com.example.abulia2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewListActivity extends AppCompatActivity implements MVPComponents.ViewContract {

    private MVPComponents.NewListPresenterContract newListPresenter;
    private androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_list_activity);
//        toolbar = findViewById(R.id.new_list_app_bar);
//        setSupportActionBar(toolbar);
        setupPresenter();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.new_list_toolbar, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu_add_option){
            newListPresenter.addOptionField();
        }
        if(id == R.id.menu_choose){
            Log.d(getClass().getName(), "Choosing choice");
            newListPresenter.choose();
            return true;
        }
        if(id == R.id.menu_save_list){
            newListPresenter.saveListAs();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setupPresenter(){
        newListPresenter = new NewListPresenter(this);
    }

    void newTV(){
        TextView newTV = new TextView(this);
    }

}
