package com.bdgw.cc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bdgw.cc.ui.ApiRepo;
import com.bdgw.cc.ui.UserInfo;
import com.bdgw.cc.ui.bean.ProvinceInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socks.library.KLog;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.http.rx.RxSubscriber;
import me.goldze.common.utils.ActivityToActivity;
import me.goldze.common.utils.AssetsUtils;
import me.goldze.common.utils.ProgressFragment;
import me.goldze.common.utils.ToastUtils;
import me.goldze.common.utils.Utils;
import me.goldze.xui.button.TextChangeUtils;

@Route(path = ARouterConfig.REGISTERACTIVITY2)
public class RegisterActivity2 extends BaseActivity {

    @Autowired
    String phone;
    @Autowired
    String code;
    @Autowired
    String password;
    @Autowired
    String invitecode;

    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ed_address)
    MaterialEditText edAddress;
    @BindView(R.id.ed_contacts)
    MaterialEditText edContacts;
    @BindView(R.id.ed_ywyno)
    MaterialEditText edYwyno;
    @BindView(R.id.ed_company)
    MaterialEditText edCompany;
    @BindView(R.id.btn_register)
    TextView btnRegister;

    private String region, address, contacts, ywyno, company;

    private ProgressFragment progressFragment;
    private String addressId;

    private List<ProvinceInfo> options1Items = new ArrayList<>();//省
    private List<List<ProvinceInfo.City>> options2Items = new ArrayList<>();//市
    private List<List<List<ProvinceInfo.City.Area>>> options3Items = new ArrayList<>();//区

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register2;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        showSuccess();
        progressFragment = new ProgressFragment();
        setTitle("注册");

        KLog.i(phone + " " + code + " " + password);

        TextChangeUtils.observer(btnRegister, tvAddress, edAddress, edContacts, edYwyno);
    }

    @OnClick({R.id.tv_to_login, R.id.tv_address, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_to_login:
                ActivityToActivity.toActivity(ARouterConfig.LOGINACTIVITY);
                finish();
                break;
            case R.id.btn_register:/*注册*/
                checkIsNull();
                break;
            case R.id.tv_address:
                showPickerView(false);
                break;
        }
    }

    private void checkIsNull() {
        region = tvAddress.getText().toString().trim();
        address = edAddress.getText().toString().trim();
        contacts = edContacts.getText().toString().trim();
        company = edCompany.getText().toString().trim();
        ywyno = edYwyno.getText().toString().trim();

        region = addressId;


        if (false) {
            ApiRepo.register2(phone, code, password, invitecode, region, address, contacts, company, ywyno).subscribeWith(new RxSubscriber<UserInfo>() {

                @Override
                public void onSuccess(UserInfo response) {
                    if (progressFragment.getDialog().isShowing()) {
                        progressFragment.dismiss();
                    }

                    KLog.i(response.getErrorMsg() + response.getError_desc());

                    if (!response.isSuccess()) {
                        ToastUtils.showLong(response.getErrorMsg());
                    } else {
                        /*第二部*/
                        Map<String, String> map = new HashMap<>();
                        map.put("phone", phone);
                        map.put("code", code);
                        map.put("password", password);
                        ActivityToActivity.toActivity(ARouterConfig.REGISTERACTIVITY2, map);
                    }
                }

                @Override
                public void onFailure(String msg) {
                    KLog.i(msg);
                    ToastUtils.showLong(msg);
                    if (progressFragment.getDialog().isShowing()) {
                        progressFragment.dismiss();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    KLog.i(t.getMessage());
                    ToastUtils.showLong("请稍后再试");
                    if (progressFragment.getDialog().isShowing()) {
                        progressFragment.dismiss();
                    }
                }
            });
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
                        String tx = options1Items.get(options1).getPickerViewText();
                        addressId = String.valueOf(options1Items.get(options1).getId());
                        ProvinceInfo.City city = options2Items.get(options1).get(options2);
                        if (null != city) {
                            tx = tx.concat(city.getPickerViewText());
                            addressId = addressId.concat(",").concat(String.valueOf(city.getId()));
                        }
                        ProvinceInfo.City.Area area = options3Items.get(options1).get(options2).get(options3);
                        if (null != area) {
                            tx = tx.concat(area.getPickerViewText());
                            addressId = addressId.concat(",").concat(String.valueOf(area.getId()));
                        }
                        tvAddress.setText(tx);
                        KLog.i("地址:" + addressId);
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

    private void initJsonData() {
        String JsonData = AssetsUtils.getStringFromAssert(Utils.getApplication(), "province.json");
        options1Items = new Gson().fromJson(JsonData, new TypeToken<List<ProvinceInfo>>() {
        }.getType());
        /**
         * 添加省份数据
         */
//        options1Items = provinceInfos;
        List<ProvinceInfo.City.Area> City_AreaList;
        List<ProvinceInfo.City> cityList;
        List<List<ProvinceInfo.City.Area>> areaList;
        for (ProvinceInfo provinceInfo : options1Items) { //遍历省份
            cityList = new ArrayList<>();//该省的城市列表（第二级）
            areaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (ProvinceInfo.City city : provinceInfo.getCityList()) {
//                cityList.add(city.getName());//添加城市
                cityList.add(city);
                City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (city.getArea() == null || city.getArea().size() == 0) {
                    City_AreaList.add(null);
                } else {
                    City_AreaList.addAll(city.getArea());
                }
                areaList.add(City_AreaList);//添加该省所有地区数据
            }
            options2Items.add(cityList);
            options3Items.add(areaList);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != progressFragment && progressFragment.isVisible()) {
            progressFragment.dismiss();
            progressFragment = null;
        }
    }
}
