package com.arjinmc.madao.fingerprint;

import android.content.Context;

/**
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
interface IFingerPrintWrapper {

    /**
     * 是否支持指纹识别(硬件)
     *
     * @param context
     * @return
     */
    boolean isSupported(Context context);

    /**
     * 是否可用
     *
     * @param context
     * @return
     */
    boolean isEnable(Context context);

}
