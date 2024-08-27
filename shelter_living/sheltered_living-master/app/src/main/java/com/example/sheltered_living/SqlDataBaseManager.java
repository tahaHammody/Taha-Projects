package com.example.sheltered_living;

import android.content.Context;

public class SqlDataBaseManager {

    public static MySqlDatabaseHelper database;
    public static void init(Context context) {
        database = new MySqlDatabaseHelper(context);
    }
}
