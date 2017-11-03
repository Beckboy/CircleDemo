package com.example.hunter_j.hunter_circledemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.hunter_j.hunter_circledemo.R;
import com.example.hunter_j.hunter_circledemo.utils.SystemBarUtil;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by hunter_J on 2017/11/1.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isApplyKitKatTranslucency()){
            setTranslucentStaus(true);
        }
        if (isApplyColorPrimary()){
            setSystemBarTintDrawable(getResources().getDrawable(R.color.basic_color_primary));
        }
    }


    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    protected boolean isApplyColorPrimary(){
        return true;
    }

    private void setSystemBarTintDrawable(Drawable tintDrawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            SystemBarUtil mTintManager = new SystemBarUtil(this);
            if (tintDrawable != null){
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }
    }

    protected void setTranslucentStaus(boolean on){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window win = getWindow();
            WindowManager.LayoutParams winparams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on){
                winparams.flags |= bits;
            } else {
                winparams.flags &= ~bits;
            }
            win.setAttributes(winparams);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);

    }
}
