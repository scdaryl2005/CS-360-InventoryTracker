package com.zybooks.darylmillercs_360inventorytracker;

/* Public class to get Inventory Users */
public class Inventory_Users {
    private String Username;
    private String Password;

    public Inventory_Users(){}

    public Inventory_Users(String username, String password) {
        super();
        this.Username = username;
        this.Password = password;

    }

    /* Get Username */
    public String getUserName() {

        return Username;

    }

    /* Get Password */
    public String getPassword() {

        return Password;

    }

    /* Set Username */
    public void setUserName(String username) {

        this.Username = username;

    }

    /* Set Password */
    public void setPassword(String password) {

        this.Password = password;

    }

}
