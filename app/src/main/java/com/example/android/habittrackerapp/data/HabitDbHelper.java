package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Database helper for Drinking Habits Tracker app. Manages database creation and version management.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "drinks.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the drinks table
        String SQL_CREATE_DRINKS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_DRINK_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_DRINK_CATEGORY + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_DRINK_QUANTITY + " INTEGER NOT NULL DEFAULT 1, "
                + HabitEntry.COLUMN_DRINK_CALORIES + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_DRINKS_TABLE);
    }

    /**
     * Method to read all the information from the database
     */
    public Cursor readAllHabits(SQLiteDatabase db) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_DRINK_NAME,
                HabitEntry.COLUMN_DRINK_CATEGORY,
                HabitEntry.COLUMN_DRINK_QUANTITY,
                HabitEntry.COLUMN_DRINK_CALORIES};

        // Perform a query on the pets table
        return db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}