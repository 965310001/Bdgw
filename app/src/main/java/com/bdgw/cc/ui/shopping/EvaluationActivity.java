package com.bdgw.cc.ui.shopping;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.home.bean.OrderInfo;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ImageUtils;
import me.goldze.common.utils.ToastUtils;

/**
 * 评价晒单
 */
@Route(path = ARouterConfig.Shopping.EVALUATIONACTIVITY)
public class EvaluationActivity extends BaseActivity {

    @Autowired
    OrderInfo.OrdersBean bean;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ed_content)
    MaterialEditText edContent;

//    private String content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluation;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);

        showSuccess();
        ivBack.setVisibility(View.VISIBLE);
        rlTitleBar.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.VISIBLE);

        tvTitle.setText("评价晒单");
        tvRight.setText("提交");

        KLog.i(bean.getGoodsName());

        tvName.setText(bean.getGoodsName());
        tvPrice.setText(bean.getShippingprice());
        ImageUtils.loadImage(ivIcon, bean.getThumb());
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                String content = edContent.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showLong("请输入评价内容");
                    return;
                }
                ToastUtils.showLong("提交到网络");
                finish();
                break;
        }
    }
}