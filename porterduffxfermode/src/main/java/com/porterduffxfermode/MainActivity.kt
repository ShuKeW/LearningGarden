package com.porterduffxfermode

import android.content.Intent
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var porterDuffXfermodeView: PorterDuffXfermodeView
    private val idArray = arrayOf(
        R.id.clear,
        R.id.src,
        R.id.dst,
        R.id.src_over,
        R.id.dst_over,
        R.id.src_in,
        R.id.dst_in,
        R.id.src_out,
        R.id.dst_out,
        R.id.src_atop,
        R.id.dst_atop,
        R.id.xor,
        R.id.darken,
        R.id.lighten,
        R.id.multiply,
        R.id.screen
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        porterDuffXfermodeView = findViewById(R.id.porterDuffXfermodeView)
        idArray.forEach {
            findViewById<Button>(it).setOnClickListener(this@MainActivity)
        }
        findViewById<Button>(R.id.start_second_activity).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.clear -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.CLEAR)
            }
            R.id.src -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.SRC)
            }
            R.id.dst -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.DST)
            }
            R.id.src_over -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.SRC_OVER)
            }
            R.id.dst_over -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.DST_OVER)
            }
            R.id.src_in -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.SRC_IN)
            }
            R.id.dst_in -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.DST_IN)
            }
            R.id.src_out -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.SRC_OUT)
            }
            R.id.dst_out -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.DST_OUT)
            }
            R.id.src_atop -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.SRC_ATOP)
            }
            R.id.dst_atop -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.DST_ATOP)
            }
            R.id.xor -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.XOR)
            }
            R.id.darken -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.DARKEN)
            }
            R.id.lighten -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.LIGHTEN)
            }
            R.id.multiply -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.MULTIPLY)
            }
            R.id.screen -> {
                porterDuffXfermodeView.setXfermode(PorterDuff.Mode.SCREEN)
            }
            R.id.start_second_activity -> {
                startActivity(Intent(this,SecondActivity::class.java))
            }
        }
    }
}
