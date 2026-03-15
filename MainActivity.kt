package com.example.messagesender

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val serverUrl = "http://192.168.0.119:5000/send"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.messageInput)
        val button = findViewById<Button>(R.id.sendButton)

        button.setOnClickListener {

            val text = input.text.toString()

            thread {

                try {

                    val url = URL(serverUrl)
                    val conn = url.openConnection() as HttpURLConnection

                    conn.requestMethod = "POST"
                    conn.doOutput = true

                    val data = "text=$text"

                    conn.outputStream.write(data.toByteArray())
                    conn.outputStream.flush()

                    conn.inputStream

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }

    }

}
