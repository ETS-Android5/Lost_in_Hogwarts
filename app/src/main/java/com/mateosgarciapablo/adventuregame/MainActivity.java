//Pablo Mateos García

package com.mateosgarciapablo.adventuregame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Elements of the layout

    static final int NO_EXIT = -1;
    static final int NUM_OF_LOCATIONS = 70;

    Location[] hogwarts;

    Player player;

    TextView titleTextView;
    TextView descriptionTextView;
    Button upButton;
    Button rightButton;
    Button downButton;
    Button leftButton;
    Button craftButton;
    Button useButton;
    ImageView imageView;

    Button playerInventory1;
    Button playerInventory2;
    Button playerInventory3;
    Button playerInventory4;
    Button playerInventory5;

    Button roomInventory1;
    Button roomInventory2;
    Button roomInventory3;
    Button roomInventory4;
    Button roomInventory5;

    Button saveButton;
    Button backButton;

    TextView roomInventoryTextView;
    TextView playerInventoryTextView;

    ArrayList<Integer> roomInventory;
    ArrayList<Integer> playerInventory;

    TextView infoTextView;

    int catLocation;

    boolean crafting;
    boolean use;
    int craft1;
    int craft2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHogwarts();

        Bundle intent = getIntent().getExtras();

        setupPlayer();

        if(intent!=null){ //Load game
            readXMLFile(1);
        }
        else{ //Create game
            readXMLFile(0);
        }

        setupControls();

        titleTextView.setText( hogwarts[player.getPlayerPos()].getDescription());
        descriptionTextView.setText( hogwarts[player.getPlayerPos()].getName());
        imageView.setImageResource(getResources().getIdentifier("l_" + player.getPlayerPos(), "drawable", getPackageName()));
        validDirections();

        roomInventory = new ArrayList<>();

        crafting = false;

        use = false;

        displayInventories();

        displayLevelInfo();

    }

    protected void displayInventories(){ //Display the inventoroies of the player and the room

        roomInventory = hogwarts[player.getPlayerPos()].getInventory();

        roomInventory1.setBackgroundResource(roomInventory.get(0));
        roomInventory2.setBackgroundResource(roomInventory.get(1));
        roomInventory3.setBackgroundResource(roomInventory.get(2));
        roomInventory4.setBackgroundResource(roomInventory.get(3));
        roomInventory5.setBackgroundResource(roomInventory.get(4));

        playerInventory = player.getInventory();

        playerInventory1.setBackgroundResource(playerInventory.get(0));
        playerInventory2.setBackgroundResource(playerInventory.get(1));
        playerInventory3.setBackgroundResource(playerInventory.get(2));
        playerInventory4.setBackgroundResource(playerInventory.get(3));
        playerInventory5.setBackgroundResource(playerInventory.get(4));

        checkInventoryEnabled();

    }

    protected void checkInventoryEnabled() { //Disable void item buttons

        if(roomInventory.get(0)==R.drawable.ic_void){
            roomInventory1.setEnabled(false);
        }
        else{
            roomInventory1.setEnabled(true);
        }
        if(roomInventory.get(1)==R.drawable.ic_void){
            roomInventory2.setEnabled(false);
        }
        else{
            roomInventory2.setEnabled(true);
        }
        if(roomInventory.get(2)==R.drawable.ic_void){
            roomInventory3.setEnabled(false);
        }
        else{
            roomInventory3.setEnabled(true);
        }
        if(roomInventory.get(3)==R.drawable.ic_void){
            roomInventory4.setEnabled(false);
        }
        else{
            roomInventory4.setEnabled(true);
        }
        if(roomInventory.get(4)==R.drawable.ic_void){
            roomInventory5.setEnabled(false);
        }
        else{
            roomInventory5.setEnabled(true);
        }

        if(playerInventory.get(0)==R.drawable.ic_void){
            playerInventory1.setEnabled(false);
        }
        else{
            playerInventory1.setEnabled(true);
        }
        if(playerInventory.get(1)==R.drawable.ic_void){
            playerInventory2.setEnabled(false);
        }
        else{
            playerInventory2.setEnabled(true);
        }
        if(playerInventory.get(2)==R.drawable.ic_void){
            playerInventory3.setEnabled(false);
        }
        else{
            playerInventory3.setEnabled(true);
        }
        if(playerInventory.get(3)==R.drawable.ic_void){
            playerInventory4.setEnabled(false);
        }
        else{
            playerInventory4.setEnabled(true);
        }
        if(playerInventory.get(4)==R.drawable.ic_void){
            playerInventory5.setEnabled(false);
        }
        else{
            playerInventory5.setEnabled(true);
        }
    }


    protected void validDirections() { //Check the movements allowed in your location

        if (hogwarts[player.getPlayerPos()].getUp() == NO_EXIT){
            upButton.setEnabled(false);
        }
        else{
            upButton.setEnabled(true);
        }
        if (hogwarts[player.getPlayerPos()].getRight() == NO_EXIT){
            rightButton.setEnabled(false);
        }
        else{
            rightButton.setEnabled(true);
        }
        if (hogwarts[player.getPlayerPos()].getDown() == NO_EXIT){
            downButton.setEnabled(false);
        }
        else{
            downButton.setEnabled(true);
        }
        if (hogwarts[player.getPlayerPos()].getLeft() == NO_EXIT){
            leftButton.setEnabled(false);
        }
        else{
            leftButton.setEnabled(true);
        }
    }

    protected void setupControls() {

        //Link with layout

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        imageView = findViewById(R.id.imageView);
        roomInventoryTextView = findViewById(R.id.roomInventoryTextView);
        playerInventoryTextView = findViewById(R.id.playerInventoryTextView);
        playerInventory1 = findViewById(R.id.playerInventory1);
        playerInventory2 = findViewById(R.id.playerInventory2);
        playerInventory3 = findViewById(R.id.playerInventory3);
        playerInventory4 = findViewById(R.id.playerInventory4);
        playerInventory5 = findViewById(R.id.playerInventory5);
        roomInventory1 = findViewById(R.id.roomInventory1);
        roomInventory2 = findViewById(R.id.roomInventory2);
        roomInventory3 = findViewById(R.id.roomInventory3);
        roomInventory4 = findViewById(R.id.roomInventory4);
        roomInventory5 = findViewById(R.id.roomInventory5);
        infoTextView = findViewById(R.id.infoTextView);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        craft1 = -1;
        craft2 = -1;

        catLocation = 20;
        catLocation = 20;

        //Functions of buttons

        upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hogwarts[player.getPlayerPos()].getUp() != NO_EXIT) {
                    player.setPlayerPos(hogwarts[player.getPlayerPos()].getUp());
                    titleTextView.setText(hogwarts[player.getPlayerPos()].getDescription());
                    descriptionTextView.setText(hogwarts[player.getPlayerPos()].getName());
                    imageView.setImageResource(getResources().getIdentifier("l_" + player.getPlayerPos(), "drawable", getPackageName()));
                    if (player.getLevel() == 2 && !player.getInventory().contains(R.drawable.ic_cat)) {
                        changeCatLocation();
                    }
                    validDirections();
                    displayInventories();
                }
            }
        });

        rightButton = findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hogwarts[player.getPlayerPos()].getRight() != NO_EXIT) {
                    player.setPlayerPos(hogwarts[player.getPlayerPos()].getRight());
                    titleTextView.setText(hogwarts[player.getPlayerPos()].getDescription());
                    descriptionTextView.setText(hogwarts[player.getPlayerPos()].getName());
                    imageView.setImageResource(getResources().getIdentifier("l_" + player.getPlayerPos(), "drawable", getPackageName()));
                    if (player.getLevel() == 2 && !player.getInventory().contains(R.drawable.ic_cat)) {
                        changeCatLocation();
                    }
                    validDirections();
                    displayInventories();
                }

            }
        });

        downButton = findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hogwarts[player.getPlayerPos()].getDown() != NO_EXIT) {
                    player.setPlayerPos(hogwarts[player.getPlayerPos()].getDown());
                    titleTextView.setText(hogwarts[player.getPlayerPos()].getDescription());
                    descriptionTextView.setText(hogwarts[player.getPlayerPos()].getName());
                    imageView.setImageResource(getResources().getIdentifier("l_" + player.getPlayerPos(), "drawable", getPackageName()));
                    if (player.getLevel() == 2 && !player.getInventory().contains(R.drawable.ic_cat)) {
                        changeCatLocation();
                    }
                    validDirections();
                    displayInventories();
                }

            }
        });

        leftButton = findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hogwarts[player.getPlayerPos()].getLeft() != NO_EXIT) {
                    player.setPlayerPos(hogwarts[player.getPlayerPos()].getLeft());
                    titleTextView.setText(hogwarts[player.getPlayerPos()].getDescription());
                    descriptionTextView.setText(hogwarts[player.getPlayerPos()].getName());
                    imageView.setImageResource(getResources().getIdentifier("l_" + player.getPlayerPos(), "drawable", getPackageName()));
                    if (player.getLevel() == 2 && !player.getInventory().contains(R.drawable.ic_cat)) {
                        changeCatLocation();
                    }
                    validDirections();
                    displayInventories();
                }
            }
        });

        craftButton = findViewById(R.id.craftButton);
        craftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crafting = true;

                infoTextView.setText("Select the two items you want to craft. Remember that they need to be on your own inventory.");

                craftButton.setEnabled(false);

            }
        });

        useButton = findViewById(R.id.useButton);
        useButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                use = true;

                infoTextView.setText("Select the item you want to use. Remember that it needs to be on your own inventory.");

                useButton.setEnabled(false);
            }
        });

        roomInventory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isInventoryFull()) {
                    player.addInventory(roomInventory.get(0));
                    hogwarts[player.getPlayerPos()].deleteInventory(0);
                    displayInventories();
                }
            }
        });

        roomInventory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isInventoryFull()) {
                    player.addInventory(roomInventory.get(1));
                    hogwarts[player.getPlayerPos()].deleteInventory(1);
                    displayInventories();
                }
            }
        });

        roomInventory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isInventoryFull()) {
                    player.addInventory(roomInventory.get(2));
                    hogwarts[player.getPlayerPos()].deleteInventory(2);
                    displayInventories();
                }
            }
        });

        roomInventory4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isInventoryFull()) {
                    player.addInventory(roomInventory.get(3));
                    hogwarts[player.getPlayerPos()].deleteInventory(3);
                    displayInventories();
                }
            }
        });

        roomInventory5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!player.isInventoryFull()) {
                    player.addInventory(roomInventory.get(4));
                    hogwarts[player.getPlayerPos()].deleteInventory(4);
                    displayInventories();
                }
            }
        });

        playerInventory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!crafting) {
                    if(use) {
                        checkUsable(playerInventory.get(0));
                    }
                    else {
                        if (!hogwarts[player.getPlayerPos()].isInventoryFull()) {
                            if (player.getLevel() == 2 && playerInventory.get(0) == R.drawable.ic_cat) {
                                catLocation = player.getPlayerPos();
                            }
                            hogwarts[player.getPlayerPos()].addInventory(playerInventory.get(0));
                            player.deleteInventory(0);
                            displayInventories();
                        }
                    }
                }
                else{
                    if (craft1 == -1) {
                        craft1 = playerInventory.get(0);
                    } else {
                        craft2 = playerInventory.get(0);
                        checkCrafteable();
                    }
                }
                checkLevelFinished();
            }
        });

        playerInventory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!crafting) {
                    if(use){
                        checkUsable(playerInventory.get(1));
                    }
                    else{
                        if (!hogwarts[player.getPlayerPos()].isInventoryFull()) {
                            if (player.getLevel() == 2 && playerInventory.get(1)==R.drawable.ic_cat) {
                                catLocation = player.getPlayerPos();
                            }
                            hogwarts[player.getPlayerPos()].addInventory(playerInventory.get(1));
                            player.deleteInventory(1);
                            displayInventories();
                        }
                    }
                }
                else{
                    if (craft1 == -1) {
                        craft1 = playerInventory.get(1);
                    } else {
                        craft2 = playerInventory.get(1);
                        checkCrafteable();
                    }
                }
                checkLevelFinished();
            }
        });

        playerInventory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!crafting) {
                    if(use){
                        checkUsable(playerInventory.get(2));
                    }
                    else{
                        if (!hogwarts[player.getPlayerPos()].isInventoryFull()) {
                            if (player.getLevel() == 2 && playerInventory.get(2)==R.drawable.ic_cat) {
                                catLocation = player.getPlayerPos();
                            }
                            hogwarts[player.getPlayerPos()].addInventory(playerInventory.get(2));
                            player.deleteInventory(2);
                            displayInventories();
                        }
                    }
                }
                else{
                    if (craft1 == -1) {
                        craft1 = playerInventory.get(2);
                    } else {
                        craft2 = playerInventory.get(2);
                        checkCrafteable();
                    }
                }
                checkLevelFinished();
            }
        });

        playerInventory4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!crafting) {
                    if(use){
                        checkUsable(playerInventory.get(3));
                    }
                    else{
                        if (!hogwarts[player.getPlayerPos()].isInventoryFull()) {
                            if (player.getLevel() == 2 && playerInventory.get(3)==R.drawable.ic_cat) {
                                catLocation = player.getPlayerPos();
                            }
                            hogwarts[player.getPlayerPos()].addInventory(playerInventory.get(3));
                            player.deleteInventory(3);
                            displayInventories();
                        }
                    }
                }
                else{
                    if (craft1 == -1) {
                        craft1 = playerInventory.get(3);
                    } else {
                        craft2 = playerInventory.get(3);
                        checkCrafteable();
                    }
                }
                checkLevelFinished();
            }
        });

        playerInventory5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!crafting) {
                    if(use){
                        checkUsable(playerInventory.get(4));
                    }
                    else {
                        if (!hogwarts[player.getPlayerPos()].isInventoryFull()) {
                            if (player.getLevel() == 2 && playerInventory.get(4) == R.drawable.ic_cat) {
                                catLocation = player.getPlayerPos();
                            }
                            hogwarts[player.getPlayerPos()].addInventory(playerInventory.get(4));
                            player.deleteInventory(4);
                            displayInventories();
                        }
                    }
                }
                else{
                    if (craft1 == -1) {
                        craft1 = playerInventory.get(4);
                    } else {
                        craft2 = playerInventory.get(4);
                        checkCrafteable();
                    }
                }
                checkLevelFinished();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savetoXML();
                Toast.makeText(getApplicationContext(),"Saved successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    protected void setupPlayer() { //Initialise player

        player = new Player();
    }

    protected void initHogwarts() { //Initialise hogwarts

        hogwarts = new Location[NUM_OF_LOCATIONS];
        for (int pos = 0; pos < NUM_OF_LOCATIONS; pos++){
            hogwarts[pos] = new Location();
        }
    }

    protected void readXMLFile(int value) {
        try {
            XmlPullParser xpp;
            if(value==1){ //Read from saved XML - Load game
                FileInputStream input = new FileInputStream(new File(getFilesDir() + File.separator +"game.xml"));
                xpp = Xml.newPullParser();
                xpp.setInput(new InputStreamReader(input));
                xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
            }
            else{ //Read from resource XML - Create game
                xpp = getResources().getXml(R.xml.hogwarts);
            }
            xpp.next();
            int eventType = xpp.getEventType();
            int room_count = 0;
            String elemtext = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String elemName = xpp.getName();
                    if (elemName.equals("room")){
                        room_count = room_count + 1;
                    }
                    if (elemName.equals("up")){
                        elemtext = "up";
                    }
                    if (elemName.equals("right")){
                        elemtext = "right";
                    }
                    if (elemName.equals("down")){
                        elemtext = "down";
                    }
                    if (elemName.equals("left")){
                        elemtext = "left";
                    }
                    if (elemName.equals("description")){
                        elemtext = "description";
                    }
                    if (elemName.equals("name")){
                        elemtext = "name";
                    }
                    if(elemName.equals("item")){
                        elemtext = "item";
                    }
                    if(elemName.equals("position")){
                        elemtext = "position";
                    }
                    if(elemName.equals("level")){
                        elemtext = "level";
                    }
                    if(elemName.equals("item_user")){
                        elemtext = "item_user";
                    }
                }
                else if (eventType == XmlPullParser.TEXT) {
                    if (elemtext.equals("up")) {
                        hogwarts[room_count-1].setUp( Integer.parseInt(xpp.getText()));
                    }
                    else if (elemtext.equals("right")) {
                        hogwarts[room_count-1].setRight(Integer.parseInt(xpp.getText()));
                    }
                    else if (elemtext.equals("down")) {
                        hogwarts[room_count-1].setDown(Integer.parseInt(xpp.getText()));
                    }
                    else if (elemtext.equals("left")) {
                        hogwarts[room_count-1].setLeft(Integer.parseInt(xpp.getText()));
                    }
                    else if (elemtext.equals("description")) {
                        hogwarts[room_count-1].setDescription( xpp.getText() );
                    }
                    else if (elemtext.equals("name")) {
                        hogwarts[room_count-1].setName( xpp.getText() );
                    }
                    else if(elemtext.equals("item")){
                        hogwarts[room_count-1].addInventory(obtainResource(xpp.getText()));
                    }
                    else if(elemtext.equals("position")){
                        player.setPlayerPos(Integer.parseInt(xpp.getText()));
                    }
                    else if(elemtext.equals("level")){
                        player.setLevel(Integer.parseInt(xpp.getText()));
                    }
                    else if(elemtext.equals("item_user")){
                        player.addInventory(obtainResource(xpp.getText()));
                    }
                }
                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException e) { }
        catch (IOException e) { }
    }

    protected void savetoXML() { //Save to XML to be able to load later

        try {
            String filename = "game.xml";

            File file = new File(getFilesDir() + File.separator +filename);
            file.delete();

            FileOutputStream fos;
            fos = openFileOutput(filename,Context.MODE_APPEND);

            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, Boolean.TRUE);

            serializer.startTag(null, "hogwarts");

            for(Location location:hogwarts) {
                if (location != null) {
                    serializer.startTag(null, "room");

                    if(location.getRight()!=-1){
                        serializer.startTag(null, "right");
                        serializer.text(String.valueOf(location.getRight()));
                        serializer.endTag(null, "right");
                    }

                    if(location.getLeft()!=-1){
                        serializer.startTag(null, "left");
                        serializer.text(String.valueOf(location.getLeft()));
                        serializer.endTag(null, "left");
                    }

                    if(location.getUp()!=-1){
                        serializer.startTag(null, "up");
                        serializer.text(String.valueOf(location.getUp()));
                        serializer.endTag(null, "up");
                    }

                    if(location.getDown()!=-1){
                        serializer.startTag(null, "down");
                        serializer.text(String.valueOf(location.getDown()));
                        serializer.endTag(null, "down");
                    }

                    serializer.startTag(null, "description");
                    serializer.text(String.valueOf(location.getDescription()));
                    serializer.endTag(null, "description");

                    serializer.startTag(null, "name");
                    serializer.text(String.valueOf(location.getName()));
                    serializer.endTag(null, "name");

                    for(int item: location.getInventory()){
                        String name = getResources().getResourceEntryName(item).replace("ic_","");
                        if(!name.equals("void")){
                            serializer.startTag(null, "item");
                            serializer.text(String.valueOf(name));
                            serializer.endTag(null, "item");
                        }
                    }
                    serializer.endTag(null, "room");
                }
            }

            serializer.startTag(null, "player");

            serializer.startTag(null, "position");

            serializer.text(String.valueOf(player.getPlayerPos()));

            serializer.endTag(null, "position");

            serializer.startTag(null, "level");

            serializer.text(String.valueOf(player.getLevel()));

            serializer.endTag(null, "level");

            serializer.startTag(null, "inventory");

            for(int item:player.getInventory()){
                String name = getResources().getResourceEntryName(item).replace("ic_","");
                if(!name.equals("void")){
                    serializer.startTag(null, "item_user");
                    serializer.text(String.valueOf(name));
                    serializer.endTag(null, "item_user");
                }
            }

            serializer.endTag(null, "inventory");

            serializer.endTag(null, "player");

            serializer.endTag(null, "hogwarts");

            serializer.endDocument();
            serializer.flush();
            fos.close();
        }
        catch (IOException e) { }
    }

    protected int obtainResource(String item) { //get the resource int ID by the item icon name of the XML

        String itemIcon = "ic_" + item;
        return getResources().getIdentifier(itemIcon, "drawable",getPackageName());
    }

    protected void checkCrafteable() { //Check craft combinations

        if((craft1==R.drawable.ic_blood && craft2==R.drawable.ic_frog) || (craft1==R.drawable.ic_frog && craft2==R.drawable.ic_blood)){
            player.deleteItem(craft1);
            player.deleteItem(craft2);
            player.addInventory(R.drawable.ic_bowl);
        }
        else if((craft1==R.drawable.ic_bowl && craft2==R.drawable.ic_pot) || (craft1==R.drawable.ic_pot && craft2==R.drawable.ic_bowl)){
            player.deleteItem(craft1);
            player.deleteItem(craft2);
            player.addInventory(R.drawable.ic_potion);
        }
        else if((craft1==R.drawable.ic_sheets && craft2==R.drawable.ic_pencil) || (craft1==R.drawable.ic_pencil && craft2==R.drawable.ic_sheets)){
            player.deleteItem(craft1);
            player.deleteItem(craft2);
            player.addInventory(R.drawable.ic_book);
        }
        else if((craft1==R.drawable.ic_music && craft2==R.drawable.ic_brush) || (craft1==R.drawable.ic_brush && craft2==R.drawable.ic_music)){
            player.deleteItem(craft1);
            player.deleteItem(craft2);
            player.addInventory(R.drawable.ic_trophy);
        }
        else{
            Toast.makeText(getApplicationContext(),"The selected items are not crafteable.", Toast.LENGTH_SHORT).show();
        }
        crafting = false;
        craftButton.setEnabled(true);
        displayInventories();
        displayLevelInfo();
        craft1 = -1;
        craft2 = -1;
        checkLevelFinished();
    }

    protected void checkUsable(int item) { //Check use combinations

        if(item == R.drawable.ic_wardrobe && player.getPlayerPos() == 19 && player.getLevel()==4){
            player.deleteItem(item);
            player.addInventory(R.drawable.ic_spider);
        }
        else if(item == R.drawable.ic_mirror && player.getPlayerPos() == 47 && player.getLevel()==6){
            player.deleteItem(item);
            player.addInventory(R.drawable.ic_key1);
        }
        else if(item == R.drawable.ic_key1 && player.getPlayerPos() == 46 && player.getLevel()==6){
            player.deleteItem(item);
            hogwarts[50].addInventory(R.drawable.ic_key1);
        }
        else{
            Toast.makeText(getApplicationContext(),"You cannot use this item in this location or in this level.", Toast.LENGTH_SHORT).show();
        }
        use = false;
        useButton.setEnabled(true);
        displayInventories();
        displayLevelInfo();
        checkLevelFinished();
    }

    protected void finishLevel() { //Unlock new level, that is, setting access to upper floors (set Up direction)

        int level = player.getLevel();
        switch(level){
            case 1:{
                hogwarts[1].setUp(12);
                hogwarts[6].setUp(20);
                hogwarts[8].setUp(23);
            }
                break;
            case 2:{
                hogwarts[11].setUp(24);
                hogwarts[12].setUp(29);
                hogwarts[13].setUp(28);
            }
            break;
            case 3:{
                hogwarts[28].setUp(36);
                hogwarts[35].setUp(37);
            }
            break;
            case 4:{
                hogwarts[36].setUp(40);
            }
            break;
            case 5:{
                hogwarts[41].setUp(45);
                hogwarts[42].setUp(46);
            }
            break;
            case 6:{
                hogwarts[46].setUp(51);
            }
            break;
            case 7:{
                hogwarts[51].setUp(59);
            }
            break;
            case 8:{
                hogwarts[54].setUp(60);
                hogwarts[55].setUp(62);
                hogwarts[56].setUp(67);
                hogwarts[59].setUp(68);
            }
            break;
        }
        player.nextLevel();
        validDirections();
        Toast.makeText(getApplicationContext(), "Level " + player.getLevel() + " unlocked.", Toast.LENGTH_SHORT).show();
        displayLevelInfo();
    }

    protected void displayLevelInfo() { //Writes the task of the level on the text area

        int level = player.getLevel();
        String text ="";
        switch(level){
            case 1:{
                text = "Create your first potion. Find dragon's blood and frog's legs, go to the kitchen and use a pot and the craft button to make it.";
            }
            break;
            case 2:{
                text = "We are starving. Bring the turkey to the Great Hall. After that, find Mrs. Norris (Argus Filch's cat) and bring her to his office (Caretaker's Office). She likes to move around the castle.";
            }
            break;
            case 3:{
                text = "The hospital needs the potion you made to use it as a remedy. Give it to them. After that, help the matron. She is looking for a pacifier.";
            break;
            }
            case 4:{
                text = "The wardrobe of the Staffroom contains a Boggart, creature that takes on the form of its observer's most fear. If you use the use button, you can see your biggest fear and bring it to the Defence Against the Dark Arts Professor's Office.";
            }
            break;
            case 5:{
                text = "Professor Snape, the Head of Slytherin, wants to write a potion's book. Bring him a pencil to his office and then make a book with his notes. After that, don't forget to bring it to the library.";
            }
            break;
            case 6:{
                text = "In the Storage Room you can find the Mirror of Erised. It gives you what you desire the most. It may give you the key to open the door of the Study Area and go to the next floor.";
            }
            break;
            case 7:{
                text = "Create an Artist Trophy combining music and draw skills and put it in the Trophy Room.";
            }
            break;
            case 8:{
                text = "Someone is looking for the Deathly Hallows (wand, stone and cloak). Collect them and put them in the Room of Rewards to secure them.";
            }
            break;
            case 9:{
                text = "Each Hogwarts' house (Gryffindor, Slytherin, Ravenclaw and Hufflepuff) has an animal that represent it. Find them on each common room and please, put them in the Room of Requirements";
            }
            break;
            case 10:{
                text = "GAME OVER. CONGRATULATIONS!";
            }
            break;
        }
        infoTextView.setText(text);
    }

    protected void checkLevelFinished() { //Check if the task to unlock the next level has been reached

        int level = player.getLevel();
        switch(level){
            case 1:{
                if(player.getPlayerPos()==8 && player.getInventory().contains(R.drawable.ic_potion)){
                    finishLevel();
                }
            }
                break;
            case 2:{
                if(hogwarts[20].getInventory().contains(R.drawable.ic_turkey) && hogwarts[18].getInventory().contains(R.drawable.ic_cat)){
                    finishLevel();
                }
            }
            break;
            case 3:{
                if(hogwarts[32].getInventory().contains(R.drawable.ic_potion) && hogwarts[31].getInventory().contains(R.drawable.ic_pacifier)){
                    finishLevel();
                }
            }
                break;
            case 4:{
                if(hogwarts[37].getInventory().contains(R.drawable.ic_spider)){
                    finishLevel();
                }
            }
                break;
            case 5:{
                if(hogwarts[41].getInventory().contains(R.drawable.ic_book)){
                    finishLevel();
                }
            }
            break;
            case 6:{
                if(player.getPlayerPos()==46 && hogwarts[50].getInventory().contains(R.drawable.ic_key1)){
                    finishLevel();
                }
            }
            break;
            case 7:{
                if(hogwarts[43].getInventory().contains(R.drawable.ic_trophy)){
                    finishLevel();
                }
            }
            break;
            case 8:{
                if(hogwarts[59].getInventory().contains(R.drawable.ic_cloak) && hogwarts[59].getInventory().contains(R.drawable.ic_wand) && hogwarts[59].getInventory().contains(R.drawable.ic_stone)){
                    finishLevel();
                }
            }
            break;
            case 9:{
                if(hogwarts[61].getInventory().contains(R.drawable.ic_lion) && hogwarts[61].getInventory().contains(R.drawable.ic_snake) && hogwarts[61].getInventory().contains(R.drawable.ic_eagle) && hogwarts[61].getInventory().contains(R.drawable.ic_badger)){
                    player.nextLevel();
                    displayLevelInfo();
                }
            }
            break;
        }
    }

    protected void changeCatLocation() { //In level 2, you have to find a cat. This function changes the location of the cat randomly to make the finding process more interesting

        int newLocation = 18;
        while(newLocation==18 || hogwarts[newLocation].isInventoryFull()){
            newLocation = new Random().nextInt(24);
        }
        hogwarts[catLocation].deleteItem(R.drawable.ic_cat);
        hogwarts[newLocation].addInventory(R.drawable.ic_cat);
        catLocation = newLocation;
    }

}
