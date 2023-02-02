package com.example.myapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class SudokuBoardView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet)
{
    private var sqrtSize = 3
    private var size = 9

    private var cellSizePixels = 0f

    private var selectedRow = 0
    private var selectedCol = 0

    private var listener: SudokuBoardView.OnTouchListener? = null

    private fun fillCell(canvas: Canvas, r : Int, c : Int, paint: Paint){ canvas.drawRect(c * cellSizePixels, r * cellSizePixels, (c+1) * cellSizePixels, (r+1) * cellSizePixels, paint)}

    private fun fillCells(canvas: Canvas)
    { if (selectedRow == -1 || selectedCol == -1) return

        for (r in 0..size)
        {
            for (c in 0..size){
                if (r == selectedRow && c == selectedCol){
                    fillCell(canvas, r, c, selectCellPaint)
                } else if (r == selectedRow || c == selectedCol) {
                    fillCell(canvas,r, c, conflictCellPaint)
                } else if (r / sqrtSize == selectedRow / sqrtSize && c / sqrtSize == selectedCol / sqrtSize){
                    fillCell(canvas, r, c, conflictCellPaint)
                }
            }


        }
    }


    private fun drawLines(canvas: Canvas)
    {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), FatLinePaint)
        for (i in 1 until size)
        { val paintToUse = when(i % sqrtSize){0 -> FatLinePaint else -> ThinLinePaint}
          canvas.drawLine(i * cellSizePixels, 0f, i * cellSizePixels, height.toFloat(), paintToUse)
            canvas.drawLine( 0f, i * cellSizePixels, width.toFloat(), i * cellSizePixels, paintToUse)

        }
    }



    private val selectCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#4682B4")
    }

    private val conflictCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#B0C4DE")
    }

    private val FatLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 8f
    }

    private val ThinLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 4f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sizePixels = Math.min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    override fun onDraw(canvas: Canvas)
    {
        cellSizePixels = (width / size).toFloat()
        fillCells(canvas)
        drawLines(canvas)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
       return when (event.action)
       {
           MotionEvent.ACTION_DOWN ->
           {
           TouchEvent(event.x, event.y)
           true
           } else -> false
       }
    }

    private fun TouchEvent (x: Float, y: Float){
        val pressSelectedRow = (y / cellSizePixels).toInt()
        val pressSelectedCol = (x / cellSizePixels).toInt()
        listener?.onCellTouched(pressSelectedRow, pressSelectedCol)
    }

    fun updateSelectedCellUI(row: Int, col: Int){
        selectedRow = row
        selectedCol = col
        invalidate()
    }

    fun regListener(listener: SudokuBoardView.OnTouchListener){
        this.listener = listener
    }

    interface OnTouchListener{
        fun onCellTouched(row: Int, col: Int)
    }

}