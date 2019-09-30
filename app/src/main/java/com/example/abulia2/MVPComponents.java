package com.example.abulia2;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public interface MVPComponents {

    //View
    interface ViewContract{}


    /**
     * Called when a new list is being created
     */
    interface NewListPresenterContract{
        NewListActivity getNewListView();
        void addOptionField();
        void choose();
        void saveListAs();
    }


    /**
     * Called when user wants to edit/choose from old Lists
     */
    interface SavedListsPresenterContract{
        SavedListsActivity getSavedListsView();
        void dropListTable(String listName);
    }

    /**
     * For inserting new list into DB
     */
    interface NewListModel{
        boolean saveListAsTable(String listName, ArrayList<EditText> optionList);
    }


    /**
     * For getting/updating saved Lists
     */
    interface SavedListsModel{
        ArrayList<String> getTablesList();
    }
}
