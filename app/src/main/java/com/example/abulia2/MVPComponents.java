package com.example.abulia2;

import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
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
        SavedListsActivity getView();
        void dropListTable(String listName);
        void editList(String listName);
        void chooseFromList(String listName);
    }

    /**
     * For editing lists that have been saved
     */
    interface EditListPresenter{
        EditListActivity getView();
        void removeOption(String option);
        void addOption();
        void updateList();
        void choose();

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
        void dropTable(String tableName);
    }

    /**
     * For editing lists that have been saved
     */
    interface EditListsModel{
        ArrayList<String> getList(String listName);
        void updateList(ArrayList<String> listArray, String listName);

    }
}
