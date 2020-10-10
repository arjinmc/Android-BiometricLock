package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;

import androidx.annotation.StringRes;

/**
 * Fingerprint Config
 * Created by Eminem Lo on 9/10/2020.
 * email: arjinmc@hotmail.com
 */
public final class FingerprintConfig {

    private static FingerprintConfig mFingerprintConfig;

    private static Context mContext;
    private static String mTitle;
    private static String mSubtitle;
    private static String mDescription;

    private FingerprintConfig() {
    }

    public static FingerprintConfig getInstance(Context context) {
        if (mFingerprintConfig == null) {
            mFingerprintConfig = new FingerprintConfig();
        }
        mContext = context;
        return mFingerprintConfig;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setTitle(@StringRes int title) {
        mTitle = mContext.getString(title);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public void setSubtitle(@StringRes int subtitle) {
        mSubtitle = mContext.getString(subtitle);
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setDescription(@StringRes int description) {
        mDescription = mContext.getString(description);
    }

    public String getDescription() {
        return mDescription;
    }
}
