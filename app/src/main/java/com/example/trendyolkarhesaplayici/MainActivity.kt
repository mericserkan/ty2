package com.example.trendyolkarhesaplayici

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtSatisFiyati = findViewById<EditText>(R.id.edtSatisFiyati)
        val edtKomisyonOrani = findViewById<EditText>(R.id.edtKomisyonOrani)
        val btnHesapla = findViewById<Button>(R.id.btnHesapla)
        val txtSonuc = findViewById<TextView>(R.id.txtSonuc)

        btnHesapla.setOnClickListener {
            val satisFiyati = edtSatisFiyati.text.toString().toDoubleOrNull() ?: 0.0
            val komisyonOrani = edtKomisyonOrani.text.toString().toDoubleOrNull() ?: 0.0
            val netKar = hesaplaKar(satisFiyati, komisyonOrani)
            txtSonuc.text = "Net Kâr: %.2f TL".format(netKar)
        }
    }

    private fun hesaplaKar(satisFiyati: Double, komisyonOrani: Double): Double {
        val satisKdv = satisFiyati * (20.0 / 120.0)
        val komisyonTutar = (satisFiyati / 1.20) * (komisyonOrani / 100.0)
        val komisyonKdv = komisyonTutar * 0.20
        val komisyonToplam = komisyonTutar + komisyonKdv
        val stopaj = satisFiyati * 0.01
        val platform = 8.5
        val platformKdv = platform * 0.20
        val platformToplam = platform + platformKdv
        val kargo = 66.5
        val kargoKdv = kargo * 0.20
        val kargoToplam = kargo + kargoKdv
        val kdvGideri = satisKdv - (komisyonKdv + platformKdv + kargoKdv)
        return satisFiyati - (komisyonToplam + stopaj + platformToplam + kargoToplam + kdvGideri)
    }
}