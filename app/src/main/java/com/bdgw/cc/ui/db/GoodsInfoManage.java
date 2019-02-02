package com.bdgw.cc.ui.db;

import com.bdgw.cc.ui.shopping.bean.GoodsInfo;

import org.greenrobot.greendao.AbstractDao;

/**
 * @author GuoFeng
 * @date : 2019/1/24 20:38
 * @description: 购物车商品DB操作类
 */
public class GoodsInfoManage extends BaseDBManager<GoodsInfo, String> {

    public GoodsInfoManage(AbstractDao dao) {
        super(dao);
    }
}
