package zls.app.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zls.app.library.MyApp;
import zls.app.library.R;
import zls.app.library.util.DimenUtil;


/**
 * created by zls
 * a simple custom view used for navigation of views
 */
public class NavBar extends RelativeLayout {
    private static final int ITEM_COUNT = 3;
    private static final int ITEM_BACKGROUND = MyApp.context.getResources().getColor(R.color.nar_bar_item_bg);
    private static final int ITEM_TEXT_COLOR = MyApp.context.getResources().getColor(R.color.nar_bar_text_color);
    private static final int ITEM_TEXT_SIZE = DimenUtil.sp2px(18);
    private static final int BAR_HEIGHT = DimenUtil.dip2px(2);
    private static final int BAR_BACKGROUND = MyApp.context.getResources().getColor(R.color.nar_bar_bar_bg);

    private int itemCount;
    private int itemBackground;
    private int itemTextColor;
    private int itemTextSize;
    private int barBackground;
    private int barHeight;
    private int barWidth;
    private int barLeftMargin;
    private int currentIndex;

    private LinearLayout tvHolder;
    private View bar;
    private TextView[] tvs;
    private float ration;

    public NavBar(Context context) {
        this(context, null);
    }

    public NavBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        init();
    }

    private void init() {
        barWidth = 0;
        barLeftMargin = 0;
        tvs = new TextView[itemCount];
        currentIndex = 0;
        ration = 0;

        this.setBackgroundColor(itemBackground);

        bar = new View(this.getContext());
        RelativeLayout.LayoutParams lp_bar = new LayoutParams(0, 0);
        bar.setLayoutParams(lp_bar);
        bar.setBackgroundColor(barBackground);
        bar.setId(R.id.id_for_nav_bar);
        this.addView(bar);


        tvHolder = new LinearLayout(this.getContext(), null);
        RelativeLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tvHolder.setLayoutParams(lp);
        tvHolder.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0, n = itemCount; i < n; i++) {
            LinearLayout.LayoutParams lp_text = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            lp_text.weight = 1;
            TextView tv = new TextView(this.getContext());
            tv.setLayoutParams(lp_text);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(DimenUtil.px2sp(itemTextSize));
            tv.setTextColor(itemTextColor);
            tvs[i] = tv;
            tvHolder.addView(tv);
        }
        this.addView(tvHolder);

    }

    public void setNavText(String[] texts){
        int n = texts.length;
        if(n != itemCount){
            throw new IllegalStateException("texts' length must equals itemCount");
        }
        for (int i = 0; i < n; i++) {
            tvs[i].setText(texts[i]);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int textHeight = this.itemTextSize;
        if(height < textHeight * 1.5 + barHeight){
            height = (int) (textHeight * 1.5 + barHeight);
        }

        barWidth = width / itemCount;
        barLeftMargin = (int) (barWidth * (currentIndex + ration));
        RelativeLayout.LayoutParams lp_bar = (LayoutParams) bar.getLayoutParams();
        lp_bar.width = width / itemCount;
        lp_bar.height = barHeight;
        lp_bar.leftMargin = barLeftMargin;
        bar.setLayoutParams(lp_bar);

        RelativeLayout.LayoutParams lp = (LayoutParams) tvHolder.getLayoutParams();
        lp.width = width;
        lp.height = height - barHeight;
        tvHolder.setLayoutParams(lp);
        measureChild(tvHolder, widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.NavBar, defStyle, 0);
        itemCount = a.getInt(R.styleable.NavBar_navBar_itemCount, ITEM_COUNT);
        itemBackground = a.getColor(R.styleable.NavBar_navBar_itemBackground, ITEM_BACKGROUND);
        itemTextColor = a.getColor(R.styleable.NavBar_navBar_itemTextColor, ITEM_TEXT_COLOR);
        itemTextSize = a.getDimensionPixelSize(R.styleable.NavBar_navBar_itemTextSize, ITEM_TEXT_SIZE);
        barHeight = a.getDimensionPixelOffset(R.styleable.NavBar_navBar_barHeight, BAR_HEIGHT);
        barBackground = a.getColor(R.styleable.NavBar_navBar_barBackground, BAR_BACKGROUND);
        a.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child;
        int cl = 0, ct = 0, cr = 0, cb = 0;
        for (int i = 0, n = this.getChildCount(); i < n; i++) {
            child = this.getChildAt(i);
            RelativeLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if(i == 0){
                cl = lp.leftMargin;
                ct = this.getMeasuredHeight() - lp.bottomMargin - lp.height;
                cr = cl + lp.width;
                cb = ct + lp.height;
            }else if(i == 1){
                cl = lp.leftMargin;
                ct = lp.topMargin;
                cr = cl + lp.width;
                cb = ct + lp.height;
            }
            child.layout(cl, ct, cr, cb);
        }
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    /**
     *
     * @param ratio should only be assigned value in [-1, 1]
     * ratio < 0 --> scroll to left
     * ratio > 0 --> scroll to right
     */
    public void onScroll(float ratio){
        if(ration > 1 || ration < -1){
            throw new IllegalStateException("invalid ratio(ratio = " + ratio + ")");
        }
        this.ration = ratio;
        requestLayout();
    }
    public void setCurrentIndex(int currentIndex){
        if(currentIndex > itemCount - 1 || currentIndex < 0){
            throw new IllegalStateException("invalid currentIndex(currentIndex = " + currentIndex + ")");
        }
        this.currentIndex = currentIndex;
    }

    public void onScroll(int currentIndex, float ratio){
        setCurrentIndex(currentIndex);
        onScroll(ratio);
    }

}
