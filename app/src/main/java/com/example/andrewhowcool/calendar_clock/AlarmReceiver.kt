package com.example.andrewhowcool.calendar_clock

/**
 * Created by AndrewHowCool on 2017/11/20.
 */

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast



class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        (context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(
                intent!!.getIntExtra("notificationId", 0),
                Notification.Builder(context).apply {
                    setSmallIcon(android.R.drawable.ic_dialog_info)
                    setContentTitle("Calendar Clock Reminder")
                    setContentText(intent.getCharSequenceExtra("reminder"))
                    setWhen(System.currentTimeMillis())
                    setPriority(Notification.PRIORITY_DEFAULT)
                    setAutoCancel(true)
                    setDefaults(Notification.DEFAULT_ALL)
                    setContentIntent(PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0))
                }.build()
        )
    }
}