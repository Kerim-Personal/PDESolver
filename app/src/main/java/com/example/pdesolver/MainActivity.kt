package com.example.pdesolver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pdesolver.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSolve.setOnClickListener {
            solveLaplaceEquation()
        }
    }

    private fun solveLaplaceEquation() {
        // Kullanıcı girdilerini al
        val gridSize = binding.etGridSize.text.toString().toIntOrNull() ?: 20
        val topBoundary = binding.etTopBoundary.text.toString().toDoubleOrNull() ?: 100.0
        val bottomBoundary = binding.etBottomBoundary.text.toString().toDoubleOrNull() ?: 0.0
        val leftBoundary = binding.etLeftBoundary.text.toString().toDoubleOrNull() ?: 0.0
        val rightBoundary = binding.etRightBoundary.text.toString().toDoubleOrNull() ?: 0.0

        // Hesaplamayı arayüzü kilitlememek için arka planda (coroutine) yap
        CoroutineScope(Dispatchers.Default).launch {
            val solution = performCalculation(gridSize, topBoundary, bottomBoundary, leftBoundary, rightBoundary)

            // Sonucu UI thread'inde göster
            withContext(Dispatchers.Main) {
                binding.heatmapView.setData(solution)
            }
        }
    }

    private fun performCalculation(
        gridSize: Int,
        top: Double,
        bottom: Double,
        left: Double,
        right: Double
    ): Array<Array<Double>> {
        // Izgarayı (matrisi) oluştur
        val grid = Array(gridSize) { Array(gridSize) { 0.0 } }

        // Sınır koşullarını ata
        for (i in 0 until gridSize) {
            grid[0][i] = top       // Üst kenar
            grid[gridSize - 1][i] = bottom // Alt kenar
            grid[i][0] = left      // Sol kenar
            grid[i][gridSize - 1] = right  // Sağ kenar
        }

        val maxIterations = 2000 // Maksimum iterasyon sayısı
        val tolerance = 1e-4     // Durma kriteri için tolerans

        for (iter in 0 until maxIterations) {
            var maxChange = 0.0
            for (i in 1 until gridSize - 1) {
                for (j in 1 until gridSize - 1) {
                    val oldValue = grid[i][j]
                    val newValue = (grid[i - 1][j] + grid[i + 1][j] + grid[i][j - 1] + grid[i][j + 1]) / 4.0
                    grid[i][j] = newValue
                    maxChange = maxOf(maxChange, Math.abs(newValue - oldValue))
                }
            }

            // Değişim yeterince küçükse döngüden çık
            if (maxChange < tolerance) {
                break
            }
        }
        return grid
    }
}