package com.example.bugsreporter.ViewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.bugsreporter.Data.BugDao
import com.example.bugsreporter.Data.BugsDb
import com.example.bugsreporter.Models.Bug
import kotlinx.coroutines.launch

class Factory(val dao: BugDao):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(dao) as T;
    }
}

class AppViewModel(private val dao:BugDao):ViewModel() {
    private val actualList=mutableListOf<Bug>();


    val chosenBug=MutableLiveData<Bug?>(null);

    val bugs:MutableLiveData<List<Bug>> by lazy { MutableLiveData(listOf<Bug>()) };

    init {
        loadBugs()
    }

    fun modifyBug(bug:Bug){
        chosenBug.value=bug;
    }
    private fun loadBugs(){
        viewModelScope.launch {
            actualList.addAll(dao.getBugs())
            updateLiveList()
        }
    }

    //As we are using the dao upsert, this method will be used to add or update bug
    fun commitChange(){
        val bug=chosenBug.value!!;
        val new=(bug.id?:0)<1;
        toggleDialog();
        viewModelScope.launch{
            val assignedId:Long=dao.upsert(bug);
            if(new) {
                actualList.add(bug.apply { id=assignedId.toInt() });
                updateLiveList()
            }
//                bugs.postValue(bugs.value?.toList());
//            bugs.postValue(bugs.value);
        }
//            .invokeOnCompletion {
//                bugs.value=bugs.value;
//            }
    }
    fun deleteBug(bug:Bug){
        viewModelScope.launch{
            dao.delete(bug)
        }.invokeOnCompletion {
            actualList.remove(bug);
            updateLiveList()
//            bugs.postValue(bugs.value?.toMutableList());
        }
    }
    fun toggleDialog() {
        chosenBug.setValue(
            if(chosenBug.value==null)
                Bug()
            else
                null
        )
    }
    private fun updateLiveList(){
        bugs.postValue(actualList.toList());
    }
}