package com.marisa.guillaume.appmeteo.city

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marisa.guillaume.appmeteo.App
import com.marisa.guillaume.appmeteo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        App.database
    }
}
