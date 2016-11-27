package cn.xu.accp.xrv;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;

/**
 * Created by Xu on 2016/11/27.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initImageLoader(this);//初始化imageloader
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图像加载处理工具类初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(1000)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO);

//        if (LOG.DEBUG) {
//            builder.writeDebugLogs();
//        } else {
        L.writeLogs(false);//正式发布屏蔽log
//        }
        ImageLoaderConfiguration config = builder.build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }


}
