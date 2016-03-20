package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private int[] res = {R.id.img_a,R.id.img_b,R.id.img_c,R.id.img_d,R.id.img_e};
    private List<ImageView> list_img = new ArrayList<ImageView>();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<res.length;i++){
            ImageView img = (ImageView) findViewById(res[i]);
            img.setOnClickListener(this);
            list_img.add(img);
        }
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.img_a:
                if(flag){
                    startAnim();
                }else{
                    closeAnim();
                }
                break;
            default:
                Toast.makeText(MainActivity.this,"click"+view.getId(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeAnim(){
        for(int i =1;i<res.length;i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(list_img.get(i),
                    "translationY",(res.length-i-1)*50f,0f );
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(list_img.get(i),
                    "translationX",(i-1)*50f,0f );
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(animator).with(animator1);
            animSet.setStartDelay(500);
            animSet.start();
            flag = true;
        }
    }
    private void startAnim() {
        for(int i =1;i<res.length;i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(list_img.get(i),
                    "translationY", 0f,(res.length-i-1)*50f );
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(list_img.get(i),
                    "translationX", 0f , (i-1)*50f );
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(animator).with(animator1);
            animSet.setStartDelay(500);
            animSet.start();
            flag = false;
        }
    }
}
