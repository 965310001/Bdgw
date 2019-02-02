package com.bdgw.cc.ui;

import com.bdgw.cc.ui.classify.bean.ClassificationInfo;
import com.bdgw.cc.ui.classify.bean.DistributionInfo;
import com.bdgw.cc.ui.home.bean.GoodsComment;
import com.bdgw.cc.ui.home.bean.OrderInfo;
import com.bdgw.cc.ui.home.bean.RedItemInfo;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.bdgw.cc.ui.me.bean.AfterSalesInfo;
import com.bdgw.cc.ui.me.bean.DefiniteInfo;
import com.bdgw.cc.ui.me.bean.ExtractInfo;
import com.bdgw.cc.ui.shopping.bean.DeliverInfo;
import com.bdgw.cc.ui.shopping.bean.GoodsInfo;
import com.bdgw.cc.ui.shopping.bean.OrderDetailsInfo;
import com.bdgw.cc.ui.shopping.bean.VendorInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.socks.library.KLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.goldze.common.utils.AssetsUtils;
import me.goldze.common.utils.Utils;

/**
 * @author GuoFeng
 * @date : 2019/1/24 16:57
 * @description:
 */
public class ApiData {

    /*商品信息*/
    public static List<GoodsInfo> getGoodsInfos() {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "GoodsInfo.json"), type);
        ArrayList<GoodsInfo> goodsInfos = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            goodsInfos.add(new Gson().fromJson(jsonObject, GoodsInfo.class));
        }
        return goodsInfos;
    }

    public static List<VendorInfo> getVendors() {
        List<VendorInfo> vendorInfos = new ArrayList<>();
        List<GoodsInfo> goodsInfos = getGoodsInfos();
        for (int i = 0; i < 1; i++) {
            vendorInfos.add(new VendorInfo("联想商家" + i, goodsInfos));
        }
        return vendorInfos;
    }

    /*地址*/
    private static AddressInfo gtAddressInfo() {
        return new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "AddressInfo.json"), AddressInfo.class);
    }

    /*地址列表*/
    public static List<AddressInfo> getAddressInfos() {
        List<AddressInfo> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(gtAddressInfo());
        }
        list.get(0).isDefault = true;
        list.get(1).name = "李四";
        list.get(2).name = "王五";


        list.get(1).phone = "18500";
        list.get(2).phone = "18500701962";
        list.get(2).details = "具体详情";
        return list;
    }

    /*配送方式*/
    public static DistributionInfo getDistributionInfo() {
        return new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "Distribution.json"), DistributionInfo.class);
    }

    /*提现记录*/
    public static List<ExtractInfo.DataBean> getExtractInfoList() {
        return new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "ExtractInfo.json"), ExtractInfo.class).getData();
    }

    /*订单*/
    public static OrderInfo getOrderInfo() {
        return new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "OrderList.json"), OrderInfo.class);
    }

    /*商品详情*/
    public static List<GoodsComment> getGoodsCommentList() {
        List<GoodsComment> commentList = new ArrayList<>();
        GoodsComment comment = new GoodsComment();
        comment.setComment("手机到了刚弄好，非常流畅");
        comment.setStar(5);
        comment.setUserHead("http://i10.hoopchina.com.cn/hupuapp/bbs/966/16313966/thread_16313966_20180726164538_s_65949_o_w1024_h1024_62044.jpg?x-oss-process=image/resize,w_800/format,jpg");
        comment.setUserName("噶***蛋");
        commentList.add(comment);

        comment = new GoodsComment();
        comment.setComment("物流速度很给力，同时店家也很有耐心客服也很有责任心各种操作都很流畅信号各方面都没有问题，谢谢！");
        comment.setStar(3);
        comment.setUserName("阿***蛋");
        comment.setUserHead("https://img4.duitang.com/uploads/item/201601/16/20160116161347_svH5y.thumb.224_0.jpeg");
        commentList.add(comment);

        KLog.i(new Gson().toJson(commentList));

        return commentList;
    }

    /*资金详情*/
    public static DefiniteInfo getDefinite(String status) {
        List<DefiniteInfo.BalancesBean> list = new ArrayList<>();
        DefiniteInfo info = new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "Definite.json"), DefiniteInfo.class);
        if (!"0".equals(status)) {
            for (DefiniteInfo.BalancesBean bean : info.getBalances()) {
                if (bean.getStatus() == Integer.parseInt(status)) {
                    list.add(bean);
                }
            }
            info.setBalances(list);
        }
        return info;
    }

    /*我的红包*/
    public static List<RedItemInfo.CashgiftsBean> getRedList() {
        RedItemInfo info = new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "RedInfo.json"), RedItemInfo.class);
        return info.getCashgifts();
    }


    /*三级listview*/
    public static ClassificationInfo getClasszInfo() {
        return new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "ClasszInfo.json"), ClassificationInfo.class);
    }

    /*物流*/
    public static DeliverInfo getDeliverInfo() {
        return new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "DeliveryInfo.json"), DeliverInfo.class);
    }

    /*订单详情*/
    public static List<OrderDetailsInfo> getOrderDetailsInfos() {
        OrderDetailsInfo data = new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "OrderDetails.json"), OrderDetailsInfo.class);
        data.setAddressInfo(getAddressInfos().get(0));//地址
        data.setGoodsInfos(getGoodsInfos());
        List<OrderDetailsInfo> objects = new ArrayList<>();
        objects.add(data);
        return objects;
    }

    /*售后list*/
    public static List<AfterSalesInfo> getAfterSalesList() {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(AssetsUtils.getStringFromAssert(Utils.getApplication(),
                "AfterSales.json"), type);
        ArrayList<AfterSalesInfo> salesInfos = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            salesInfos.add(new Gson().fromJson(jsonObject, AfterSalesInfo.class));
        }
        return salesInfos;
    }
}