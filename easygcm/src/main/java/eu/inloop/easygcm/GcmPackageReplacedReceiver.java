package eu.inloop.easygcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class GcmPackageReplacedReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, final Intent intent) {
        if (intent != null && intent.getAction().equals(Intent.ACTION_MY_PACKAGE_REPLACED)) {
            if (context.getApplicationContext() instanceof GcmListener) {
                Log.d(GcmHelper.TAG, "Received application update broadcast");
                final GcmListener gcmListener = ((GcmListener) context.getApplicationContext());
                GcmHelper.getInstance(gcmListener.getSenderId()).registerInBackground(context, new GcmHelper.RegistrationListener() {
                    @Override
                    public void onFinish() {
                        GcmBroadcastReceiver.completeWakefulIntent(intent);
                    }
                });
            } else {
                GcmBroadcastReceiver.completeWakefulIntent(intent);
                throw new IllegalStateException("Application must implement GcmListener interface!");
            }
        }
    }
}