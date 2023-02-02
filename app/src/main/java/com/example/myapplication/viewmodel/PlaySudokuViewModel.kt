package com.example.myapplication.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.myapplication.view.SudokuGame

class PlaySudokuViewModel : ViewModel(){
val sudokuGame = SudokuGame()
}
