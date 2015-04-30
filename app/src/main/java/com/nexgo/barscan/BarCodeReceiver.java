package com.nexgo.barscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BarCodeReceiver extends BroadcastReceiver {
    public BarCodeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String code = intent.getStringExtra("Code");
        Toast.makeText(context, code, Toast.LENGTH_SHORT).show();
    }
}
