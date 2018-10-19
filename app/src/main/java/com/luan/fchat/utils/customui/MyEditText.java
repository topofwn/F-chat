package com.luan.fchat.utils.customui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.luan.fchat.data.LanguageController;
import com.luan.fchat.utils.CustomFontLoader;
import com.luan.fchat.utils.OGILVYLog;

public class MyEditText extends AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
        initView(getTypeface());
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        CharSequence tempText = text;
        if (tempText == null) {
            tempText = "";
        }
        String bufftext = LanguageController.getLanguageController().getStringFromMap(getContext(), tempText.toString());
        if (bufftext != null && !bufftext.isEmpty()) {
            tempText = bufftext;
        }
        super.setText(tempText, type);
    }

    /**
     */
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(getTypeface());
    }

    /**
     */
    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(getTypeface());
    }

    private void initView(Typeface tf) {
        try {
            upadteTypeFace(tf);
        } catch (RuntimeException ex) {
            OGILVYLog.logEx(ex, MyEditText.class);
        }
    }

    private void upadteTypeFace(Typeface tf) {
        Typeface typeFace;
        if (tf != null) {
            switch (tf.getStyle()) {
                case Typeface.NORMAL:
                    typeFace = CustomFontLoader.getTypeface(getContext(), CustomFontLoader.ARIAL);
                    setTypeface(typeFace,
                            tf.getStyle());
                    break;
                case Typeface.BOLD_ITALIC:
                case Typeface.ITALIC:
                default:
                    typeFace = CustomFontLoader.getTypeface(getContext(), CustomFontLoader.ARIAL);
                    setTypeface(typeFace,
                            tf.getStyle());
            }
        } else {
            typeFace = CustomFontLoader.getTypeface(getContext(), CustomFontLoader.ARIAL);
            setTypeface(typeFace);
        }
    }
}
