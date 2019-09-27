package com.example.abulia2;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

//This class is meant to create text fields and buttons for the user to enter potential choices into
public class NewListPresenter implements MVPComponents.NewListPresenterContract {

    private NewListActivity viewNewList;
    private MVPComponents.NewListModel modelNewList;
    private ArrayList<EditText> optionList = new ArrayList<>();
    //The number of options to choose from will never be less than 2
    private int optionCount = 0;
    private int optionID = 0;


    public NewListPresenter(NewListActivity view){
        viewNewList = view;
        modelNewList = new NewListModel(this);
        addOptionField();
        addOptionField();
    }


     public void choose(){
         Random r = new Random();
         int textId = r.nextInt(optionCount);
         Log.d(getClass().getName(), "chooseOption() textView id = " + textId + " editTextCount = " + optionCount);
         EditText chosenEditText = optionList.get(textId);
         String chosenOption = chosenEditText.getText().toString();
         showChoice(chosenOption);
    }

    public void saveListAs(){

    }

    public void addOptionField(){
        if(optionCount < 10) {
            EditText et = new EditText(viewNewList);

            LinearLayout parentContainer = viewNewList.findViewById(R.id.optionLayout);
            LinearLayout optionContainer = new LinearLayout(viewNewList);

            et.setHint("Option");
            et.setLayoutParams(new ViewGroup.LayoutParams(400, 100));

            optionContainer.setId(optionID);
            optionID++;
            optionCount++;
            optionContainer.setOrientation(LinearLayout.HORIZONTAL);
            optionContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            optionList.add(et);
            optionContainer.addView(et);
            optionContainer.addView(createCancelButton(parentContainer, optionContainer, et));
            parentContainer.addView(optionContainer);
        }else{
            alert("Maximum Options Reached");
        }
    }

    private ImageButton createCancelButton(final LinearLayout parent, final LinearLayout container, final EditText option ){
        final ImageButton cancelButton = new ImageButton(viewNewList);
        cancelButton.setId(optionID);
        cancelButton.setImageDrawable(viewNewList.getApplicationContext()
                .getResources().getDrawable(R.drawable.ic_close));
        cancelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //disallow less than two options
                if(optionCount > 2) {
                    Log.d(getClass().getName(), "createCancelButton() Deleting option " + option.getText());
                    optionList.remove(option);

                    for (int i = 0; i < optionList.size(); i++) {
                        Log.d(getClass().getName(), "createCancelButton() List[" + i + "] = " + optionList.get(i).getText().toString());
                    }
                    parent.removeView(container);
                    optionCount--;
                }
            }
        });
        return cancelButton;
    }
    /**
     * Alerts the user if something is awry
     * @param alertMsg what the alert should say
     */
    private void alert(String alertMsg){
        AlertDialog.Builder aDialog = new AlertDialog.Builder(viewNewList);
        TextView alert = new TextView(viewNewList);
        alert.setTextSize(24);
        alert.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        alert.setText(alertMsg);
        alert.setGravity(Gravity.CENTER_HORIZONTAL);
        aDialog.setView(alert);
        aDialog.show();
    }

    public void showChoice(String choice){
        Log.d(getClass().getName(), "Choice is " + choice);
        final PopupWindow choicePopUp = new PopupWindow(viewNewList);
        final LinearLayout ll = new LinearLayout(viewNewList);
        ll.setOrientation(LinearLayout.VERTICAL);
        ActionMenuItemView popUpButton =  viewNewList.findViewById(R.id.menu_choose);

        TextView choiceTV = new TextView(viewNewList);

        choiceTV.setText(choice);
        choiceTV.setTextSize(35);
        choiceTV.setGravity(Gravity.CENTER_HORIZONTAL);
        ll.addView(choiceTV);
        choicePopUp.setContentView(ll);
        choicePopUp.update();
        choicePopUp.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        choicePopUp.setOutsideTouchable(true);
        choicePopUp.setFocusable(true);
        choicePopUp.showAtLocation(ll, Gravity.CENTER_HORIZONTAL, 10, 10);

    }



}
