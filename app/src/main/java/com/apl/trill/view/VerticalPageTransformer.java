package com.apl.trill.view;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/4/10.
 */
public class VerticalPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

            page.setAlpha(1);
            page.setTranslationX(page.getWidth()*-position);
            float yPosition = position * page.getHeight();
            page.setTranslationY(yPosition);

    }

}
