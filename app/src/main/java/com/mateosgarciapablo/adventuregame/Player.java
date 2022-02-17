//Pablo Mateos García

package com.mateosgarciapablo.adventuregame;

import java.util.ArrayList;

public class Player {

    //Elements of the player

    private int playerPos;
    private ArrayList<Integer> inventory;
    private boolean inventoryFull;
    private int level;

    public Player() {

        //Initial set up

        playerPos = 0;
        inventory = new ArrayList<>();
        inventoryFull = false;
        level = 1;
    }

    //Getters and setters

    public int getPlayerPos() {

        return playerPos;
    }

    public void setPlayerPos(int playerPos) {

        this.playerPos = playerPos;
    }

    public ArrayList<Integer> getInventory() {

        ArrayList<Integer> newInventory = new ArrayList<>();
        newInventory.addAll(inventory);
        while(newInventory.size()<5){
            newInventory.add(R.drawable.ic_void);
        }
        return newInventory;
    }

    public boolean isInventoryFull() {

        return inventoryFull;
    }


    public int getLevel() {

        return level;
    }

    public void setLevel(int level) {

        this.level = level;
    }

    //Go to next level

    public void nextLevel() {

        setLevel(getLevel() + 1);
    }

    //Add and delete functions

    public void deleteItem(int item) {

        inventory.remove((Object) item);
        inventoryFull = false;
    }

    public void addInventory(int newInventory) {

        if(inventory.size()<5){
            inventory.add(newInventory);
        }
        if(inventory.size()==5){
            inventoryFull = true;
        }
    }

    public void deleteInventory(int position) {

        inventory.remove(position);
        inventoryFull = false;
    }

}
