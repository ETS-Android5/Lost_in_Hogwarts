//Pablo Mateos García

package com.mateosgarciapablo.adventuregame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;

public class MainMenuActivity extends AppCompatActivity {

    //Elements of the layout

    Button newGameButton;
    Button loadGameButton;
    Button helpButton;
    Button aboutButton;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Set up controls

        setupControls();
    }

    protected void setupControls() {

        //Define and add functions to buttons

        newGameButton = findViewById(R.id.newGame_button);
        newGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        loadGameButton = findViewById(R.id.loadGame_button);
        loadGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                File file = new File(getFilesDir() + File.separator + "game.xml");
                if(file.exists()){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("load", "1");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"There is not game saved.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        helpButton = findViewById(R.id.help_button);
        helpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
}