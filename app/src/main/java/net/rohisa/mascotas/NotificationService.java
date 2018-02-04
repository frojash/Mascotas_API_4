package net.rohisa.mascotas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.media.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import net.rohisa.mascotas.MainActivity;
import net.rohisa.mascotas.R;

/**
 * Created by frojash on 1/29/18.
 */

public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("Tab",1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icons8_cat_footprint_filled_50)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());


        //super.onMessageReceived(remoteMessage);
    }
}
