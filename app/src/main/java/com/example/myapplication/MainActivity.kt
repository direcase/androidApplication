package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
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
    var notificationChannel: NotificationChannel? = null
    var notificationManager: NotificationManager? = null
    var builder: Notification.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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
                val text=dialog.textInputLayout1.text.toString()
                var fileOutputStream: FileOutputStream
                dialog.button.setOnClickListener {
                    try {
                        fileOutputStream= openFileOutput("song1.txt", Context.MODE_PRIVATE)
                        var s=" "
                        fileOutputStream.write(textSong.text.toString().toByteArray()+s.toByteArray()+text.toByteArray())

                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                    dialog.hide()
                    btnNotify()
                }

            }
            dialog.show()
        }
    }
    fun btnNotify() {
        val intent = Intent(this, LauncherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
            notificationChannel!!.lightColor = Color.BLUE
            notificationChannel!!.enableVibration(true)
            notificationManager!!.createNotificationChannel(notificationChannel!!)
            builder = Notification.Builder(this, channelId).setContentTitle("NOTIFICATION USING " +
                    "KOTLIN").setContentText("Successfully added text ").setSmallIcon(R.drawable.ic_launcher_background).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable
                    .ic_launcher_background)).setContentIntent(pendingIntent)
        }
        notificationManager!!.notify(12345, builder!!.build())
    }
    companion object {
        const val channelId = "12345"
        const val description = "You added text to song"
    }
}