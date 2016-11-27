package cn.xu.accp.xrv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import cn.xu.accp.xrv.widget.XuRippleImageView;

public class MainAct extends AppCompatActivity implements View.OnClickListener{
    private XuRippleImageView rippleImageView,rippleImageView2,rippleImageView3;
    private Button btn_start,btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_stop=(Button)findViewById(R.id.btn_stop);
        rippleImageView=(XuRippleImageView)findViewById(R.id.rippleImageView);
        rippleImageView2=(XuRippleImageView)findViewById(R.id.rippleImageView2);
        rippleImageView2.setUrl("http://v1.qzone.cc/avatar/201308/31/14/10/522188dc53f3f929.jpg!200x200.jpg");
        rippleImageView3=(XuRippleImageView)findViewById(R.id.rippleImageView3);
        btn_stop.setOnClickListener(this);
        btn_start.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                //开始动画
                rippleImageView.startWaveAnim();
                rippleImageView2.startWaveAnim();
                rippleImageView3.startWaveAnim();
                break;
            case R.id.btn_stop:
                //停止动画
                rippleImageView.stopWaveAnim();
                rippleImageView2.stopWaveAnim();
                rippleImageView3.stopWaveAnim();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null!=rippleImageView){
            rippleImageView.startWaveAnim();
            rippleImageView2.startWaveAnim();
            rippleImageView3.startWaveAnim();
        }
        Log.e("xu","onResume +++++++++++++开始动画");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止动画
        rippleImageView.stopWaveAnim();
        rippleImageView2.stopWaveAnim();
        rippleImageView3.stopWaveAnim();
        Log.e("xu","onPause +++++++++++++停止动画");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止动画
        rippleImageView.stopWaveAnim();
        rippleImageView2.stopWaveAnim();
        rippleImageView3.stopWaveAnim();
        Log.e("xu","onDestroy +++++++++++++停止动画");
    }
}
