package fun.inaction.centertitletoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

public class CenterTitleToolbar extends Toolbar {

    /**
     * android 命名空间
     */
    private String androidNameSpace = "http://schemas.android.com/apk/res/android";

    /**
     * 中央标题的默认文字大小，单位为sp
     */
    private float defaultTextSize = 22;

    /**
     * 中央标题的默认文字颜色
     */
    private int defaultTextColor = Color.WHITE;

    /**
     * 中间的标题的TextView
     */
    private TextView titleTextView = new TextView(getContext());

    public CenterTitleToolbar(@NonNull Context context) {
        this(context,null);
    }

    public CenterTitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CenterTitleToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);

    }

    /**
     * 中央标题的初始化设置
     */
    private void init(AttributeSet attrs){
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        titleTextView.setTextColor(Color.WHITE);

        // 把标题添加到Toolbar中并居中
        LayoutParams layoutParams = new LayoutParams(Gravity.CENTER);
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        addView(titleTextView,layoutParams);

        // 把左侧的默认标题去掉
        setTitle("");

        // 设置属性

        // 如果没有xml属性，直接返回
        if(attrs == null){
            return;
        }

        // 如果没有设置颜色，则把颜色设为主题的 colorPrimary
        String attrBackground = attrs.getAttributeValue(androidNameSpace,"background");
        if(attrBackground == null){
            setBackgroundColor(getThemePrimaryColor(getContext()));
        }

        // 获取xml设置的属性
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.CenterTitleToolbar);

        // 设置文字
        String title = typedArray.getString(R.styleable.CenterTitleToolbar_centerTitle);
        setCenterTitle(title);

        // 设置文字大小

        float textSize = typedArray.getDimension(R.styleable.CenterTitleToolbar_centerTitleSize
                ,spToPx(defaultTextSize));
        textSize = pxToSp(textSize);
//        Log.e("tag","textSize="+textSize+"sp");
        setCenterTitleSize(textSize);

        // 设置文字颜色
        int textColor = typedArray.getColor(R.styleable.CenterTitleToolbar_centerTitleColor
                ,defaultTextColor);
        setCenterTitleColor(textColor);

        // 设置文字的Appearance
        int appearanceId = typedArray.getResourceId(R.styleable.CenterTitleToolbar_centerTitleTextAppearance,-1);
        if(appearanceId != -1){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setCenterTitleAppearance(appearanceId);
            }
        }

        typedArray.recycle();

    }

    /**
     * 设置中央标题的文字大小，默认为 22sp
     * @param unit 单位，例如 TypedValue.COMPLEX_UNIT_SP
     * @param size 大小
     */
    public void setCenterTitleSize(int unit,float size){
        titleTextView.setTextSize(unit,size);
    }

    /**
     * 设置中央标题的文字大小，单位为sp，默认为 22sp
     * @param size 大小，单位为sp
     */
    public void setCenterTitleSize(float size){
        titleTextView.setTextSize(size);
    }

    /**
     * 设置中央标题的文字颜色，默认为白色
     * @param color
     */
    public void setCenterTitleColor(int color){
        titleTextView.setTextColor(color);
    }

    /**
     * 设置中央标题的Appearance
     * @param resId
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setCenterTitleAppearance(int resId){
        titleTextView.setTextAppearance(resId);
    }

    /**
     * 设置中央标题的文字
     * @param title
     */
    public void setCenterTitle(CharSequence title){
        titleTextView.setText(title);
    }

    /**
     * 获取中央标题的TextView，要设什么自己设去吧
     * @return TextView
     */
    public TextView getCenterTitleView(){
        return titleTextView;
    }

    /**
     * 获取主题的 PrimaryColor
     * @param context
     * @return int颜色
     */
    public int getThemePrimaryColor(Context context){
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorPrimary });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    /**
     * 把px转换为sp
     * @param px
     * @return
     */
    public float pxToSp(float px){
        return px / getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 把sp转换为px
     * @param sp
     * @return
     */
    public float spToPx(float sp){
        return sp * getResources().getDisplayMetrics().scaledDensity;
    }

}
