package com.example.bugsreporter.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bugsreporter.Models.Bug

@Database(
    entities = [Bug::class],
    version = 1
)
abstract class BugsDb:RoomDatabase() {
    public abstract val dao:BugDao;

    //To avoid multiple db handlers
    companion object Singleton{
        var _instance:BugsDb?=null;


        fun getInstance(ctx: Context):BugsDb{
            if(_instance!=null)
                return _instance as BugsDb;
            return Room.databaseBuilder(ctx,BugsDb::class.java,"BugsDb").build();
        }
    }
}