package com.example.pasapplication.data.LocalDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pasapplication.data.Product;

@Database(entities = {Product.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDAO getProductDAO();
    private static AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "PasDatabase").
                    allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
