package com.learninggarden

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        editText = findViewById(R.id.editText)
//        editText.filters = arrayOf(object : InputFilter {
//            override fun filter(
//                source: CharSequence?,
//                start: Int,
//                end: Int,
//                dest: Spanned?,
//                dstart: Int,
//                dend: Int
//            ): CharSequence? {
//                Log.e("aaa","source:$source")
//                Log.e("aaa","start:$start")
//                Log.e("aaa","end:$end")
//                Log.e("aaa","dest:$dest")
//                Log.e("aaa","dstart:$dstart")
//                Log.e("aaa","dend:$dend")
//                return null
//            }
//
//        })
    }
}
