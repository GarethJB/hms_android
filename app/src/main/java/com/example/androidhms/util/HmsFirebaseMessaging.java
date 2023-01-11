package com.example.androidhms.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.androidhms.R;
import com.example.androidhms.staff.messenger.ChatActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class HmsFirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Map<String, String> map = message.getData();
        getNotification(map.get("key"),
                map.get("title"),
                map.get("content"),
                map.get("name"));
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    private void getNotification(String key, String title, String content, String name) {
        String titleView = "";
        if (title.contains("#")) titleView = name;
        else titleView = title + " / " + name;
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("key", key);
        // PendingIntent 가 알림에 따라 update 되지 않는 오류때문에 cancel 후 한번 더 선언 (임시방편)
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_MUTABLE);
        pendingIntent.cancel();
        pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_MUTABLE);

        String channelId = "fcm_default_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.icon_message)
                        .setContentTitle(titleView)
                        .setContentText(content)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("fcm_default_channel",
                    "fcm_default_channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        } else {

        }
        notificationManager.notify(0, notificationBuilder.build());
    }

}
