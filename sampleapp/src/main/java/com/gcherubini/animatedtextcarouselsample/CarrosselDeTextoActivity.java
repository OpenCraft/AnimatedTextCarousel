package com.gcherubini.animatedtextcarouselsample;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.gcherubini.animatedtextcarousel.AnimatedTextCarouselListener;
import com.gcherubini.animatedtextcarouselsample.databinding.ActivityAnimatedTextCarouselSampleBinding;

import java.util.ArrayList;
import java.util.List;


public class CarrosselDeTextoActivity extends Activity implements AnimatedTextCarouselListener {

    private ActivityAnimatedTextCarouselSampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animated_text_carousel_sample);
        loadCarrossel();
    }

    void loadCarrossel() {
        List<String> items = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            items.add(String.valueOf(i));
        }

        binding.carroselViewPager.setItemsWithListener(items, this);
    }

    @Override
    public void onTextSelected(String selectedText) {
        Toast.makeText(this, selectedText, Toast.LENGTH_SHORT).show();
    }
}
