package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.myapplication.Adapter.CryptoListAdapter
import com.myapplication.Adapter.Datasource


class HomeFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = Datasource.cryptosList

        val recyclerView = binding.recyclerView1
        recyclerView.adapter = CryptoListAdapter(data) { crypto ->
            viewModel.selectCryp(crypto)
            val action = HomeFragmentDirections.actionHomeFragmentToDetailCryptoFragment(
                crypto.logo,
                crypto.name
            )
            findNavController().navigate(action)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
