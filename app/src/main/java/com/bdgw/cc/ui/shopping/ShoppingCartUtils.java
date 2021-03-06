package com.bdgw.cc.ui.shopping;

import android.text.TextUtils;

import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.db.ShoppingDBFactory;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bdgw.cc.ui.shopping.bean.VendorInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.goldze.common.base.event.LiveBus;
import me.goldze.common.utils.BeanCopyUtils;
import me.goldze.common.utils.GroupUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/24 14:04
 * @description: 购物车帮助类
 */
public class ShoppingCartUtils {

    /**
     * 获取购物车本地数据
     */
    public static List<VendorInfo> getLocalData() {
        List<GoodsInfo> goodsInfoList = ShoppingDBFactory.getInstance()
                    .getGoodsInfoManage()
                    .queryAll();
        //分组后的购物车数据
        List<VendorInfo> result = new ArrayList<>();

        Map<String, List<GoodsInfo>> map = new LinkedHashMap<>();
        GroupUtils.listGroup2Map(goodsInfoList, map, GoodsInfo.class, "getVendorId");
        for (String key : map.keySet()) {
            VendorInfo vendor = new VendorInfo();
            vendor.setVendorId(key);
            //这里伪数据  设置商品的供应商
            vendor.setVendorName(TextUtils.equals(key, "1") ? "小米之家" : "龙门客栈");
            vendor.setGoodsInfos(map.get(key));
            result.add(vendor);
        }
        return result;
    }

    /**
     * 添加商品到购物车
     *
     * @param goods goods
     */
    public static void addCartGoods(GoodsInfo goods) {
        addOrUpdateCartGoods(goods, false);
    }

    /**
     * 更新或添加商品到购物车
     *
     * @param goods     goods
     * @param updateNum 是否是修改商品数量
     */
    private static void addOrUpdateCartGoods(GoodsInfo goods, boolean updateNum) {
        if (goods == null) {
            return;
        }
        GoodsInfo goodsInfo = BeanCopyUtils.copy(goods);

        if (goodsInfo.getNum() < 1) {
            goodsInfo.setNum(1);
        }
        if (!updateNum) {
            GoodsInfo query = ShoppingDBFactory.getInstance()
                    .getGoodsInfoManage()
                    .query(goodsInfo.getGoodsId());
            if (query != null) {
                goodsInfo.setNum(query.getNum() + goodsInfo.getNum());
            }
        }
        ShoppingDBFactory.getInstance()
                .getGoodsInfoManage()
                .insertOrUpdate(goodsInfo);
    }

    /**
     * 修改购物车商品数量
     */
    public static void updateCartGoodsNum(GoodsInfo goods) {
        addOrUpdateCartGoods(goods, true);
    }

    /**
     * 获取购物车商品总数
     *
     * @return count
     */
    public static int getCartCount() {
        List<GoodsInfo> goodsInfoList = ShoppingDBFactory.getInstance()
                .getGoodsInfoManage()
                .queryAll();
        int count = 0;
        for (GoodsInfo info : goodsInfoList) {
            count += info.getNum();
        }
        return count;
    }

    /**
     * 获取购物车商品总价
     *
     * @return count
     */
    public static double getCartCountPrice(List<VendorInfo> vendors) {
        double price = 0.00f;
        for (GoodsInfo info : getAllCheckedGoods(vendors)) {
            price += info.getNum() * Double.parseDouble(info.getGoodsPrice());
        }
        return price;
    }

    /**
     * 删除购物车数据
     *
     * @param goodsList goodsList
     */
    public static void delete(List<GoodsInfo> goodsList) {
        ShoppingDBFactory.getInstance()
                .getGoodsInfoManage()
                .delete(goodsList);
        LiveBus.getDefault().postEvent(Constants.Shopping.EVENT_SHOPPING_CART_CHANGED, Constants.Shopping.EVENT_SHOPPING_CART_CHANGED,
                Constants.Shopping.EVENT_SHOPPING_CART_REFRESH);

    }

    //**********************************购物车点击逻辑操作*******************************************

    /**
     * 选择商品
     */
    public static void checkGoods(List<VendorInfo> vendors, int parent, int child) {
        vendors.get(parent).getGoodsInfos().get(child).setChecked(!vendors.get(parent).getGoodsInfos().get(child).isChecked());
        vendors.get(parent).setChecked(isAllGoodsChecked(vendors.get(parent).getGoodsInfos()));
    }

    /**
     * 选择供应商
     */
    public static void checkVendor(List<VendorInfo> vendors, int position) {
        vendors.get(position).setChecked(!vendors.get(position).isChecked());
        for (GoodsInfo info : vendors.get(position).getGoodsInfos()) {
            info.setChecked(vendors.get(position).isChecked());
        }
    }

    /**
     * 选择全部
     */
    public static void checkAll(List<VendorInfo> deliveryInfos, boolean check) {
        for (VendorInfo deliveryInfo : deliveryInfos) {
            deliveryInfo.setChecked(check);
            for (GoodsInfo info : deliveryInfo.getGoodsInfos()) {
                info.setChecked(check);
            }
        }
    }

    /**
     * 是否所有的商品都已经选择
     */
    private static boolean isAllGoodsChecked(List<GoodsInfo> list) {
        for (GoodsInfo info : list) {
            if (!info.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否所有的供应商已经全选
     */
    public static boolean isAllVendorChecked(List<VendorInfo> vendors) {
        for (VendorInfo info : vendors) {
            if (!info.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否至少选择了一个
     */
    public static boolean isCheckedLeastOne(List<VendorInfo> vendors) {
        for (VendorInfo deliveryInfo : vendors) {
            if (deliveryInfo.isChecked()) {
                return true;
            }
            for (GoodsInfo info : deliveryInfo.getGoodsInfos()) {
                if (info.isChecked()) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取购物车中已经选择的商品集合
     */
    public static List<GoodsInfo> getAllCheckedGoods(List<VendorInfo> vendors) {
        List<GoodsInfo> result = new ArrayList<>();
        for (VendorInfo deliveryInfo : vendors) {
            if (deliveryInfo.isChecked()) {
                result.addAll(deliveryInfo.getGoodsInfos());
            } else {
                for (GoodsInfo info : deliveryInfo.getGoodsInfos()) {
                    if (info.isChecked()) {
                        result.add(info);
                    }
                }
            }
        }
        return result;
    }
}
