package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailCryptoBinding
import com.myapplication.Adapter.CrpytosDetail


class DetailCryptoFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailCryptoBinding
    private val args: DetailCryptoFragmentArgs by navArgs()
    private lateinit var item: CrpytosDetail


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCryptoBinding.inflate(inflater, container, false)

        binding.symbolNameTXT.text = args.name
        binding.logoIMG.setImageResource(args.logo)

        binding.backBTN.setOnClickListener {
            backToHomescreen()


        }
        return binding.root
    }

    private fun backToHomescreen() {
        findNavController().navigate(R.id.action_detailCryptoFragment_to_homeFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isClicked: Boolean = false

        viewModel.selectedCryptoDetail.observe(viewLifecycleOwner) { cryptoDetail ->
            cryptoDetail?.let {
                item = it
                binding.priceTXT.text = it.price
                binding.changePercentTXT.text = it.changePercent.toString()
                binding.pSellTXT1.text = it.sellPrice1.toString()
                binding.pSellTXT2.text = it.sellPrice2.toString()
                binding.pSellTXT3.text = it.sellPrice3.toString()
                binding.pSellTXT4.text = it.sellPrice4.toString()
                binding.pSellTXT5.text = it.sellPrice5.toString()
                binding.aSellTXT1.text = it.sellAmount1.toString()
                binding.aSellTXT2.text = it.sellAmount2.toString()
                binding.aSellTXT3.text = it.sellAmount3.toString()
                binding.aSellTXT4.text = it.sellAmount4.toString()
                binding.aSellTXT5.text = it.sellAmount5.toString()
                binding.pBuyTXT1.text = it.buyPrice1.toString()
                binding.pBuyTXT2.text = it.buyPrice2.toString()
                binding.pBuyTXT3.text = it.buyPrice3.toString()
                binding.pBuyTXT4.text = it.buyPrice4.toString()
                binding.pBuyTXT5.text = it.buyPrice5.toString()
                binding.aBuyTXT1.text = it.buyAmount1.toString()
                binding.aBuyTXT2.text = it.buyAmount2.toString()
                binding.aBuyTXT3.text = it.buyAmount3.toString()
                binding.aBuyTXT4.text = it.buyAmount4.toString()
                binding.aBuyTXT5.text = it.buyAmount5.toString()
                binding.likeBTN.setOnClickListener {

                    if (isClicked) {
                        binding.likeBTN.setImageResource(R.drawable.favorite_filled)
                        binding.likeBTN.animate().apply {
                            duration = 1000
                            rotationYBy(360f)


                        }.start()
                    } else {
                        binding.likeBTN.setImageResource(R.drawable.favorite)
                    }
                    isClicked = !isClicked
                }



                // Textfarbe basierend auf dem Prozentwert setzen
                val changePercent = it.changePercent
                val textColor = if (changePercent < 0) {
                    ContextCompat.getColor(requireContext(), R.color.red)
                } else {
                    ContextCompat.getColor(requireContext(), R.color.green)
                }
                binding.changePercentTXT.setTextColor(textColor)

            }
        }
        setVariable()

    }







    private fun setVariable() {
        binding.buyPositionBTN.setOnClickListener {
            binding.buyPositionBTN.setBackgroundResource(R.drawable.green_bg)
            binding.seellPositionBTN.setBackgroundResource(R.drawable.semi_white_bg)
            binding.sendOrderBTN.setBackgroundResource(R.drawable.green_bg)
            binding.sendOrderBTN.setText("Buy " + item.symbol.replace("/USDT", ""))
        }
        // OnClickListener Buy-Button , der  Hintergrundbild des Buttons und anderer UI-Elemente ändert und den Text des Buttons 'sendOrderBTN' auf "Buy" setzt.


        binding.seellPositionBTN.setOnClickListener {
            binding.buyPositionBTN.setBackgroundResource(R.drawable.semi_white_bg)
            binding.seellPositionBTN.setBackgroundResource(R.drawable.red_bg)
            binding.sendOrderBTN.setBackgroundResource(R.drawable.red_bg)
            binding.sendOrderBTN.setText("Sell " + item.symbol.replace("/USDT", ""))
        }
        binding.plusAmountBTN.setOnClickListener {
            if (binding.amountEDIT.text.isEmpty()) {
                binding.amountEDIT.setText("0")
            }
            var n: Double = binding.amountEDIT.text.toString().toDouble()
            n += 1
            binding.amountEDIT.setText(n.toString())
            //OnClickListener für, der  Menge im EditText 'amountEDIT' um 1 erhöht.


        }
        binding.minusAmountBTN.setOnClickListener {
            if (binding.amountEDIT.text.isEmpty()) {
                binding.amountEDIT.setText("0")
            }
            var n: Double = binding.amountEDIT.text.toString().toDouble()
            if (n > 0) {
                n -= 1
                binding.amountEDIT.setText(n.toString())
            }
        }
        // Share button implementieren
        binding.shareBTN.setOnClickListener {
            shareCryptoDetail(item.symbol, item.price)
        }
    }

    private fun shareCryptoDetail(symbol: String, price: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Teile diese Kryptowährung")
            putExtra(Intent.EXTRA_TEXT, "Schau dir diese Kryptowährung an: $symbol zu einem Preis von $price")
        }
        startActivity(Intent.createChooser(shareIntent, "Teilen über"))
    }
}