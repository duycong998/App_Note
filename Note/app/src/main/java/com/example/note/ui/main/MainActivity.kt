package com.example.note.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R
import com.example.note.utils.addFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(MainFragment(), R.id.container)

    }

//    private fun test() {
//        val result = arrayListOf(1, 2, 5, 6).reduce { a, b ->
//            (a + b)
//        }
//        Log.d("AAA", result.toString())
//    }
}
