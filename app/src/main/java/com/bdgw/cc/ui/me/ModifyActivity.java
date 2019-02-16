package com.bdgw.cc.ui.me;

import android.graphics.Color;
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
import com.bdgw.cc.ui.bean.ProvinceInfo;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.AssetsUtils;
import me.goldze.common.utils.ProgressFragment;
import me.goldze.common.utils.RegexUtils;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.utils.Utils;

/**
 * 收货地址的增删改查
 */
@Route(path = ARouterConfig.Me.MODIFYACTIVITY)
public class ModifyActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    @BindView(R.id.ed_name)
    MaterialEditText edName;
    @BindView(R.id.ed_phone)
    MaterialEditText edPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ed_details)
    MaterialEditText edDetails;

    @Autowired
    AddressInfo data;

    @Autowired
    int status; //增：0    改：1

    private String name, phone, address, details;

    private List<ProvinceInfo> options1Items = new ArrayList<>();//省
    private List<List<String>> options2Items = new ArrayList<>();//市
    private List<List<List<String>>> options3Items = new ArrayList<>();//区

    ProgressFragment fragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        showSuccess();

        ARouter.getInstance().inject(this);

        rlTitleBar.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);

        tvTitle.setVisibility(View.VISIBLE);

        if (status == 0) {
            tvTitle.setText("增加收货地址");
        } else if (status == 1) {
            tvTitle.setText("修改收货地址");
            setAddress();
        }
    }

    private void setAddress() {
        edName.setText(data.name);
        edPhone.setText(data.phone);
        tvAddress.setText(data.address);
        edDetails.setText(data.details);
    }

    @OnClick({R.id.iv_back, R.id.tv_address, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_address:
                showPickerView(false);
                break;
            case R.id.tv_save:
                if (isNull()) {
                    if (null == fragment) {
                        fragment = ProgressFragment.show(getSupportFragmentManager());
                    }
                    if (!fragment.getDialog().isShowing()) {
                        fragment.getDialog().show();
                    }
                    fragment.dismiss();/*取消*/
                    // TODO: 2019/1/26  取消
//                    ApiRepo.updateAddress("", String.valueOf(status), name, phone, address, details)
//                            .subscribeWith(new RxSubscriber<BaseResponse<String>>() {
//                                @Override
//                                public void onSuccess(BaseResponse<String> response) {
//                                    fragment.dismiss();
//                                    if (response.isSuccess()) {
//                                        ToastUtils.showLong("保存地址成功");
//                                        finish();
//                                    } else {
//                                        ToastUtils.showLong(response.getMessage());
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(String msg) {
//                                    fragment.dismiss();
//                                    ToastUtils.showLong(msg);
//                                }
//                            });
                }
                break;
        }
    }

    //判断收货地址
    private boolean isNull() {
        name = edName.getText().toString();    //收件人
        phone = edPhone.getText().toString();
        address = tvAddress.getText().toString();
        details = edDetails.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showLong("收件人为空");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showLong("联系电话为空");
            return false;
        } else if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showLong("请输入正确的手机号");
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            ToastUtils.showLong("地址不完整");
            return false;
        }
        if (TextUtils.isEmpty(details)) {
            ToastUtils.showLong("地址不完整");
            return false;
        }
        address = address.concat(details);
        return true;
    }

    /************************************************************省市区************************************************************/
    private void initJsonData() {
        String JsonData = AssetsUtils.getStringFromAssert(Utils.getApplication(), "province.json");
        List<ProvinceInfo> provinceInfos = new Gson().fromJson(JsonData, new TypeToken<List<ProvinceInfo>>() {
        }.getType());
        /**
         * 添加省份数据
         */
        options1Items = provinceInfos;
        List<String> cityList, City_AreaList;
        List<List<String>> areaList;
        for (ProvinceInfo provinceInfo : provinceInfos) { //遍历省份
            cityList = new ArrayList<>();//该省的城市列表（第二级）
            areaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (ProvinceInfo.City city : provinceInfo.getCityList()) {
                cityList.add(city.getName());//添加城市
                City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (city.getArea() == null || city.getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(city.getArea());
                }
                areaList.add(City_AreaList);//添加该省所有地区数据
            }
            options2Items.add(cityList);
            options3Items.add(areaList);
        }
    }

    public void showPickerView(boolean isDialog) {// 弹出选择器
        initJsonData();
        com.bigkoo.pickerview.OptionsPickerView pvOptions = new com.bigkoo.pickerview.OptionsPickerView.Builder(this,
                new com.bigkoo.pickerview.OptionsPickerView
                        .OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText() +
                                options2Items.get(options1).get(options2) +
                                options3Items.get(options1).get(options2).get(options3);
                        tvAddress.setText(tx);
                    }
                })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .isDialog(isDialog)
//                .setSelectOptions(defaultSelectOptions[0], defaultSelectOptions[1], defaultSelectOptions[2])
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);       //三级选择器
        pvOptions.show();
    }

    /**
     * @return 获取默认城市的索引
     */
    private int[] getDefaultCity(String... itmes) {
        int[] res = new int[3];
        ProvinceInfo provinceInfo;
        List<ProvinceInfo.City> cities;
        ProvinceInfo.City city;
        List<String> ares;
        for (int i = 0; i < options1Items.size(); i++) {
            provinceInfo = options1Items.get(i);
            if (itmes[0].equals(provinceInfo.getName())) {
                res[0] = i;
                cities = provinceInfo.getCityList();
                for (int j = 0; j < cities.size(); j++) {
                    city = cities.get(j);
                    if (itmes[1].equals(city.getName())) {
                        res[1] = j;
                        ares = city.getArea();
                        for (int k = 0; k < ares.size(); k++) {
                            if (itmes[2].equals(ares.get(k))) {
                                res[2] = k;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        return res;
    }

}
