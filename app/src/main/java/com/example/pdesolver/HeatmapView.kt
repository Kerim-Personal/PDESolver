package com.example.pdesolver

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class HeatmapView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var data: Array<Array<Double>>? = null
    private val paint = Paint()
    private var maxValue: Double = 100.0
    private var minValue: Double = 0.0

    fun setData(newData: Array<Array<Double>>) {
        this.data = newData
        // Veri setindeki min ve max değerleri bul (renk eşlemesi için)
        if (newData.isNotEmpty() && newData[0].isNotEmpty()) {
            minValue = newData.flatten().minOrNull() ?: 0.0
            maxValue = newData.flatten().maxOrNull() ?: 100.0
        }
        // View'ı yeniden çizmesi için invalidate() çağır
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        data?.let {
            val grid = it
            val gridSize = grid.size
            if (gridSize == 0) return

            val cellWidth = width.toFloat() / gridSize
            val cellHeight = height.toFloat() / gridSize

            for (i in 0 until gridSize) {
                for (j in 0 until gridSize) {
                    val value = grid[i][j]
                    paint.color = getColorForValue(value)
                    canvas.drawRect(
                        j * cellWidth,
                        i * cellHeight,
                        (j + 1) * cellWidth,
                        (i + 1) * cellHeight,
                        paint
                    )
                }
            }
        }
    }

    private fun getColorForValue(value: Double): Int {
        if (maxValue == minValue) {
            return Color.BLUE // Tek renk
        }
        // Değeri 0-1 aralığına normalize et
        val fraction = ((value - minValue) / (maxValue - minValue)).toFloat()

        // Basit bir mavi (soğuk) -> kırmızı (sıcak) renk skalası
        val red = (255 * fraction).toInt()
        val blue = (255 * (1 - fraction)).toInt()
        val green = 0

        return Color.rgb(red, green, blue)
    }
}