package cn.xu.accp.xrv.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.xu.accp.xrv.R;
import cn.xu.accp.xrv.utils.DensityUtil;
import cn.xu.accp.xrv.utils.RoundBitmapUtils;


/**
 * 带icon的圆环水波扩散view
 * Created by Xu on 2016/11/26.
 */
public class XuRippleImageView extends RelativeLayout {

    private static final int SHOW_SPACING_TIME=500;
    private static final int MSG_WAVE2_ANIMATION = 1;
    private static final int MSG_WAVE3_ANIMATION = 2;
    /**三张波纹图片*/
    private static final int SIZE =3 ;

    /**动画默认循环播放时间*/
    private  int show_spacing_time=SHOW_SPACING_TIME;
    /**初始化动画集*/
    private AnimationSet [] mAnimationSet=new AnimationSet[SIZE];
    /**水波纹图片*/
    private ImageView [] imgs=new ImageView[SIZE];
    /** 水波圆环默认资源 **/
    private int ripple_circle= R.drawable.ripple_circle;
    /**背景图片*/
    private ImageView img_bg;
    private int bg_icon=-1;
    /**网络图片地址**/
    private String url;
    /**背景图片的默认大小*/
    private float ripple_width= DensityUtil.dip2px(getContext(),25);
    private float ripple_heigth=DensityUtil.dip2px(getContext(),25);
    /**缩放度：默认变大比例*/
    private float ripple_scale=1.5f;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WAVE2_ANIMATION:
                    imgs[MSG_WAVE2_ANIMATION].startAnimation(mAnimationSet[MSG_WAVE2_ANIMATION]);
                    break;
                case MSG_WAVE3_ANIMATION:
                    imgs[MSG_WAVE2_ANIMATION].startAnimation(mAnimationSet[MSG_WAVE3_ANIMATION]);
                    break;
            }

        }
    };

    public XuRippleImageView(Context context) {
        super(context);
        initView(context);
    }

    public XuRippleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.circle_ripple);
        ripple_circle = typedArray.getResourceId(R.styleable.circle_ripple_ripple_circle_src, -1);
        bg_icon=typedArray.getResourceId(R.styleable.circle_ripple_centre_bg_src, -1);
        show_spacing_time = typedArray.getInt(R.styleable.circle_ripple_show_spacing_time, SHOW_SPACING_TIME);
        ripple_width = typedArray.getDimension(R.styleable.circle_ripple_ripple_width, -1);
        ripple_heigth = typedArray.getDimension(R.styleable.circle_ripple_ripple_heigth, -1);
        ripple_scale = typedArray.getFloat(R.styleable.circle_ripple_ripple_scale,-1);
        Log.e("xu","show_spacing_time="+show_spacing_time+" ripple_width="+ripple_width+"px  ripple_heigth="+ripple_heigth+"px");
        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        setLayout(context);
        for (int i = 0; i <imgs.length ; i++) {
            mAnimationSet[i]=initAnimationSet();
        }
    }

    /**
     * 开始动态布局
     */
    private void setLayout(Context context) {
        Log.e("xu", "getChildCount()+++++++" + getChildCount());
        //每次清空所有子view
        if (getChildCount() > SIZE + 1) {
            removeAllViews();
        }
        LayoutParams params = new LayoutParams((int) ripple_width, (int) ripple_heigth);
        //添加一个规则
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        /**添加水波纹图片*/
        for (int i = 0; i < SIZE; i++) {
            imgs[i] = new ImageView(context);
            imgs[i].setImageResource(ripple_circle);
            addView(imgs[i], params);
        }
        //中心图片
        if (TextUtils.isEmpty(url)) {
            if (bg_icon != -1) { //本地图片

                LayoutParams params_bg = new LayoutParams(params.width, params.height);
                //添加一个规则
                params_bg.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                /**添加背景图片*/
                img_bg = new ImageView(context);
                img_bg.setImageResource(bg_icon);
                addView(img_bg, params_bg);
            }
        } else { //圆形的网络图片
            LayoutParams params_bg = new LayoutParams(params.width, params.height);
            //添加一个规则
            params_bg.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            /**添加背景图片*/
            img_bg = new ImageView(context);

            Object object = getTag();
            if (object != null && TextUtils.equals(url, (String) object))
                return;
            if (TextUtils.isEmpty(url)) {
                img_bg.setImageResource(bg_icon);
                return;
            }
            setTag(url);
            RoundBitmapUtils.roundImageView(url, img_bg);
            addView(img_bg, params_bg);
        }
    }

    /**
     * 初始化动画集
     * @return
     */
    private AnimationSet initAnimationSet() {
        AnimationSet as = new AnimationSet(true);
        //缩放度：变大1.5倍
        ScaleAnimation sa = new ScaleAnimation(1f, ripple_scale, 1f, ripple_scale,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(show_spacing_time * 3);
        sa.setRepeatCount(Animation.INFINITE);// 设置循环
        //透明度
        AlphaAnimation aa = new AlphaAnimation(1, 0.1f);
        aa.setDuration(show_spacing_time * 3);
        aa.setRepeatCount(Animation.INFINITE);//设置循环
        as.addAnimation(sa);
        as.addAnimation(aa);
        return as;
    }

    //============================对外暴露的public方法=========================================
   /**开始水波纹动画**/
    public void startWaveAnim() {
        imgs[0].startAnimation(mAnimationSet[0]);
        mHandler.sendEmptyMessageDelayed(MSG_WAVE2_ANIMATION, show_spacing_time);
        mHandler.sendEmptyMessageDelayed(MSG_WAVE3_ANIMATION, show_spacing_time * 2);
    }
   /**停止水波纹动画**/
    public void stopWaveAnim() {
        for (int i = 0; i <imgs.length ; i++) {
            imgs[i].clearAnimation();
        }
    }
    /**获取播放的速度*/
    public int getShow_spacing_time() {
        return show_spacing_time;
    }
    /**设置播放的速度，默认是500毫秒*/
    public void setShow_spacing_time(int show_spacing_time) {
        this.show_spacing_time = show_spacing_time;
    }
    /**设置圆环扩散的图片，或者shape*/
    public void setRipple_circle(int ripple_circle) {
        this.ripple_circle = ripple_circle;
    }
    /**设置圆环中心icon*/
    public void setBg_icon(int bg_icon) {
        this.bg_icon = bg_icon;
    }
    /**设置圆环宽度 &中心icon 宽度*/
    public void setRipple_width(float ripple_width) {
        this.ripple_width = ripple_width;
    }
    /**设置圆环高度 &中心icon 高度*/
    public void setRipple_heigth(float ripple_heigth) {
        this.ripple_heigth = ripple_heigth;
    }
    /**设置水波扩散变大比例*/
    public void setRipple_scale(float ripple_scale) {
        this.ripple_scale = ripple_scale;
    }
    /**图片路径**/
    public void setUrl(String url) {
        this.url = url;
        setLayout(getContext());
    }
    public String getUrl() {
        return url;
    }
}
