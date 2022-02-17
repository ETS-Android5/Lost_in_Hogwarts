//Pablo Mateos García

package com.mateosgarciapablo.adventuregame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    //Elements of the layout

    private TextView titleTextView;
    private TextView infoTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Set up controls

        setUpControls();
    }

    private void setUpControls(){

        //Link variables with their layout elements

        titleTextView = findViewById(R.id.title_text_view_h);
        infoTextView = findViewById(R.id.info_text_view_h);
        backButton = findViewById(R.id.back_button_h);

        //Set the texts

        titleTextView.setText("Help");
        infoTextView.setText("To use this app, you should complete the task displayed on the screen.\n\n\n" +
                "You can move around the different floors using the UP / DOWN / RIGHT / LEFT buttons.\n\n\n" +
                "To collect an item, click on the item.\n\n\n" +
                "To drop an item, click on the item.\n\n\n" +
                "If you want to craft an item, click on the CRAFT button and then click on the items you want to craft. These items must be on your own inventory.\n\n\n" +
                "If you want to use an item, click on the USE button and then click on the item you want to use. This item must be on your own inventory. Besides, some items can be used only in a concrete level or in a concrete location.\n\n\n" +
                "If you have more question, you can contact me at:\n\n\n" +
                "S19001531@mail.glyndwr.ac.uk");

        //Set button

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                finish();
            }
        });
    }
}
