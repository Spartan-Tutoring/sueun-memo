package com.hg_studio.oneline

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        Timer().schedule(3000){
            val intent = Intent(this@SplashActivity, SignInActivity::class.java)

            startActivity(intent)
            finish()
        }
    }

}