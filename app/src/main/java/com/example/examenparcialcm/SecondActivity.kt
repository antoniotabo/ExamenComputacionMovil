package com.example.examenparcialcm

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val nombre = intent.getStringExtra("EXTRA_NOMBRE")
        val apellidos = intent.getStringExtra("EXTRA_APELLIDOS")


        val tvMensajeFinal = findViewById<TextView>(R.id.tvMensajeFinal)
        tvMensajeFinal.text = "Registro Correcto $nombre $apellidos"
    }

    override fun onStart() {
        super.onStart()

        mediaPlayer = MediaPlayer.create(this, R.raw.super_mario)
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.start()
        mediaPlayer?.seekTo(position)
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.let { player ->
            player.pause()
            position = player.currentPosition
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}