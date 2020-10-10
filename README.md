# Android-BiometricLock

## Fingerprint
### Check status
check if support fingerprint
```code
FingerprintUtil.isSupport(context);
```
check if has set fingerprint. Some devices cannot get fingrprint counts that will return FingerprintEnrollStatus.STATUS_UNKNOWN .
```code
int result = FingerprintUtil.hasEnrolledStatus(context);
```

### Call authenticate fingerprint
The callback is ran in a thread.
```code
FingerprintUtil.authenticate(context, new FingerprintAuthenticateCallback() {
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
```

### Cancel authenticate fingerprint
```code
FingerprintUtil.cancelAuthenticate();
```

### Config for FingerprintAuthenticateDialog 
```code
FingerprintConfig fingerprintConfig = FingerprintConfig.getInstance(this);
//set title
fingerprintConfig.setTitle(R.string.biometriclock_authenticate_fingerprint_dialog_title);
//set subtitle
fingerprintConfig.setSubtitle("your own subtitle");
//set description
fingerprintConfig.setDescription();
//set touch senesor tips
fingerprintConfig.setTouchSensorTips(R.string.biometriclock_fingerprint_touch_sensor);
//set touch senser authenticate failed tips
fingerprintConfig.setTouchSensorFailedTips(R.string.biometriclock_fingerprint_touch_sensor_failed)

//You can define your own authenticate dialog extends AbstractFingerprintAuthenticateDialog for below android P
//remember to set the dialog instance like this
//fingerprintConfig.setAuthenticateDialog(dialog);
```

#### Customer FingerprintAuthenticateDialog
Firstly, extends ``AbstractFingerprintAuthenticateDialog``  
Then over write  ``onAuthenticateFailed()`` and ``onReset()``  
Remember to call ```FingerprintUtil.cancelAuthenticate()```  when your own cancel button of FingerprintAuthenticateDialog is clicked.

## License
```code
   Copyright 2020 arjinmc

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```