package com.arjinmc.biometriclock.fingerprint.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arjinmc.biometriclock.fingerprint.FingerprintConfig;
import com.arjinmc.biometriclock.fingerprint.FingerprintUtil;
import com.arjinmc.biometriclock.fingerprint.R;


/**
 * Authenticate finger print dialog
 * Created by Eminem Lo on 9/10/2020.
 * email: arjinmc@hotmail.com
 */
public class FingerprintAuthenticateDialog extends Dialog {

    private TextView mTvTitle, mTvSubTitle, mTvDescription, mTvTouchSensorTips;
    private TextView mTvCancel;

    public FingerprintAuthenticateDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FingerprintAuthenticateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected FingerprintAuthenticateDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.biometriclock_dialog_authenticate_fingerprint, null);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvSubTitle = view.findViewById(R.id.tv_subtitle);
        mTvDescription = view.findViewById(R.id.tv_description);
        mTvTouchSensorTips = view.findViewById(R.id.tv_touch_sensor);

        FingerprintConfig fingerprintConfig = FingerprintConfig.getInstance(context);
        if (!TextUtils.isEmpty(fingerprintConfig.getTitle())) {
            mTvTitle.setText(fingerprintConfig.getTitle());
        }
        if (!TextUtils.isEmpty(fingerprintConfig.getSubtitle())) {
            mTvSubTitle.setText(fingerprintConfig.getTitle());
            mTvSubTitle.setVisibility(View.VISIBLE);
        } else {
            mTvSubTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(fingerprintConfig.getDescription())) {
            mTvDescription.setText(fingerprintConfig.getDescription());
            mTvDescription.setVisibility(View.VISIBLE);
        } else {
            mTvDescription.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(fingerprintConfig.getTouchSensorTips())) {
            mTvTouchSensorTips.setText(fingerprintConfig.getTouchSensorTips());
        }
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                FingerprintUtil.cancelAuthenticate(getContext());
            }
        });
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view, layoutParams);

        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.gravity = Gravity.CENTER;
        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.biometriclock_finggerprint_margin);
        attrs.width = getScreenWidth(getContext()) - padding * 2;
        getWindow().setAttributes(attrs);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setCanceledOnTouchOutside(false);
    }

    private int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = windowManager.getCurrentWindowMetrics();
            return windowMetrics.getBounds().width();
        } else {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.x;
        }
    }
}
