package maxi_160514.custom;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maxi.weixiao.R;

import java.util.List;

/**
 * Created by mingzhi.yuan on 12/14/16.
 */

public class RecyclePageView extends LinearLayout {

    static final String TAG = "RecyclePageView";
    static boolean debug = false;
    private int width = 0;
    private int height = 0;
    private float firstX = 0;
    private float lastX = 0;
    private float preX = 0;
    private float detaX = 0;
    private Context context = null;
    private View r1View = null;
    private View r2View = null;
    private View currenView = null;
    private View l1View = null;
    private View l2View = null;
    private ValueAnimator preAnimator = null;
    private boolean isAnimating = false;
    private VelocityTracker mVT = VelocityTracker.obtain();
    private float xVelocity = 0;
    private float lastCurrenX = 0;
    private List<String> mData = null;
    private int currenLocation = 0;
    private SlidePageCallBack callBack = null;


    public RecyclePageView(Context context) {
        super(context);
    }

    public RecyclePageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Context context, SlidePageCallBack cb, int width, int height) {

        callBack = cb;
        this.context = context;
        this.width = width;
        this.height = height;
        initPageView();
    }

    public void setData(List list, int position) {
        if (debug) Log.d("kk" + TAG, "setData-----list:" + list.size());
        mData = list;
        currenLocation = position;
        updatePage();
    }

    public void updatePage() {
        if (debug) Log.d("kk" + TAG, "updatePage--------");

        if (mData != null && mData.size() > 0 && currenLocation >= 0 && currenLocation < mData.size()) {
            setText(currenView, mData.get(currenLocation));
            if (debug)
                Log.d("kk" + TAG, "setData--11--currenLocation:" + currenLocation + " text:" + mData.get(currenLocation));
            if (currenLocation - 1 >= 0) {
                setText(l1View, mData.get(currenLocation - 1));
                if (currenLocation - 2 >= 0) {
                    setText(l2View, mData.get(currenLocation - 2));
                }
            }
            if (currenLocation + 1 < mData.size()) {
                setText(r1View, mData.get(currenLocation + 1));
                if (currenLocation + 2 < mData.size()) {
                    setText(r2View, mData.get(currenLocation + 2));
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = event.getX();
                preX = event.getX();
                lastCurrenX = currenView.getX();

                if (isAnimating) {
                    //   Log.d("kk" + TAG, "onTouchEvent---ACTION_DOWN-event.getX():" + event.getX() + " currenView:" + currenView.getX());
                    preAnimator.cancel();
                    //  preAnimator.end();
                    isAnimating = false;
                }
                mVT.clear();

                return true;
            case MotionEvent.ACTION_MOVE:
                mVT.addMovement(event);
                mVT.computeCurrentVelocity(1000);
                xVelocity = mVT.getXVelocity();
                //   Log.d("kk" + TAG, "onTouchEvent---ACTION_MOVE-event.getXVelocity():" + mVT.getXVelocity());
                detaX = event.getX() - preX;
                l2View.offsetLeftAndRight((int) detaX);
                l1View.offsetLeftAndRight((int) detaX);
                currenView.offsetLeftAndRight((int) detaX);
                r1View.offsetLeftAndRight((int) detaX);
                r2View.offsetLeftAndRight((int) detaX);
                preX = event.getX();

                if (lastCurrenX >= 0 - width && currenView.getX() < 0 - width) {
                    //        Log.d("kk" + TAG, "onTouchEvent---ACTION_MOVE---over:" + event.getX() + " currenView:" + currenView.getX());
                    resetPage(true);
                } else if (lastCurrenX <= width && currenView.getX() > width) {
                    resetPage(false);
                }
                lastCurrenX = currenView.getX();

                break;


            case MotionEvent.ACTION_UP:
                lastX = event.getX();
                if (lastX - firstX > 0) {//右滑
                    if ((Math.abs(xVelocity) > 2000 || currenView.getX() > width / 2) && currenLocation - 1 >= 0) {//To next page
                        beginAnimation(0 - l1View.getX(), true);
                    } else {//keep curren page
                        beginAnimation(0 - currenView.getX(), false);
                    }
                } else {//左滑
                    if ((Math.abs(xVelocity) > 2000 || r1View.getX() < width / 2) &&
                            mData != null && mData.size() > 0 && currenLocation + 1 < mData.size()) {//To next page
                        beginAnimation(0 - r1View.getX(), true);
                    } else {//keep curren page
                        beginAnimation(width - r1View.getX(), false);
                    }
                }

                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    public void setText(View v, String str) {
        if (v != null) {
            TextView tv = (TextView) v.findViewById(R.id.tv);
            tv.setText(str);
        }
    }

    int endCount = 0;
    int preValue = 0;

    private void beginAnimation(final float toXDelta, final boolean success) {
        if (debug) Log.d("kk" + TAG, "beginAnimation----------------------");
        isAnimating = true;
        endCount = 0;
        preValue = 0;


        preAnimator = ValueAnimator.ofInt(0, (int) toXDelta);
        preAnimator.setDuration(200);

        preAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //     Log.d("kk", "beginAnimation--------onAnimationEnd-----------------currenView.getX():" + currenView.getX());
                if (Math.abs(currenView.getX()) == width) {
                    slideEnd(toXDelta > 0 ? false : true, success);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        preAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                l2View.offsetLeftAndRight(curValue - preValue);
                l1View.offsetLeftAndRight(curValue - preValue);
                currenView.offsetLeftAndRight(curValue - preValue);
                r1View.offsetLeftAndRight(curValue - preValue);
                r2View.offsetLeftAndRight(curValue - preValue);
                preValue = curValue;
            }
        });

        preAnimator.start();
    }


    public void slideEnd(boolean toLeft, boolean success) {

        endCount++;
        if (success) {
            resetPage(toLeft);
        }
        isAnimating = false;
        //     Log.d("kk", "slideEnd-------------------end");
    }

    public void resetPage(boolean toLeft) {
        if (debug) Log.d("kk" + TAG, "resetPage----------------------");
        //      Log.d("kk", "resetPage-------------------");
        if (toLeft) {
            View temp = l2View;
            l2View = l1View;
            l1View = currenView;
            currenView = r1View;
            r1View = r2View;

            r2View = temp;
            if (currenLocation + 3 < mData.size()) {
                setText(r2View, mData.get(currenLocation + 3));
            } else {
                setText(r2View, "default");
            }
            currenLocation++;
            r2View.offsetLeftAndRight(width * 5);

            //          Log.d("kk", "slideEnd--toLeft-currenView:" + currenView.getX() + " r1View:" + r1View.getX());
        } else {
            View temp = r2View;
            r2View = r1View;
            r1View = currenView;
            currenView = l1View;
            l1View = l2View;
            l2View = temp;
            if (currenLocation - 3 >= 0) {
                setText(l2View, mData.get(currenLocation - 3));
            } else {
                setText(l2View, "default");
            }
            currenLocation--;
            l2View.offsetLeftAndRight(0 - width * 5);

            //          Log.d("kk", "slideEnd-toRight-currenView:" + currenView.getX() + " l1View:" + l1View.getX());
        }
        callBack.pageEnd(mData.size(), currenLocation);
    }

    TextView kk;

    private void initPageView() {
        if (debug) Log.d("kk" + TAG, "initPageView----------------------");
        View view = null;
        ViewGroup.LayoutParams lp = null;
        TextView tv = null;

        //l2
        view = LayoutInflater.from(context).inflate(R.layout.page_view, null);
        kk = tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("0000");
        //    tv.setBackgroundColor(Color.CYAN);
        addView(view);
        lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);

        lp = tv.getLayoutParams();
        lp.width = width;
        lp.height = height;
        tv.setLayoutParams(lp);

        l2View = view;
        l2View.setX(0 - width * 2);


        view = LayoutInflater.from(context).inflate(R.layout.page_view, null);
        kk = tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("1111");
        //   tv.setBackgroundColor(Color.YELLOW);
        addView(view);
        lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);

        lp = tv.getLayoutParams();
        lp.width = width;
        lp.height = height;
        tv.setLayoutParams(lp);

        l1View = view;
        l1View.setX(0 - width * 2);


        //currenView
        view = LayoutInflater.from(context).inflate(R.layout.page_view, null);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("2222");
        //  tv.setBackgroundColor(Color.RED);
        addView(view);
        lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);

        lp = tv.getLayoutParams();
        lp.width = width;
        lp.height = height;
        tv.setLayoutParams(lp);

        currenView = view;
        currenView.setX(0 - width * 2);


        //r1
        view = LayoutInflater.from(context).inflate(R.layout.page_view, null);
        tv = (TextView) view.findViewById(R.id.tv);
        //  tv.setBackgroundColor(Color.BLUE);
        tv.setText("3333");
        addView(view);
        lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);

        lp = tv.getLayoutParams();
        lp.width = width;
        lp.height = height;
        tv.setLayoutParams(lp);

        r1View = view;
        r1View.setX(0 - width * 2);


        //r2
        view = LayoutInflater.from(context).inflate(R.layout.page_view, null);
        tv = (TextView) view.findViewById(R.id.tv);
        //  tv.setBackgroundColor(Color.GREEN);
        tv.setText("4444");
        addView(view);
        lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);

        lp = tv.getLayoutParams();
        lp.width = width;
        lp.height = height;
        tv.setLayoutParams(lp);

        r2View = view;
        r2View.setX(0 - width * 2);

    }


    public void setPageBg(String color) {
        int c = Color.parseColor(color);

        setBackgroundColor(c);
        l2View.setBackgroundColor(c);
        l1View.setBackgroundColor(c);
        currenView.setBackgroundColor(c);
        r1View.setBackgroundColor(c);
        r1View.setBackgroundColor(c);
    }

    public interface SlidePageCallBack {
        void pageEnd(int len, int curren);
    }
}
