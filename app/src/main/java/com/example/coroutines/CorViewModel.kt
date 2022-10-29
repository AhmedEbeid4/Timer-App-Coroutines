package com.example.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CorViewModel : ViewModel() {
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    private val integer=MutableLiveData<Int>()

    init {
        _counter.value = 0
        integer.value = 1
    }
    private fun inc(){
        _counter.postValue(_counter.value!!+1)
    }
    fun onPause(){
        integer.value=1
    }
    fun onStop(){
        integer.value=0
        _counter.value=0
    }

    fun onStart() {
        integer.value=2
        start()
    }

    private fun start() {
        GlobalScope.launch {
            while (integer.value == 2){
                delay(1000)
                if(integer.value==2){
                    inc()
                }
            }
        }
    }
}