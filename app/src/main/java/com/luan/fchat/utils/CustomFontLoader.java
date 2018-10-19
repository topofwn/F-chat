package com.luan.fchat.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by NguyenTa.Tran on 22/01/2018.
 */

public class CustomFontLoader {

    public static final int ARIAL = 0;
    private static final int NUM_OF_CUSTOM_FONTS = 1;

    private static boolean fontsLoaded = false;

    private static Typeface[] fonts = new Typeface[NUM_OF_CUSTOM_FONTS];

    private static String[] fontPath = {
            "fonts/arial.ttf"
    };

    public static Typeface getTypeface(Context context, int fontIdentifier) {
        if (!fontsLoaded) {
            loadFonts(context);
        }
        return fonts[fontIdentifier];
    }

    private static void loadFonts(Context context) {
        for (int i = 0; i < NUM_OF_CUSTOM_FONTS; i++) {
            fonts[i] = Typeface.createFromAsset(context.getAssets(), fontPath[i]);
        }
        fontsLoaded = true;
    }
}
