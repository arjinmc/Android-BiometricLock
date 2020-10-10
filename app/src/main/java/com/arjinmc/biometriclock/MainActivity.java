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
import com.arjinmc.biometriclock.fingerprint.FingerprintConfig;
import com.arjinmc.biometriclock.fingerprint.FingerprintUtil;
import com.arjinmc.biometriclock.fingerprint.model.FingerprintEnrollStatus;

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

        FingerprintConfig fingerprintConfig = FingerprintConfig.getInstance(this);
        fingerprintConfig.setTitle(R.string.biometriclock_authenticate_fingerprint_dialog_title);
        fingerprintConfig.setTouchSensorTips(R.string.biometriclock_fingerprint_touch_sensor);

        mTvSupportFingerPrint = findViewById(R.id.tv_support_finger_print);
        mTvSupportFingerPrint.setText("Support Finger Print: " + FingerprintUtil.isSupport(this));

        mTvHasEnrolled = findViewById(R.id.tv_has_enrolled);
        mTvHasEnrolled.setText("Has Enrolled:" + FingerprintEnrollStatus.getStatusName(
                FingerprintUtil.hasEnrolledStatus(this)));

        mBtnAuthenticate = findViewById(R.id.btn_authenticate);
        mBtnAuthenticate.setOnClickListener(v -> {
            checkAuthenticate();
        });

        checkPermission();

        if (!FingerprintUtil.isSupport(this)) {
            return;
        }
    }

    private void checkAuthenticate() {

        if (!FingerprintUtil.isSupport(this)) {
            return;
        }

//        if(FingerprintUtil.hasEnrolledStatus(this) == FingerprintEnrollStatus.STATUS_NONE_ENROLLED){
//            Toast.makeText(this,"You need to setup a fingerprint on your devices",Toast.LENGTH_SHORT).show();
//            return;
//        }
        FingerprintUtil.authenticate(this, new FingerprintAuthenticateCallback() {
            @Override
            public void onError(String errorMsg) {
                Log.e("authenticate", "onError:" + errorMsg);
                runOnUiThread(() -> Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onFailed() {
                Log.e("authenticate", "onFailed");
            }

            @Override
            public void onHasNoEnrolled() {
                Log.e("authenticate", "has no enrolled");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onSuccess() {
                Log.e("authenticate", "onSuccess");
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Authenticate fingerprint success!", Toast.LENGTH_SHORT).show());
            }
        });
    }

    /**
     * request permissions
     */
    private void checkPermission() {
        ActivityCompat.requestPermissions(this, mPermissions, 1);
    }
}