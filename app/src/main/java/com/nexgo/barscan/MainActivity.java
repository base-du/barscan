package com.nexgo.barscan;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements InputManager.InputDeviceListener {
    private static final String scanDevDescriptor = "42847bbc5bd47647b10eb0421eef8f8f73c66da6";
    private InputManager mIm;
    private InputDevice mScandev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIm = (InputManager) getSystemService(Context.INPUT_SERVICE);
        mIm.registerInputDeviceListener(this, null);
        mIm.getInputDeviceIds(); // must run getInputDeviceIds first.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIm.unregisterInputDeviceListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyMultiple(final int keyCode, final int repeatCount, final KeyEvent event) {
        Log.d("onKeyMultiple", event.toString());
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyUp(final int keyCode, final KeyEvent event) {
        //Bar code scaner use OnkeyUp
        Log.d("onKeyUp", event.toString());
        //do something when the key is came from bar scan
        if (mScandev != null && mScandev.getId() == event.getDeviceId()) {
            //TODO
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        Log.d("onKeyDown", event.toString());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onInputDeviceAdded(final int deviceId) {
        Log.d("InputDevice", "> onInputDeviceAdded()");
        updateDev();
        listDev();
    }

    @Override
    public void onInputDeviceRemoved(final int deviceId) {
        Log.d("InputDevice", "> onInputDeviceRemoved()");
        updateDev();
    }

    @Override
    public void onInputDeviceChanged(final int deviceId) {
        Log.d("InputDevice", "> onInputDeviceChanged");
        updateDev();
    }

    private void updateDev() {
        final int[] devices = InputDevice.getDeviceIds();
        mScandev = null;
        for (int i = 0; i < devices.length; i++) {
            InputDevice device = InputDevice.getDevice(devices[i]);
            if (device != null) {
                if (scanDevDescriptor.equals(device.getDescriptor())) {
                    Log.d("InputDevice", "get Scan device ID:" + device.getId() + " " + device.getName());
                    mScandev = device;
                }
            }
        }
    }

    private void listDev() {
        final int[] devices = InputDevice.getDeviceIds();
        for (int i = 0; i < devices.length; i++) {
            InputDevice device = InputDevice.getDevice(devices[i]);
            if (device != null) {
                Log.d("InputDevice", device.toString());
            }
        }
    }

}
