package com.dkkim.anew.Util

import android.app.Dialog
import android.content.Context
import com.github.ybq.android.spinkit.style.PulseRing

import com.github.ybq.android.spinkit.sprite.Sprite

import com.dkkim.anew.R
import android.os.Bundle
import android.view.View
import android.view.Window

import android.widget.ProgressBar

class ProgressDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) //title 없이
        setContentView(R.layout.dialog_progress)
        //라이브러리 로딩이미지 사용
        val progressBar = findViewById<View>(R.id.spin_kit) as ProgressBar
        val pulseRing: Sprite = PulseRing()
        progressBar.indeterminateDrawable = pulseRing
    }
}