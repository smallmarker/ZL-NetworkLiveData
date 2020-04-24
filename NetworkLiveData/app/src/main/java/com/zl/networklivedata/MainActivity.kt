package com.zl.networklivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NetworkLiveData.getInstance().observe(this, Observer {
            when (it) {
                NetworkState.CONNECT -> {
                    text.text = "网络已连接"
                }

                NetworkState.NONE -> {
                    text.text = "网络中断"
                }
            }
        })
    }
}
