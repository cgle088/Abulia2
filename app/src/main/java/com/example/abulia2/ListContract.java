package com.example.abulia2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class ListContract {
    //Constructor is private to prevent "accidentally instantiating the contract class"
    private ListContract() {
    }

    public static class List implements BaseColumns {
        private static final String TAG = "ListContract";
        private static final String TABLE_NAME = "Lists";
        private static final String COLUMN = "Option";
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +COLUMN + " TEXT )";
        private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static class ListDbHelper extends SQLiteOpenHelper {
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "Lists.db";

            public ListDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
                //this.getReadableDatabase();
            }

            public void dropTable(SQLiteDatabase db, String tableName){
                db.execSQL("DROP TABLE " + tableName + ";");
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

            public Cursor getTable(SQLiteDatabase db, String listName){
                Log.d(TAG,"getTable: called, getting " + listName);
                return db.rawQuery("SELECT * FROM " + listName + ";", null);
            }

            public Cursor getAllTables(SQLiteDatabase db){
                return db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' " +
                        "AND name != 'sqlite_sequence' AND name != 'android_metadata';", null);
            }

            public void dropTableIfExists(SQLiteDatabase db, String tableName){
                Log.d(getClass().getName(), "dropTableIfExists: called. Removing list "+tableName);
                db.execSQL("DROP TABLE IF EXISTS "+tableName+";");

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
