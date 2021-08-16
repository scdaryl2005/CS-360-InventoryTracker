package com.zybooks.darylmillercs_360inventorytracker;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/* Public Class using SQLite */
public class Inventory_DB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Inventory_DB";

    private static final int VERSION = 1;

    private static Inventory_DB Inventory_DB;

    /* Create or return DB */
    public static Inventory_DB getInstance(Context context) {
        if (Inventory_DB == null)  // Look for empty DB

            Inventory_DB = new Inventory_DB (context);  // Create new DB

        return Inventory_DB;  // Return existing DB

    }

    private Inventory_DB(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

/* Define Item Inventory_DB Table */
    private static final class ItemTable {
        private static final String Table = "items";
        private static final String Column = "_id";
        private static final String Description = "description";
        private static final String Title = "_title";
        private static final String Quantity = "_count";

    }

/* Define User Inventory DB Table */
    private static final class UserTable {
        private static final String Table = "users";
        private static final String Column = "_id";
        private static final String Username = "username";
        private static final String Password = "password";

    }

    /* Create Item and User Tables */
    @Override
    public void onCreate(SQLiteDatabase db) {

        /* Create Item Table */
        db.execSQL("create table " + ItemTable.Table + " (" +
                ItemTable.Column + " integer primary key autoincrement, " +
                ItemTable.Description + " text, " +
                ItemTable.Title + " text, " +
                ItemTable.Quantity + " int )");

        /* Create User Table */
        db.execSQL("create table " + UserTable.Table + " (" +
                UserTable.Column + " integer  primary key autoincrement, " +
                UserTable.Username + " text, " +
                UserTable.Password + " text )");

    }

/* Update the table schema to the requested version */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserTable.Table);
        db.execSQL("drop table if exists " + ItemTable.Table);
        onCreate(db);

    }

    /* Add User */
    public void addUser(Inventory_Users user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.Username, user.getUserName());
        values.put(UserTable.Password, user.getPassword());
        db.insert(UserTable.Table, null, values);

    }

    /* Get User */
    public Inventory_Users getUser(Inventory_Users user) {
        SQLiteDatabase db = getReadableDatabase();
        Inventory_Users foundUser = null;
        String sql = "select * from " + UserTable.Table + " where " + UserTable.Username + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {user.getUserName()});

        if (cursor.moveToFirst()) {
            foundUser = new Inventory_Users();
            foundUser.setUserName(cursor.getString(1));
            foundUser.setPassword(cursor.getString(2));

        }

        return foundUser;
    }

    /* Add Item */
    public void addItem(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ItemTable.Title, item.getTitle());
        values.put(ItemTable.Quantity, item.getCount());

        db.insert(ItemTable.Table, null, values);

    }

    /* Get Item */
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + ItemTable.Table;

        Cursor cursor = db.rawQuery(sql, null,null);
        if(cursor.moveToFirst()) {
            do {
                Item newItem = new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                items.add(newItem);

            }

            while(cursor.moveToNext());

        }

        cursor.close();
        return items;

    }

    /* Delete Item */
    public void deleteInventory(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ItemTable.Table, ItemTable.Column + " = " + id, null);

    }

    /* Increase Quantity */
    public void increaseQuantity(int id, int currentCount) {
        SQLiteDatabase dbAdd = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemTable.Quantity, currentCount+1);
        dbAdd.update(ItemTable.Table, values, "_id = ?", new String[] {Integer.toString(id)});

    }

    /* Decrease Quantity */
    public void decreaseQuantity(int id, int currentCount) {
        SQLiteDatabase dbAdd = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemTable.Quantity, currentCount-1);
        dbAdd.update(ItemTable.Table, values, "_id = ?", new String[] {Integer.toString(id)});

    }

}