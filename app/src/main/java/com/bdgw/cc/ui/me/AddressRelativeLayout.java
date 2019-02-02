//package com.bdgw.cc.ui.me;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//
//import com.bdgw.cc.R;
//import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * @author GuoFeng
// * @date : 2019/1/25 21:12
// * @description:
// */
//public class AddressRelativeLayout extends FrameLayout {
//
//
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
//
//    @BindView(R.id.tv_value)
//    TextView tvValue;
//
//    @BindView(R.id.ed_value)
//    MaterialEditText edValue;
//
//    public AddressRelativeLayout(Context context) {
//        this(context, null);
//    }
//
//    public AddressRelativeLayout(Context context, AttributeSet attrs) {
//        super(context, attrs, 0);
//    }
//
//    public AddressRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initView(context);
//    }
//
//    private void initView(Context context) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_address, null);
//        ButterKnife.bind(this, view);
//        addView(view);
//    }
//
//    public void setTitle(String title) {
//        tvTitle.setText(title);
//    }
//
//    public void setValue(String value) {
//        tvValue.setText(value);
//    }
//
//    public void set() {
//
//    }
//
//    public void get() {
//
//    }
//
//
//    @OnClick(R.id.tv_value)
//    public void onClick() {
//
//    }
//}
