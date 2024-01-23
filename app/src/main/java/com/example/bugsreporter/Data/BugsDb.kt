package com.example.bugsreporter.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bugsreporter.Models.Bug

@Database(
    entities = [Bug::class],
    version = 1
)
abstract class BugsDb:RoomDatabase() {
    public abstract val dao:BugDao;

}