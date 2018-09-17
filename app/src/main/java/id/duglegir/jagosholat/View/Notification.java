package id.duglegir.jagosholat.View;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import id.duglegir.jagosholat.R;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 16/09/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class Notification {

    private Context context;
    public static final int NOTIFICATION_ID = 1;

    public Notification(Context context) {
        this.context = context;
    }

    public void callNotif(String shalat){
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("id.duglegir.jagosholat");
        //menginisialiasasi intent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        //untuk memanggil activity di Notification
        /*
        Menmbangun atau mensetup Notification dengan NotificationCompat.Builder
         */
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher) //ikon notification
                .setContentTitle("Waktunya " + shalat)//judul konten
                .setContentIntent(pendingIntent)//memanggil object pending intent
                .setAutoCancel(true)//untuk menswipe atau menghapus notification
                .setContentText("Jangan lupa untuk shalat dan mencatat ibadah"); //isi text

        /*
        Kemudian kita harus menambahkan Notification dengan menggunakan NotificationManager
         */

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, builder.build()
        );
    }




}
