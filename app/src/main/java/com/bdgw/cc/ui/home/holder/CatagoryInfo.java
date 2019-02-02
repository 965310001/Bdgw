package com.bdgw.cc.ui.home.holder;

import android.content.res.TypedArray;

import com.bdgw.cc.R;

import java.util.ArrayList;
import java.util.List;

import me.goldze.common.utils.ResourcesUtils;

/**
 * @author GuoFeng
 * @date : 2019/1/20 15:58
 * @description: 首页 四个状态栏
 */
public class CatagoryInfo {

    public List<Catagory> getCatagorys() {

        List<Catagory> list = new ArrayList<>();

        String[] stringArray = ResourcesUtils.getInstance().getStringArray(R.array.menu_item_title);
        TypedArray array = ResourcesUtils.getInstance().obtainTypedArray(R.array.menu_item_icon);

        for (int i = 0; i < stringArray.length; i++) {
            list.add(new Catagory(stringArray[i], array.getResourceId(i, 0)));
        }
        return list;
    }

    class Catagory {
        String title;
        int resId;

        public Catagory() {
        }

        private Catagory(String tvName, int tvIcon) {
            this.title = tvName;
            this.resId = tvIcon;
        }
    }

}
