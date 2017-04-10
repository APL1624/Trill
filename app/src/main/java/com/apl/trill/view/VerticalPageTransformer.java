package com.apl.trill.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2017/4/10.
 */
public class VerticalPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if(position<-1){
            page.setAlpha(0);
        }else if(position<=1){
            page.setAlpha(1);
            page.setTranslationX(page.getWidth()*-position);
            float yPosition = position * page.getHeight();
            page.setTranslationY(yPosition);
        }else { // (1,+Infinity]
            // This page is way off-screen to the right.
           page.setAlpha(0);
        }
    }
}
