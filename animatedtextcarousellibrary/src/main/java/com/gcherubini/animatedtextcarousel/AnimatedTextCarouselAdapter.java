package com.gcherubini.animatedtextcarousel;

import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcherubini.animatedtextcarousel.databinding.CarroselItemBinding;

import java.util.ArrayList;
import java.util.List;

public class AnimatedTextCarouselAdapter extends PagerAdapter {

    private final AnimatedTextCarouselViewPager viewPager;
    private List<TextView> textViews;
    private List<String> texts;

    public AnimatedTextCarouselAdapter(AnimatedTextCarouselViewPager viewPager, List<String> texts) {
        this.viewPager = viewPager;
        this.texts = texts;
        textViews = new ArrayList<>();
        initTextViewArray(texts);
    }

    private void initTextViewArray(List<String> texts) {
        if (texts != null) {
            for (String model : texts) {
                textViews.add(null);
            }
        }
    }

    public TextView getCarroselTextAt(int position) {
        return textViews.get(position);
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup parent, final int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CarroselItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.carrosel_item, parent, false);
        parent.addView(binding.getRoot());
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager != null) {
                    viewPager.setCurrentItem(position);
                }
            }
        });
        bind(texts.get(position), binding);
        textViews.set(position, binding.carrosselItemText);
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        textViews.set(position, null);
    }

    private void bind(String text, CarroselItemBinding binding) {
        binding.setVariable(com.gcherubini.animatedtextcarousel.BR.carrosselText, text);
        binding.executePendingBindings();
    }
}
