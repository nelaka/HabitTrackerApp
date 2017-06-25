package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;

/**
 * Displays list of drinks that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);

        /** Call of the insert method */
        insertDrink();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /** Call of the read methor */
        displayDatabaseInfo();
    }

    /**
     * Method to display information in the onscreen TextView about the state of
     * the drinks database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_DRINK_NAME,
                HabitEntry.COLUMN_DRINK_CATEGORY,
                HabitEntry.COLUMN_DRINK_QUANTITY,
                HabitEntry.COLUMN_DRINK_CALORIES};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_drink);

        try {
            displayView.setText("The drinks table contains " + cursor.getCount() + " drinks.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_DRINK_NAME + " - " +
                    HabitEntry.COLUMN_DRINK_CATEGORY + " - " +
                    HabitEntry.COLUMN_DRINK_QUANTITY + " - " +
                    HabitEntry.COLUMN_DRINK_CALORIES + "\n");

            // The index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DRINK_NAME);
            int categoryColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DRINK_CATEGORY);
            int quantityColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DRINK_QUANTITY);
            int caloriesColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DRINK_CALORIES);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentCategory = cursor.getInt(categoryColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                int currentCalories = cursor.getInt(caloriesColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentCategory + " - " +
                        currentQuantity + " - " +
                        currentCalories));
            }
        } finally {
            // Close the cursor to releases all its resources
            cursor.close();
        }
    }

    /**
     * Insert method to insert hardcoded drink data into the database.
     */
    private void insertDrink() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and drink's attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_DRINK_NAME, "Aperol");
        values.put(HabitEntry.COLUMN_DRINK_CATEGORY, HabitEntry.CATEGORY_ALCHOOL);
        values.put(HabitEntry.COLUMN_DRINK_QUANTITY, 2);
        values.put(HabitEntry.COLUMN_DRINK_CALORIES, 50);

        // Insert a new row for Aperol drink in the database, returning the ID of that new row.
        // The first argument for db.insert() is the drinks table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Aperol.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "Number of rows in drinks db: " + newRowId);
    }
}