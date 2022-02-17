//Pablo Mateos García

package com.mateosgarciapablo.adventuregame;

import java.util.ArrayList;

public class Location {

    //Elements of the location
    
    static final int NO_EXIT = -1;
    private int up;
    private int right;
    private int down;
    private int left;
    private String description;
    private String name;
    private ArrayList <Integer> inventory;
    private boolean inventoryFull;

    public Location() {

        //Initial set up

        up = NO_EXIT;
        right = NO_EXIT;
        down = NO_EXIT;
        left = NO_EXIT;

        description = "NOTHING";
        name = "NOTHING";

        inventory = new ArrayList<>();
        inventoryFull = false;

    }

    //Getters and setters

    protected int getUp() {

        return up;
    }

    protected void setUp(int up) {

        this.up = up;
    }

    protected int getRight() {

        return right;
    }

    protected void setRight(int right) {

        this.right = right;
    }

    protected int getDown() {

        return down;
    }

    protected void setDown(int down) {

        this.down = down;
    }

    protected int getLeft() {

        return left;
    }

    protected void setLeft(int left) {

        this.left = left;
    }

    protected String getDescription() {

        return description;
    }

    protected void setDescription(String description) {

        this.description = description;
    }

    protected ArrayList<Integer> getInventory() {

        ArrayList<Integer> newInventory = new ArrayList<>();
        newInventory.addAll(inventory);
        while(newInventory.size()<5){
            newInventory.add(R.drawable.ic_void);
        }
        return newInventory;
    }


    protected String getName() {

        return name;
    }

    protected void setName(String name) {

        this.name = name;
    }

    protected boolean isInventoryFull() {

        return inventoryFull;
    }

    //Add and delete functions

    protected void deleteInventory(int position) {

        inventory.remove(position);
        inventoryFull = false;
    }

    protected void deleteItem(int item) {

        inventory.remove((Object) item);
        inventoryFull = false;
    }

    protected void addInventory(int newInventory) {

        if(inventory.size()<5){
            inventory.add(newInventory);
        }
        if(inventory.size()==5){
            inventoryFull = true;
        }
    }

}
