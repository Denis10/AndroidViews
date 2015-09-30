package vodolazskiy.denis.customviews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class FunnyProgressDialog extends RelativeLayout {

    private TextView txtMessage;

//    public FunnyProgressDialog(Context context) {
//        super(context);
//    }

    public FunnyProgressDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FunnyProgressDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FunnyProgressDialog(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (context == null) {
            return;
        }
        Resources.Theme theme = context.getTheme();
        if (theme == null) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        RelativeLayout layout =(RelativeLayout) inflater.inflate(R.layout.animated_progress, null);

        txtMessage = (TextView) layout.findViewById(R.id.txtMessage);
        ImageView primaryCircle = (ImageView)layout.findViewById(R.id.imgPrimary);
        ImageView accentCircle = (ImageView)layout.findViewById(R.id.imgAccent);
        ImageView greenCircle = (ImageView)layout.findViewById(R.id.imgGreen);
        ImageView violetCircle = (ImageView)layout.findViewById(R.id.imgViolet);

        AnimatorSet primarySet = createScale(primaryCircle);
        AnimatorSet accentSet = createScale(accentCircle);
        AnimatorSet greenSet = createScale(greenCircle);
        AnimatorSet violetSet = createScale(violetCircle);

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(primarySet);
        animatorSet.play(accentSet).after(primarySet);
        animatorSet.play(greenSet).after(accentSet);
        animatorSet.play(violetSet).after(greenSet);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

        addView(layout);
    }

    private AnimatorSet createScale(ImageView circle){
        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(circle, "scaleX", 1.0f, 1.5f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(circle, "scaleY", 1.0f, 1.5f);
        scaleUpX.setDuration(100);
        scaleUpY.setDuration(100);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(circle, "scaleX", 1.5f, 1.0f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(circle, "scaleY", 1.5f, 1.0f);
        scaleDownX.setDuration(100);
        scaleDownY.setDuration(100);

        AnimatorSet upSet = new AnimatorSet();
        upSet.play(scaleUpX).with(scaleUpY);
        AnimatorSet downSet = new AnimatorSet();
        downSet.play(scaleDownX).with(scaleDownY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(upSet).before(downSet);

        return animatorSet;
    }

    public String getText(){
        return txtMessage.getText().toString();
    }

    public void setText(String message){
        txtMessage.setText(message);
    }

    public void setText(int message){
        txtMessage.setText(message);
    }
}
