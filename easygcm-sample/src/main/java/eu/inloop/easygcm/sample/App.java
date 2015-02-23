package eu.inloop.easygcm.sample;

import android.app.Application;
import android.os.Bundle;

import eu.inloop.easygcm.GcmListener;
import eu.inloop.easygcm.WakeLockRelease;

public class App extends Application implements GcmListener {

    private static final String SENDER_ID = "835909578313"; // easygcm-sample project

    @Override
    public void onMessage(String s, Bundle bundle, WakeLockRelease wakeLockRelease) {
        System.out.println("### message: " + s);
        System.out.println("### bundle:");
        for (String key: bundle.keySet()) {
            System.out.println("> " + key + ": " + bundle.get(key));
        }
        wakeLockRelease.release();
    }

    @Override
    public void sendRegistrationIdToBackend(String registrationId) {
        
    }

    @Override
    public String getSenderId() {
        return SENDER_ID;
    }

}
