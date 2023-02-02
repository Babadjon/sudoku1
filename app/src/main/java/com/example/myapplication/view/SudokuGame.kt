package com.example.myapplication.view

import androidx.lifecycle.MutableLiveData

class SudokuGame {
    var selectCellLiveData = MutableLiveData<Pair<Int, Int>>()
    private var selectedRow = -1
    private var selectedCol = -1

    init {
        selectCellLiveData.postValue(Pair(selectedRow, selectedCol))
    }
    fun updateSelectCell(row: Int, col:Int){
        selectedRow = row
        selectedCol = col
        selectCellLiveData.postValue(Pair(row, col))
    }

}