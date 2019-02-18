package me.goldze.xui.button;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuoFeng
 * @date : 2019/2/15 15:25
 * @description: 观察多个输入框的Button，TextView
 */
public class TextChangeUtils implements TextWatcher {

    private boolean canPress;
    private final TextView textView;
    private List<TextView> textViewList;

    public static void observer(TextView... textViews) {
        if (null == textViews || textViews.length < 2) {
            new Exception("textViews 的长度必须大于2");
            return;
        }
        new TextChangeUtils(textViews[0], textViews);
    }

    private TextChangeUtils(TextView textView, TextView... textViews) {
        textViewList = new ArrayList<>();
        this.textView = textView;
        for (TextView textView1 : textViews) {
            textView1.addTextChangedListener(this);
            textViewList.add(textView1);
        }
        initView();
    }

    private void initView() {
        textView.setEnabled(canPress);
    }

    /**
     * 检查输入框输入
     */
    private void checkEditText() {
        canPress = true;
        for (TextView tv : textViewList) {
            if (TextUtils.isEmpty(tv.getText().toString().trim())) {
                canPress = false;
                break;
            }
        }
        initView();
    }

    @Override
    public void afterTextChanged(Editable arg0) {
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
    }

    @Override
    public void onTextChanged(CharSequence cs, int start, int before,
                              int count) {
        checkEditText();
    }
}
