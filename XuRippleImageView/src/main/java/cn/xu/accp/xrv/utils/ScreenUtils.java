package cn.xu.accp.xrv.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Xu on 2016/11/17.
 */
public class ScreenUtils {
    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 设置为全屏显示
     */
    private void setFullScreen(Activity act) {
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 退出全屏显示
     */
    private void cancelFullScreen(Activity act) {
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
