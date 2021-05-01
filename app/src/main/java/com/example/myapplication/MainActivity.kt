package com.example.myapplication

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.form_text.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fileInputStream: FileInputStream
        try {
            fileInputStream=openFileInput("song1.txt")
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder: StringBuilder = StringBuilder()
            var songText: String? =null

            while ({ songText=bufferedReader.readLine(); songText}()!=null){
                stringBuilder.append(songText)
            }
            textSong.setText(stringBuilder.toString())
            //textSong.setText(stringBuilder.toString()).toString()
            println(textSong.setText(stringBuilder.toString()))
        }catch (e: Exception){
            e.printStackTrace()
        }
        button4.setOnClickListener {
            val dialog=Dialog(this)
            dialog.setContentView(R.layout.form_text)
            dialog.setTitle("Add song lyrics")
            dialog.button.setOnClickListener {
                //val text=song.text.toString()
                val text=dialog.SongText.text.toString()
                val fileOutputStream: FileOutputStream
                try {
                    fileOutputStream= openFileOutput("song1.txt", Context.MODE_PRIVATE)
                    fileOutputStream.write(text.toByteArray())

                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
            dialog.show()
        }
    }
}