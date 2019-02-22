package com.bdgw.cc.ui;

/**
 * @author GuoFeng
 * @date : 2019/1/20 14:48
 * @description:
 */
public interface Constants {
    String IS_LOGIN = "IS_LOGIN";
    /*-------------------------------------------------------------------首页---------------------------------------------------*/
    String EVENT_KEY_HOME = "event_key_home";
    String EVENT_KEY_WORK_LIST_STATE = "event_key_work_list_state";
    Object EVENT_KEY_HOME_STATE = "event_key_home_state";

    String[] SEARCH_EVENT_KEY = {"EVENT_KEY_SEARCH", "EVENT_KEY_SEARCH_TAG",
            "EVENT_KEY_SEARCH_DELETE", "EVENT_KEY_SEARCH_TAG_DELETE"};

    interface Home {
        String[] SEARCH_TITLE = {"综合排序", "销量排序", "新品上架"};
        /*搜索状态*/
        String[] SEARCH_TABBAR_STATUS = {"10", "0", "1"};

        String[] EVENT_KEY_SEARCH_LIST = {"EVENT_KEY_SEARCH_LIST", "EVENT_KEY_SEARCH_LIST_STATE"};
    }

    /**
     * 四个menu----订单
     */
    interface Order {
        String[] ORDER_TABBAR_TITLE = {"全部", "待付款", "待发货", "待收货", "待评价"};

        /*订单状态*/
        String[] ORDER_TABBAR_STATUS = {"0", "10", "1", "2", "3"};

        String[] EVENT_KEY_ORDER_LIST = {"EVENT_KEY_ORDER_LIST", "EVENT_KEY_ORDER_LIST_STATE"};

        String[] ORDER_STATUS = {"待付款", "已取消", "配送中", "待评价", "待发货"};
    }

    /**
     * 四个menu----红包
     */
    interface Red {
        String[] RED_TABBAR_TITLE = {"未使用", "已使用", "已过期"};
        /*红包状态*/
        String[] RED_TABBAR_STATUS = {"1", "2", "3"};

        String[] EVENT_KEY_RED_LIST = {"EVENT_KEY_RED_LIST", "EVENT_KEY_RED_LIST_STATE"};
    }


    /*-------------------------------------------------------------------分类---------------------------------------------------*/
    interface Listview {
        /*左边listview*/
        String[] POSTION_LEF_EVENT_KEY = {"POSTION_LEF_EVENT_KEY", "POSTION_LEFT_EVENT_KEY_TAG"};
        /*右边listview*/
        String[] POSTION_RIGHT_EVENT_KEY = {"POSTION_RIGHT_EVENT_KEY", "POSTION_RIGHT_EVENT_KEY_TAG"};

        String[] PRODUCTS_TITLE = {"综合排序", "销量排序", "新品上架"};
    }

    /*-------------------------------------------------------------------购物车-------------------------------------------------*/

    interface Shopping {
        /**
         * 购物车有变化
         */
        String EVENT_SHOPPING_CART_CHANGED = "event_shopping_cart_changed";
        /**
         * 刷新购物车 重新获取数据
         */
        String EVENT_SHOPPING_CART_REFRESH = "event_shopping_cart_refresh";


        //综合排序，销量排序，新品排序 0,4,5
        int[] SORT_KEY = {0, 4, 5};
    }

    /*-------------------------------------------------------------------我的---------------------------------------------------*/
    interface Me {
        /*修改收货地址*/
        String EVENT_EDIT_ADDRESS_CHANGED = "EVENT_EDIT_ADDRESS_CHANGED";

        /*资金明细*/
        String[] DEFINITE_TITLE = {"全部", "收入", "支出"};
        String[] DEFINITE_STATUS = {"0", "1", "2"};

    }

}
