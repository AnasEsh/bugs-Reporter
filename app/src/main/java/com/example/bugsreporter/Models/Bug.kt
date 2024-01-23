package com.example.bugsreporter.Models

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Priority{
    HIGH(),
    MEDIUM,
    LOW;

    override fun toString(): String {
        return name
    }
}

@Entity
data class Bug(
    var title:String="",
    var description:String="",
    var priority:Priority=Priority.LOW,
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null);
