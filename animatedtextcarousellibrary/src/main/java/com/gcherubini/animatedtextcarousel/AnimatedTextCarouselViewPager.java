package com.gcherubini.animatedtextcarousel;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.List;


public class AnimatedTextCarouselViewPager extends ViewPager {
    public AnimatedTextCarouselViewPager(Context context) {
        super(context);
        updateComponentPadding();
    }

    public AnimatedTextCarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateComponentPadding();
    }

    private void updateComponentPadding() {
        int horizontalPadding = getResources().getDimensionPixelSize(R.dimen.animated_text_carousel_view_pager_padding_horizontal);
        setPadding(horizontalPadding, getPaddingTop(), horizontalPadding, getPaddingBottom());
        requestLayout();
    }

    private AnimatedTextCarouselAdapter setCaroselAdapter(List<String> items) {
        AnimatedTextCarouselAdapter carroselAdapter = new AnimatedTextCarouselAdapter(this, items);
        setAdapter(carroselAdapter);
        return carroselAdapter;
    }

    public void setItems(List<String> items) {
        setPageTransformer(
                false,
                new AnimatedTextCarouselPageTransformer(this, setCaroselAdapter(items))
        );
    }


    public void setItemsWithListener(List<String> items, AnimatedTextCarouselListener listener) {
        setPageTransformer(
                false,
                new AnimatedTextCarouselPageTransformer(this, setCaroselAdapter(items), listener)
        );
    }
}
