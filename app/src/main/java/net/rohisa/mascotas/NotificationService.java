package net.rohisa.mascotas;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;
import android.widget.Toast;

import net.rohisa.mascotas.restApi.ConstantesRestApi;
import net.rohisa.mascotas.restApi.EndpointsApi;
import net.rohisa.mascotas.restApi.adapter.RestApiAdapter;
import net.rohisa.mascotas.restApi.model.UsuarioResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        ConstantesRestApi.USUARIO_OTRO = remoteMessage.getData().get("usuario").toString();
        try {
            obtenerIdUsuario2(ConstantesRestApi.USUARIO_OTRO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("AQUII2" , ConstantesRestApi.USUARIO_OTRO_ID);


        //Intent a ver el propio usuario
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("Tab", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        //Intent a ver el propio usuario
        Intent i2 = new Intent();
        i2.putExtra("usuario", ConstantesRestApi.USUARIO_OTRO);
        i2.putExtra("usuarioId", ConstantesRestApi.USUARIO_OTRO_ID);
        i2.setAction("SEGUIR_USUARIO");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);


        //Intent a ver otro usuario
        Intent i3 = new Intent(this, OtroUsuario.class);
        i3.putExtra("usuario", ConstantesRestApi.USUARIO_OTRO);
        i3.putExtra("usuarioId", ConstantesRestApi.USUARIO_OTRO_ID);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, i3, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action1 = new NotificationCompat.Action.Builder(R.drawable.ic_full_view_me, "Ver mi Perfil", pendingIntent).build();
        NotificationCompat.Action action2 = new NotificationCompat.Action.Builder(R.drawable.ic_full_follow, "Dar Follow", pendingIntent2).build();
        NotificationCompat.Action action3 = new NotificationCompat.Action.Builder(R.drawable.ic_full_view_you, "Ver Usuario", pendingIntent3).build();

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true);
        //.setBackground()
        //.setGravity(Gravity.CENTER_VERTICAL)
        //fondos de pantalla,

        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icons8_cat_footprint_filled_50)
                .setContentTitle("Notificacion Phone")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action1))
                .extend(wearableExtender.addAction(action2))
                .extend(wearableExtender.addAction(action3));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());


        //super.onMessageReceived(remoteMessage);
    }


    public void seguirUsuarioinstagram(String fotoId) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaSearch = restApiAdapter.construyeGsonDeserializadorSearch();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaSearch);
        String url = ConstantesRestApi.ROOT_URL + ConstantesRestApi.KEY_GET_MEDIA +
                fotoId + ConstantesRestApi.KEY_LIKES;
        retrofit2.Call<Gson> UsuarioResponseCall = endpointsApi.setLikeMedia(url, ConstantesRestApi.ACCESS_TOKEN);


    }

    public void obtenerIdUsuario2(String usuario) throws IOException {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaSearch = restApiAdapter.construyeGsonDeserializadorSearch();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionesRestApiInstagram(gsonMediaSearch);
        String url = ConstantesRestApi.ROOT_URL + ConstantesRestApi.KEY_GET_SEARCH +
                usuario + ConstantesRestApi.URL_GET_SEARCH;
        retrofit2.Call<UsuarioResponse> usuarioResponseCall = endpointsApi.getSearchUserCode(url);

        UsuarioResponse usuarioResponse = usuarioResponseCall.execute().body();
        ConstantesRestApi.USUARIO_OTRO_ID = usuarioResponse.getId();
        Log.d("AQUII" , ConstantesRestApi.USUARIO_OTRO_ID);

//        UsuarioResponseCall.enqueue (new Callback<UsuarioResponse>() {
//            @Override
//            public void onResponse(retrofit2.Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
//                UsuarioResponse usuarioResponse = response.body();
//                ConstantesRestApi.USUARIO_OTRO_ID = usuarioResponse.getId();
//            }
//
//            @Override
//            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
//                Log.e("Fallo en la conexion", t.toString());
//            }
//        });
    }

}

//NOTIFICACIONES SIN SOPORTE PARA WEAR
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
////        super.onMessageReceived(remoteMessage);
//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        Intent i = new Intent(this, MainActivity.class);
//        i.putExtra("Tab",1);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
//        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.icons8_cat_footprint_filled_50)
//                .setContentTitle("Notificacion")
//                .setContentText(remoteMessage.getNotification().getBody())
//                .setAutoCancel(true)
//                .setSound(sonido)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());
//
//
//        //super.onMessageReceived(remoteMessage);
//    }
