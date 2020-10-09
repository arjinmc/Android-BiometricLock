package com.arjinmc.biometriclock;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.arjinmc.biometriclock.fingerprint.FingerprintAuthenticateCallback;
import com.arjinmc.biometriclock.fingerprint.FingerprintUtil;
import com.arjinmc.biometriclock.view.AuthenticateFingerprintDialog;

public class MainActivity extends AppCompatActivity {

    private String[] mPermissions = new String[]{
            Manifest.permission.USE_FULL_SCREEN_INTENT,
            Manifest.permission.USE_BIOMETRIC
    };

    private TextView mTvSupportFingerPrint;
    private TextView mTvHasEnrolled;
    private Button mBtnAuthenticate;

    private AuthenticateFingerprintDialog mAuthenticateFingerprintDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvSupportFingerPrint = findViewById(R.id.tv_support_finger_print);
        mTvSupportFingerPrint.setText("Support Finger Print: " + FingerprintUtil.isSupport(this));

        mTvHasEnrolled = findViewById(R.id.tv_has_enrolled);
        mTvHasEnrolled.setText("Has Enrolled:" + FingerprintUtil.hasEnrolled(this));

        mBtnAuthenticate = findViewById(R.id.btn_authenticate);
        mBtnAuthenticate.setOnClickListener(v -> {
            checkAuthenticate();
        });

        checkPermission();

        if (!FingerprintUtil.isSupport(this) || !FingerprintUtil.hasEnrolled(this)) {
            return;
        }
        mAuthenticateFingerprintDialog = new AuthenticateFingerprintDialog(this);
        mAuthenticateFingerprintDialog.setOnOptionClickListener(() -> {
            FingerprintUtil.cancelAuthenticate(MainActivity.this);
        });
    }

    private void checkAuthenticate() {

        if (!FingerprintUtil.isSupport(this) || !FingerprintUtil.hasEnrolled(this)) {
            return;
        }
        FingerprintUtil.authenticate(this, new FingerprintAuthenticateCallback() {
            @Override
            public void onError(int errorCode, String errorMsg) {
                Log.e("authenticate", "onError->code：" + errorCode + "\tmessage:" + errorMsg);
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                mAuthenticateFingerprintDialog.dismiss();
            }

            @Override
            public void onFailed() {
                Log.e("authenticate", "onFailed");
            }

            @Override
            public void onSuccess() {
                Log.e("authenticate", "onSuccess");
                mAuthenticateFingerprintDialog.dismiss();
                Toast.makeText(MainActivity.this, "Authenticate fingerprint success!", Toast.LENGTH_SHORT).show();
            }
        });
        mAuthenticateFingerprintDialog.show();
    }

    /**
     * request permissions
     */
    private void checkPermission() {
        ActivityCompat.requestPermissions(this, mPermissions, 1);
    }
}