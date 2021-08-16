package com.zybooks.darylmillercs_360inventorytracker;

/* Item DB */
public class Item {
    private int id;
    private String title;
    private String description;
    private int count;

    public Item() {

    }

    public Item(int id, String description, String title, int count) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.description = description;

    }

    /* Get ID */
    public int getId() {
        return id;

    }

    /* Set ID */
    public void setId(int id) {
        this.id = id;

    }

    /* Get Title */
    public String getTitle() {
        return title;

    }

    /* Get Count */
    public int getCount() {
        return count;

    }

    /* Get Description */
    public String getDescription() {
        return description;

    }

}

