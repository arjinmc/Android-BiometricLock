package com.arjinmc.biometriclock.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arjinmc.biometriclock.R;

/**
 * Verify finger print dialog
 * Created by Eminem Lo on 9/10/2020.
 * email: arjinmc@hotmail.com
 */
public class AuthenticateFingerprintDialog extends Dialog {

    private TextView mTvCancel;
    private OnOptionClickListener mOnOptionClickListener;

    public AuthenticateFingerprintDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AuthenticateFingerprintDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected AuthenticateFingerprintDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_authenticate_fingerprint, null);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(v -> {
            dismiss();
            if (mOnOptionClickListener != null) {
                mOnOptionClickListener.onCancel();
            }
        });
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view, layoutParams);

        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.gravity = Gravity.CENTER;
        int padding = getContext().getResources().getDimensionPixelSize(R.dimen.margin);
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

    public void setOnOptionClickListener(OnOptionClickListener onOptionClickListener) {
        this.mOnOptionClickListener = onOptionClickListener;
    }

    public interface OnOptionClickListener {
        void onCancel();
    }
}
