package com.example.bugsreporter.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bugsreporter.Models.Bug

@Dao
interface BugDao {

    @Query("SELECT * FROM bug")
    suspend fun getBugs(): MutableList<Bug>

    @Upsert
    suspend fun upsert(bug: Bug):Long
    @Delete
    suspend fun delete(bug: Bug):Int
}