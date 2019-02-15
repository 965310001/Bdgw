package me.goldze.xui.button;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
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
    private final List<EditText> editTextList;

    public static void observer(TextView textView, EditText... editTexts) {
        new TextChangeUtils(textView, editTexts);
    }

    private TextChangeUtils(TextView textView, EditText... editTexts) {
        editTextList = new ArrayList<>();
        this.textView = textView;
        for (EditText editText : editTexts) {
            editText.addTextChangedListener(this);
            editTextList.add(editText);
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
        for (EditText et : editTextList) {
            if (TextUtils.isEmpty(et.getText().toString().trim())) {
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
