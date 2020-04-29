package com.example.musicplayerapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay.setOnClickListener {
            ForegroundService.startService(this, "Music is playing ...")

        }
        /*   buttonStop.setOnClickListener {
               ForegroundService.stopService(this)
           }*/
    }


    class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            Toast.makeText(context.applicationContext, "Your message", Toast.LENGTH_SHORT)
                .show()
        }
    }
/*    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, playIntent: Intent) {
            val bundle = playIntent.extras
            val string = bundle?.getString(ForegroundService.FILEPATH)
            Toast.makeText(
                this@MainActivity,
                "Download complete. Download URI: $string",
                Toast.LENGTH_LONG
            ).show()
        }
    }*/
    /*{
        val bundle = intent.extras
        if (bundle != null) {
            val string = bundle.getString(ForegroundService.FILEPATH)
            val resultCode = bundle.getInt(ForegroundService.RESULT)
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this@MainActivity,
                    "Download complete. Download URI: $string",
                    Toast.LENGTH_LONG).show()
                status.text = "Finished"
            } else {
                Toast.makeText(this@MainActivity, "Download failed",
                    Toast.LENGTH_LONG).show()
                status.text = "Failed"
            }
        }
    }*/

}
