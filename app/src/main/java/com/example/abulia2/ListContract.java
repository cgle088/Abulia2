package com.example.abulia2;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ListContract {
    //Constructor is private to prevent "accidentally instantiating the contract class"
    private ListContract() {
    }

    public static class List implements BaseColumns {
        private static final String TABLE_NAME = "Lists";
        private static final String COLUMN = "Option";
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +COLUMN + " TEXT )";
        private static final String DELETE_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;

        public static class ListDbHelper extends SQLiteOpenHelper {
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "Lists.db";

            public ListDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }



            public boolean newTable(SQLiteDatabase db, String tableName){
                try {
                    db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                            " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +COLUMN + " TEXT )");
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            public void getAllTables(SQLiteDatabase db){
                db.execSQL("SELECT name FROM sqlite_master WHERE type='table'");
            }

            public void onCreate(SQLiteDatabase db) {

            }

            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
                db.execSQL(DELETE_TABLE);
                onCreate(db);
            }
            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
                onUpgrade(db, oldVersion, newVersion);
            }

        }
    }
}
