# XuRippleImageView

# qq交流群：123965382
XuRippleImageView 是一个自定义的水波扩散view,在开源项目RippleImageView上做了些扩展，支持设置网络图片
<br/>感谢RippleImageView的[作者LiuJunb](https://github.com/LiuJunb/RippleImageView "")  

# 使用方法 

(1) 在xml里使用XuRippleImageView自定义控件
 
 ```java
  <cn.xu.accp.xrv.widget.XuRippleImageView
       android:id="@+id/rippleImageView"
       android:layout_width="48dp"
       android:layout_height="48dp"
       app:ripple_circle_src="@drawable/ripple_circle"// 圆环图片资源、项目中用的是shape
       app:centre_bg_src="@mipmap/uesr_icon"// 圆环中心的icon图片(local)
       app:show_spacing_time="500"// 动画循环间隔
       app:ripple_scale="1.5"// 水波扩散的倍率，如1.5 or 2倍
       app:ripple_width="30dp" // 这个宽高 设值 直接影响 圆环中心的icon图片 &水波大小
       app:ripple_heigth="30dp"/> 
  ```
  
(2) act的中代码调用
  
  ```java 
  xuRippleImageView=(XuRippleImageView)findViewById(R.id.rippleImageView);
  // 如果希望加载网络图片在xml设置的基础上调用setUrl即可
  xuRippleImageView.setUrl("http://v1.qzone.cc/avatar/201308/31/14/10/522188dc53f3f929.jpg!200x200.jpg");
  
    //开始动画
    rippleImageView.startWaveAnim();
    
    //停止动画
    rippleImageView.stopWaveAnim();
  ```
  
(3) 效果图有点失帧导致看起来不佳

  ![effect](https://raw.githubusercontent.com/yangxu1210/XuRippleImageView/master/XuRippleImageView/screenshot/effect.gif)
