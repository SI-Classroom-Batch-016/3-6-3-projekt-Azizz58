package com.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapplication.Adapter.Cryptos
import com.myapplication.Adapter.CrpytosDetail
import com.myapplication.Adapter.Datasource


class SharedViewModel : ViewModel() {

    private val dataSource = Datasource.cryptosDetailList

    private val _selectedCrypto = MutableLiveData<Cryptos>()

    val selectedCrypto: LiveData<Cryptos>
        get() = _selectedCrypto

    private val _selectedCryptoDetail = MutableLiveData<CrpytosDetail?>()

    val selectedCryptoDetail: MutableLiveData<CrpytosDetail?>
        get() = _selectedCryptoDetail

    fun selectCryp(crypto: Cryptos) {
        _selectedCrypto.value = crypto

        val detail = dataSource.find { it.symbol.equals(crypto.name, ignoreCase = true) }
        // Sucht in  dataSource nach Element, dessen 'symbol' mit  'name' des übergebenen 'crypto'-Objekts übereinstimmt.
        // 'find' gibt  erstes gefundene Element/null zurück, wenn kein Element gefunden wird.

        _selectedCryptoDetail.value = detail
    }
}

