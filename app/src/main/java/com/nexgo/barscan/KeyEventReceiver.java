package com.nexgo.barscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KeyEventReceiver extends BroadcastReceiver {
    private static final String TAG = "KeyEventReceiver";

    public KeyEventReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("DeviceId", 0);
        int code = intent.getIntExtra("KeyCode", 0);
        Log.d(TAG, "DeviceID:" + id + " Code:" + code);
    }
}
