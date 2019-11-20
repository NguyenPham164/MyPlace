package hali.dephant.dephanvn.myplace.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import hali.dephant.dephanvn.myplace.R;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{


    private RelativeLayout layout;
    private ImageView imgLogo;
    private TextView txtLogo1, txtLogo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layoutMainAct);
        imgLogo = findViewById(R.id.imgMainAct_Logo);
        txtLogo1 = findViewById(R.id.txtLogo_1);
        txtLogo2 = findViewById(R.id.txtLogo_2);

        Animation transitionAnim = AnimationUtils.loadAnimation(this, R.anim.trasition_icon);
        imgLogo.setAnimation(transitionAnim);
        Animation trasitionAnimTxt1 = AnimationUtils.loadAnimation(this, R.anim.trasition_txtlogo_1);
        txtLogo1.setAnimation(trasitionAnimTxt1);
        Animation trasitionAnimTxt2 = AnimationUtils.loadAnimation(this, R.anim.trasition_txtlogo_2);
        txtLogo2.setAnimation(trasitionAnimTxt2);
        Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_backgroud);
        layout.setAnimation(alphaAnim);

        alphaAnim.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        }, 50);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
