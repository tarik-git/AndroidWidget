package com.example.factsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.factsapp.data.local.SharedPreferencesManager
import com.example.factsapp.data.remote.RetrofitService
import com.example.factsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.refreshButton.setOnClickListener { onRefreshButtonClick() }
    }

    override fun onResume() {
        super.onResume()
        renderFactText()
    }

    private fun onRefreshButtonClick() = lifecycleScope.launch(Dispatchers.IO) {
        val fact = RetrofitService.getNumbersApiService().getDateFactSuspended(
            Util.getMonth(),
            Util.getDayOfMonth()
        )
        fact?.let {
            SharedPreferencesManager.saveData(baseContext, fact.text)
            renderFactText()
            // TODO Request the widget be updated here:
        } ?: Toast.makeText(baseContext, getString(R.string.failed_to_retrieve), Toast.LENGTH_SHORT).show()
    }

    private fun renderFactText() {
        val data: String? = SharedPreferencesManager.loadData(baseContext)
        data?.let {
            binding.refreshTextView.text = it
        }
    }

}