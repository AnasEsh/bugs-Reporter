package com.example.bugsreporter.ViewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bugsreporter.Data.BugDao
import com.example.bugsreporter.Data.BugsDb
import com.example.bugsreporter.Models.Bug
import kotlinx.coroutines.launch

class Factory(val context: Context):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(context) as T;
    }
}

class AppViewModel(val appContext:Context):ViewModel() {
    lateinit var dao:BugDao;
    val bugs:MutableLiveData<List<Bug>> by lazy { MutableLiveData(emptyList<Bug>()) };
    val isLoading=MutableLiveData(false);
    val choosenBug:MutableLiveData<Bug?> = MutableLiveData(null)

    init {
        dao=Room
        .databaseBuilder(appContext,BugsDb::class.java,"bugsDb").build().dao;
        loadBugs()
    }

    private fun loadBugs(){
        viewModelScope.launch {
            bugs.postValue(dao.getBugs())
        }
    }

    //As we are using the dao upsert, this method will be used to add or update bug
    fun commitChange(){
    choosenBug?.let {
        viewModelScope.launch{
            dao.upsert(choosenBug.value!!);
        }
    }
    }

}