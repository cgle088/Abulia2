package com.example.abulia2;

import android.view.View;

public interface MVPComponents {

    //View
    interface ViewContract{}


    /**
     * Called when a new list is being created
     */
    interface NewListPresenterContract{
        void addOptionField();
        void choose();
        void saveListAs();
    }


    /**
     * Called when user wants to edit/choose from old Lists
     */
    interface SavedListsPresenterContract{}

    /**
     * For inserting new list into DB
     */
    interface NewListModel{}


    /**
     * For getting/updating saved Lists
     */
    interface SavedListsModel{}
}
