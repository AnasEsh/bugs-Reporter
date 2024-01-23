package com.example.bugsreporter.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Priority{
    HIGH,
    MEDIUM,
    LOW
}

@Entity
data class Bug(
    var title:String,
    var description:String,
    var priority:Priority,
    @PrimaryKey(autoGenerate = true)
    var id:Int?);
