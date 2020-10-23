package com.starway.starrobot.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.starway.starrobot.R;

public class IntroductionActivity extends AppCompatActivity {
    // 定义一个ViewFlipper
    private ViewFlipper flipper;

    private static Context context;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    // 定义一个手指触摸屏幕的x轴坐标
    private float startX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        context = getApplicationContext();
        // 初始化ViewFlipper
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btn1 = (Button) findViewById(R.id.findbutton1);
        btn2 = (Button) findViewById(R.id.findbutton2);
        btn3 = (Button) findViewById(R.id.findbutton3);
        btn4 = (Button) findViewById(R.id.findbutton4);
        // 给ViewFlipper添加内容。这里添加四个ImageView
        int[] images = new int[]{R.drawable.pc_imgface, R.drawable.pc_imgcard, R.drawable.pc_imgvisit, R.drawable.pc_imgtotal};
        flipper.addView(getImageView(images[0]));
        flipper.addView(getImageView(images[1]));
        flipper.addView(getImageView(images[2]));
        flipper.addView(getImageView(images[3]));
        //  设置自动切换时间间隔5秒
        flipper.setFlipInterval(5000);
        // 设置切换动画
        flipper.setInAnimation(this, R.anim.left_in);
        flipper.setOutAnimation(this, R.anim.left_out);
        // 开始切换
        flipper.startFlipping();

    }
    /**
     * 创建一个ImageView
     * @param resId
     *          图片资源Id
     * @return
     *          ImageView对象
     */
    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(resId);
        return image;
    }

    /**
     * 屏幕手势
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // 手指接触屏幕
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX(); // 将手指接触屏幕的X轴的坐标保存下来
                break;
            }
            // 手指滑动屏幕
            case MotionEvent.ACTION_UP: {
                //向右滑动
                if (event.getX() - startX > 100) {
                    flipper.setInAnimation(this, R.anim.right_in);
                    flipper.setOutAnimation(this, R.anim.right_out);
                    flipper.showPrevious();
                }
                //向左滑动
                if (startX - event.getX() > 100) {
                    flipper.setInAnimation(this, R.anim.left_in);
                    flipper.setOutAnimation(this, R.anim.left_out);
                    flipper.showNext();
                }
                break;
            }
        }
        return super.onTouchEvent(event);
    }

}
