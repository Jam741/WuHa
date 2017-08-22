package com.pisces.android.framworkerlibrary.utlis;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by 99165 on 2016/3/21.
 */
public class TextViewUtils {

    /**
     * 在代码中给TextView设置图片资源
     *
     * @param textView    需要设置图片资源的控件
     * @param drawableRes 图片资源ID
     * @param direction   方向 0 为left,1为top,2为right,3为bottom
     */
    public static void setDrawable(Context context, TextView textView, int drawableRes, int direction) {
        Drawable drawable = context.getResources().getDrawable(drawableRes);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (direction) {
            case 0:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
            case 1:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case 2:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case 3:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
            default:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
        }
    }


    /**
     * 在代码中给TextView设置图片资源
     *
     * @param textView    需要设置图片资源的控件
     * @param drawableRes 图片资源ID
     */
    public static void setDrawableToLeft(Context context, TextView textView, int drawableRes) {
        setDrawable(context, textView, drawableRes, 0);
    }

    public static void setDrawableToTop(Context context, TextView textView, int drawableRes) {
        setDrawable(context, textView, drawableRes, 1);
    }

    public static void setDrawableToRight(Context context, TextView textView, int drawableRes) {
        setDrawable(context, textView, drawableRes, 2);
    }

    public static void setDrawableToBottom(Context context, TextView textView, int drawableRes) {
        setDrawable(context, textView, drawableRes, 3);
    }

    public static void setDrawableToLeftAndRight(Context context, TextView textView, int leftDrawableRes, int rightDrawanleRes) {
        Drawable leftDrawable = context.getResources().getDrawable(leftDrawableRes);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        Drawable rightDrawanle = context.getResources().getDrawable(rightDrawanleRes);
        rightDrawanle.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
        textView.setCompoundDrawables(leftDrawable, null, rightDrawanle, null);
    }


    /**
     * 字符转全角，用于排版
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }




}
