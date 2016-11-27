package cn.xu.accp.xrv.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import cn.xu.accp.xrv.R;

/**
 * 圆形图片加载类
 * Created by Xu on 2016/11/27.
 */
public class RoundBitmapUtils {
    public static void roundImageView(String url, final ImageView img) {
        roundImageView(url, img, R.mipmap.uesr_icon);
    }
    public static void roundImageView(String url, final ImageView img,int defResId) {
        if (TextUtils.isEmpty(url)||url.lastIndexOf("/")==url.length()-1) {
            if(defResId==0)
                return;
            try {
                img.setImageResource(defResId);
            } catch (Throwable e) {
            }
            return;
        }
        ImageLoader.getInstance().displayImage(url,img,getOptions(defResId, new RoundedBitmapDisplayer(180)));
       /* ImageLoader.loadImageAsync(img,url,
                DisplayImageOptionsCfg.getInstance().getOptions(defResId, new CircleRoundedBitmapDisplayer(0)));*/

    }
    public static DisplayImageOptions getOptions(int res, BitmapDisplayer displayer) {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(res)
                .showImageForEmptyUri(res)
                .showImageOnFail(res)
                .cacheInMemory(true).cacheOnDisk(true)
                .displayer(displayer)
//                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
    }
}
