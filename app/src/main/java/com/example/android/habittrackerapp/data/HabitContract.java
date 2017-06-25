package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Drinking Habits app.
 */
public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {
    }

    /**
     * Inner class that defines constant values for the drinks database table.
     * Each entry in the table represents a single drink.
     */
    public static class HabitEntry implements BaseColumns {

        /**
         * Name of database table for drinks
         */
        public static final String TABLE_NAME = "drinks";

        /**
         * Unique ID number for the drink (only for use in the database table).
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the drink.
         * Type: TEXT
         */
        public static final String COLUMN_DRINK_NAME = "name";
        /**
         * Category of the drink.
         * The only possible values are {@link #CATEGORY_UNKNOWN}, {@link #CATEWORY_WATER},
         * {@link #CATEGORY_TEA}, {@link #CATEGORY_JUICE}, {@link #CATEGORY_REFRESHMENT},
         * {@link #CATEGORY_ALCHOOL} or {@link #CATEGORY_COFFEE}.
         * Type: INTEGER
         */
        public static final String COLUMN_DRINK_CATEGORY = "category";
        /**
         * Quantity of drink (in glasses).
         * Type: INTEGER
         */
        public static final String COLUMN_DRINK_QUANTITY = "quantity";
        /**
         * Drink's calories.
         * Type: INTEGER
         */
        public static final String COLUMN_DRINK_CALORIES = "calories";

        /**
         * Possible values for the category of the drink.
         */
        public static final int CATEGORY_UNKNOWN = 0;
        public static final int CATEWORY_WATER = 1;
        public static final int CATEGORY_TEA = 2;
        public static final int CATEGORY_JUICE = 3;
        public static final int CATEGORY_REFRESHMENT = 4;
        public static final int CATEGORY_ALCHOOL = 5;
        public static final int CATEGORY_COFFEE = 6;
    }
}