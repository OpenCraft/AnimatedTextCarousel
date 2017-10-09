# AnimatedTextCarousel
A simple cool text carousel for Android


![Animated Text Carousel Animation](https://media.giphy.com/media/l378te2PaOkbjOx0Y/giphy.gif)

## Installation:
1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the dependency
```
dependencies {
  compile 'com.github.OpenCraft:AnimatedTextCarousel:-SNAPSHOT'
}
```

## Usage:
Start your own animated text carousel with:
```
  yourAnimatedTextCarouselViewPager.setItems(getYourStringArray());
```

Specify the animated text carousel component inside your layout file:
```
   <com.gcherubini.animatedtextcarousel.AnimatedTextCarouselViewPager
            android:id="@+id/animatedTextCarousel"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
```

If you would like to know what is the new selected text, when you are setting your viewpager items just pass the interface as below:
```
  yourAnimatedTextCarouselViewPager.setItemsWithListener(getYourStringArray(), new AnimatedTextCarouselListener() {
            @Override public void onTextSelected(String selectedText) { }
  });
```

**Customizing:** <br />

Create your own res/values/dimens.xml to override default behavioural configurations:

```
    <dimen name="animated_text_carousel_text_size">20dp</dimen>
    <dimen name="animated_text_carousel_view_pager_padding_horizontal">125dp</dimen>
    <item name="animated_text_carousel_deselected_text_alpha" type="dimen" format="float">0.3</item>
    <item name="animated_text_carousel_scale_factor" type="dimen" format="float">50</item>

```

And for styles res/values/colors.xml:
```
    <color name="animated_text_carousel_text_color">#FFFFFF</color>
```

