package com.bdgw.cc.ui.classify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.R;
import com.bdgw.cc.ui.ApiData;
import com.bdgw.cc.ui.classify.bean.DistributionInfo;
import com.bdgw.cc.ui.me.AddressListActivity;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.widget.MultipleItemView;

/**
 * 确认订单
 */
@Route(path = ARouterConfig.classify.BUYACTIVITY)
public class BuyActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_select_address)
    TextView tvSelectAddress;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.mv_shopping)
    MultipleItemView mvShopping;
    @BindView(R.id.mv_distribution)
    MultipleItemView mvDistribution;
    @BindView(R.id.mv_invoice)
    MultipleItemView mvInvoice;
    @BindView(R.id.mv_select_red)
    MultipleItemView mvSelectRed;
    @BindView(R.id.mv_integral)
    MaterialEditText mvIntegral;
    @BindView(R.id.mv_select_integral)
    MultipleItemView mvSelectIntegral;
    @BindView(R.id.mv_message)
    MaterialEditText mvMessage;
    @BindView(R.id.mv_money)
    MultipleItemView mvMoney;
    @BindView(R.id.mv_freight)
    MultipleItemView mvFreight;
    @BindView(R.id.mv_red)
    MultipleItemView mvRed;
    @BindView(R.id.mv_use_integral)
    MultipleItemView mvUserIntegral;
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.iv_defualt_icon)
    ImageView ivDefualtIcon;

    @Autowired
    GoodsInfo goodsInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);

        showSuccess();

        rlTitleBar.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("确认订单");
    }

    @Override
    protected void initData() {
        super.initData();

        AddressInfo data = ApiData.getAddressInfos().get(0);
        setAddress(data);
        boolean isNull = null != data;
        tvSelectAddress.setVisibility(isNull ? View.GONE : View.VISIBLE);
        tvAddress.setVisibility(isNull ? View.VISIBLE : View.GONE);
        ivDefualtIcon.setVisibility(isNull ? View.VISIBLE : View.GONE);

        mvShopping.setLeftIconResource(R.drawable.ic_arrow).setRightText("共一件");
        mvDistribution.setRightText("中通");
        mvInvoice.setRightText("发票类型");
        mvSelectRed.setRightText("无使用红包");
        mvSelectIntegral.setLeftText("可用88283积分(每积分抵扣0.01元)");

        mvMoney.setRightText("￥ 3.99");
        mvFreight.setRightText("免运费");
        mvRed.setRightText("-￥ 0.00");

        mvMoney.setRightText(goodsInfo.getGoodsPrice());
        tvPayment.setText("实际付款￥ " + goodsInfo.getGoodsPrice());
    }

    @OnClick({R.id.iv_back, R.id.tv_select_address, R.id.tv_address, R.id.mv_shopping, R.id.mv_select_integral,
            R.id.mv_distribution, R.id.mv_invoice, R.id.mv_select_red, R.id.mv_integral, R.id.tv_buy})
    public void onClick(View view) {
        Map<String, Integer> map;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_select_address:
                //添加收货地址
                map = new HashMap<>();
                map.put("status", 0);
                ActivityToActivity.toActivity(ARouterConfig.Me.MODIFYACTIVITY, map);
                break;
            case R.id.tv_address:
                /*选择地址*/
                map = new HashMap<>();
                map.put("status", 1);
                ActivityToActivity.toActivityForResult(this, AddressListActivity.class, map,
                        ADDRESSLISTACTIVITYCODE);
                break;
            case R.id.mv_shopping:
                /*商品清单*/
                break;
            case R.id.mv_distribution:
                /*配送方式*/
                ActivityToActivity.toActivityForResult(this, DistributionActivity.class, null,
                        DISTRIBUTIONACTIVITYCODE);
            case R.id.mv_select_integral:
                break;
            case R.id.mv_invoice:
                //发票类型
                break;
            case R.id.mv_select_red:
                /*使用红包*/
                break;
            case R.id.tv_buy:
                /*提交订单*/
                KLog.i("立即购买");
                ToastUtils.showLong("请稍后再试");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DISTRIBUTIONACTIVITYCODE:
                if (resultCode == Activity.RESULT_OK) {
                    DistributionInfo.VendorsBean bean = (DistributionInfo.VendorsBean)
                            data.getSerializableExtra("data");

                    mvDistribution.setRightText(bean.getName());

                    tvPayment.setText("实际付款￥ " + String.valueOf(Double.parseDouble(goodsInfo.getGoodsPrice())
                            + Double.parseDouble(bean.getFee().toString())));
                }
                break;

            case ADDRESSLISTACTIVITYCODE:
                if (resultCode == Activity.RESULT_OK) {
                    setAddress((AddressInfo) data.getSerializableExtra("data"));
                }
                break;
        }
    }

    /*设置地址*/
    private void setAddress(AddressInfo bean) {
        if (bean != null) {
            tvAddress.setText(bean.name.concat(bean.phone).concat(" 默认\n").concat(bean.address).concat(bean.details));
        } else {
            ToastUtils.showLong("请选择正确的收货地址");
        }
    }

    //配送
    private final int DISTRIBUTIONACTIVITYCODE = 0;
    /*选择收货地址*/
    private final int ADDRESSLISTACTIVITYCODE = 1;

}