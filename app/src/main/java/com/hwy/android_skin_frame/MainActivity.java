package com.hwy.android_skin_frame;

import android.Manifest;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.hwy.android_skin_frame.base.AppManager;
import com.hwy.android_skin_frame.base.SkinActivity;
import com.hwy.android_skin_frame.widget.MyView;
import com.hwy.permission.PermissionHelper;
import com.hwy.skin.SkinManager;
import com.hwy.skin.SkinResource;
import com.hwy.skin.config.SkinUtil;

import java.io.File;

public class MainActivity extends SkinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("第 " + AppManager.getInstance().getSize() + " 个 Activity");

    }

    /**
     * 扩展当前页面的自定义View的换肤
     *
     * @param view
     * @param skinResource
     */
    @Override
    public void onSkinChange(View view, SkinResource skinResource) {
        super.onSkinChange(view, skinResource);
        ColorStateList color = null;
        switch (view.getId()) {
            case R.id.my_view:
                color = skinResource.getColorByName("main_color");
                ((MyView) view).setColor(color.getDefaultColor());
                break;
        }
    }

    public void exchangeSkin(View view) {
        PermissionHelper.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, new PermissionHelper.OnRequestPermissionListener() {
            @Override
            public void onPermissionGranted() {
                String currSkinPath = SkinUtil.getInstance(MainActivity.this).getSkinPath();
                if (TextUtils.isEmpty(currSkinPath)) {
                    SkinManager.getInstance().loadSkin(Environment.getExternalStorageDirectory().getAbsolutePath()
//                            + File.separator + "Download" + File.separator + "target.skin");
                            + File.separator + "target.skin");
                } else {
                    SkinManager.getInstance().restoreSkin();
                }
            }

            @Override
            public void onPermissionDenied() {
                PermissionHelper.showTipsDialog(MainActivity.this);
            }
        });
    }

    public void startNextPage(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
