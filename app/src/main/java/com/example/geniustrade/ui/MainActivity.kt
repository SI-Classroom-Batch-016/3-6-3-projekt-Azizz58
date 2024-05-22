package com.example.geniustrade.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniustrade.Adapter.CryptoListAdapter
import com.example.geniustrade.ViewModel.MainViewModel
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        window.setFlags(
            WindowManager. LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        initRecyclerviewCrypto()

    }

    private fun initRecyclerviewCrypto() {

        binding.view.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.view.adapter=CryptoListAdapter(mainViewModel.loadData())
    }
}
