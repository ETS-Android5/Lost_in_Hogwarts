//Pablo Mateos García

package com.mateosgarciapablo.adventuregame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    //Elements of the layout

    private TextView titleTextView;
    private TextView infoTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Set up controls

        setUpControls();
    }

    private void setUpControls() {

        //Link variables with their layout elements

        titleTextView = findViewById(R.id.title_text_view);
        infoTextView = findViewById(R.id.info_text_view);
        backButton = findViewById(R.id.back_button_a);

        //Set the texts

        titleTextView.setText("About this app");
        infoTextView.setText("Welcome to Lost to Hogwarts.\n\n\n" +
                "Move around the castle collecting and crafting items.\n\n\n" +
                "You can unlock new floors of the castle by completing the task of each level. The castle has 10 floors and 70 locations.\n\n\n" +
                "If you have any doubt about the app, please click on the help button.");

        //Set button

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                finish();
            }
        });
    }
}
