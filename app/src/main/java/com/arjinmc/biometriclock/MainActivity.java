package com.arjinmc.biometriclock;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.arjinmc.biometriclock.fingerprint.FingerPrintAuthenticateCallback;
import com.arjinmc.biometriclock.fingerprint.FingerPrintUtil;

public class MainActivity extends AppCompatActivity {

    private String[] mPermissions = new String[]{
            Manifest.permission.USE_FULL_SCREEN_INTENT,
            Manifest.permission.USE_BIOMETRIC
    };

    private TextView mTvSupportFingerPrint;
    private TextView mTvHasEnrolled;
    private Button mBtnAuthenticate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvSupportFingerPrint = findViewById(R.id.tv_support_finger_print);
        mTvSupportFingerPrint.setText("Support Finger Print: " + FingerPrintUtil.isSupport(this));

        mTvHasEnrolled = findViewById(R.id.tv_has_enrolled);
        mTvHasEnrolled.setText("Has Enrolled:" + FingerPrintUtil.hasEnrolled(this));

        mBtnAuthenticate = findViewById(R.id.btn_authenticate);
        mBtnAuthenticate.setOnClickListener(v -> {
            checkAuthenticate();
        });
    }

    private void checkAuthenticate() {
        FingerPrintUtil.authenticate(this, new FingerPrintAuthenticateCallback() {
            @Override
            public void onError(int errorCode, String errorMsg) {
                Log.e("authenticate", "onError" + errorMsg);
            }

            @Override
            public void onSuccess() {
                Log.e("authenticate", "onSuccess");

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    /**
     * request permissions
     */
    private void checkPermission() {

    }
}