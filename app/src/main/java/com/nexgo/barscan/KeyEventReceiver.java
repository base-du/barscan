package com.nexgo.barscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class KeyEventReceiver extends BroadcastReceiver {
    private static final String TAG = "KeyEventReceiver";

    private static StringBuffer mCache = new StringBuffer();

    public KeyEventReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int code = intent.getIntExtra("KeyCode", 0);
        int act = intent.getIntExtra("Action", 0);

        if (act == KeyEvent.ACTION_DOWN) return;

        if (code >= KeyEvent.KEYCODE_0 && code <= KeyEvent.KEYCODE_9) {
            mCache.append((char) ('0' + code - KeyEvent.KEYCODE_0));
        } else if (code >= KeyEvent.KEYCODE_A && code <= KeyEvent.KEYCODE_Z) {
            mCache.append((char) ('a' + code - KeyEvent.KEYCODE_A));
        } else if (code == KeyEvent.KEYCODE_ENTER) {
            Intent broadcast = new Intent("com.nexgo.barscan.CODE");
            broadcast.putExtra("Code", mCache.toString());
            context.sendBroadcast(broadcast);
            mCache.delete(0, mCache.length());
        }
    }
}
