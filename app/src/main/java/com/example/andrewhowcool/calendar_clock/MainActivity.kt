package com.example.andrewhowcool.calendar_clock

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*
import android.widget.*
import android.app.AlarmManager
import org.jetbrains.anko.datePicker
import android.content.BroadcastReceiver


class MainActivity : AppCompatActivity() {

    var notificationId = 0 //reset the notification ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //after clicking the button, get the date and time from the calendar
        //set the alarm, and remind the AlarmReceiver to do things when the time has come
        button.setOnClickListener {
            var year = datePicker.year
            var month = datePicker.month
            var day = datePicker.dayOfMonth
            var hour = timePicker.hour
            var minute = timePicker.minute
            var to_do = editText.text


            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.set(
                    AlarmManager.RTC_WAKEUP, //wake up the phone even it is sleeping

                    //set the time
                    Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, day)
                        set(Calendar.HOUR_OF_DAY, hour)
                        set(Calendar.MINUTE, minute)
                        set(Calendar.SECOND, 0)
                    }.timeInMillis, //accuracy

                    //execute not immediately
                    PendingIntent.getBroadcast(
                            applicationContext,

                            0,

                            Intent(applicationContext, AlarmReceiver::class.java).apply {
                                putExtra("notificationId", ++notificationId)
                                putExtra("reminder", to_do)
                            },

                            PendingIntent.FLAG_CANCEL_CURRENT
                    )
            )


            month = month + 1 //month starsts from 0
            toast("already set $to_do at $year/$month/$day $hour : $minute")
        }
    }
}



