package com.gcherubini.animatedtextcarousel;

import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;


public class AnimatedTextCarouselPageTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private float fontScaleFactor;
    private float textSizePxl;
    private float deselectedItemAlpha;

    private AnimatedTextCarouselListener listener;
    private AnimatedTextCarouselAdapter adapter;
    private float lastOffset;


    public AnimatedTextCarouselPageTransformer(ViewPager pager, AnimatedTextCarouselAdapter adapter) {
        this.adapter = adapter;
        this.textSizePxl = pager.getResources().getDimensionPixelSize(R.dimen.animated_text_carousel_text_size);

        TypedValue alphaTypedValue = new TypedValue();
        pager.getResources().getValue(R.dimen.animated_text_carousel_deselected_text_alpha, alphaTypedValue, true);
        deselectedItemAlpha = alphaTypedValue.getFloat();

        TypedValue scaleFactorValue = new TypedValue();
        pager.getResources().getValue(R.dimen.animated_text_carousel_scale_factor, scaleFactorValue, true);
        fontScaleFactor = scaleFactorValue.getFloat();

        pager.addOnPageChangeListener(this);
    }

    public AnimatedTextCarouselPageTransformer(ViewPager pager, AnimatedTextCarouselAdapter adapter, AnimatedTextCarouselListener listener) {
        this(pager, adapter);
        this.listener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition, nextPosition;
        boolean isSwipingLeft = lastOffset > positionOffset;
        float realOffset;

        if (isSwipingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if (nextPosition > adapter.getCount() - 1
                || realCurrentPosition > adapter.getCount() - 1) {
            return;
        }

        TextView currentText = adapter.getCarroselTextAt(realCurrentPosition);
        TextView nextText = adapter.getCarroselTextAt(nextPosition);

        if (currentText != null) {
            currentText.setTextSize((fontScaleFactor * (1 - realOffset)) + textSizePxl);
            currentText.setAlpha(1 - realOffset + deselectedItemAlpha);
        }

        if (nextText != null) {
            nextText.setTextSize((fontScaleFactor * (realOffset)) + textSizePxl);
            nextText.setAlpha(realOffset + deselectedItemAlpha);
        }

        lastOffset = positionOffset;
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageSelected(int position) {
        if(adapter == null) return;

        TextView selectedText = adapter.getCarroselTextAt(position);

        if(listener == null
                || selectedText == null
                || selectedText.getText() == null
                || selectedText.getText().toString() == null) return;

        listener.onTextSelected(selectedText.getText().toString());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
