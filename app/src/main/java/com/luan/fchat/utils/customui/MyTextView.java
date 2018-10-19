package com.luan.fchat.utils.customui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.luan.fchat.data.LanguageController;
import com.luan.fchat.utils.CustomFontLoader;
import com.luan.fchat.utils.OGILVYLog;

public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
        initView();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        CharSequence textUsing = text;
        if (textUsing == null) {
            textUsing = "";
        }
        String bufftext = LanguageController.getLanguageController().getStringFromMap(getContext(), textUsing.toString());
        if (!TextUtils.isEmpty(bufftext)) {
            textUsing = bufftext;
        }
        super.setText(textUsing, type);
    }

    private void initView() {
        try {
            upadteTypeFace(getTypeface());
        } catch (RuntimeException ex) {
            OGILVYLog.logEx(ex, MyTextView.class);
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