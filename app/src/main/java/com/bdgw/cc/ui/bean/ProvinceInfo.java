package com.bdgw.cc.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.xuexiang.xui.widget.picker.wheelview.interfaces.IPickerViewItem;

import java.util.List;

/**
 * @author GuoFeng
 * @date : 2019/1/26 10:56
 * @description:
 */
public class ProvinceInfo implements IPickerViewItem {

    /**
     * name : 省份
     * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区"]}]
     */
    private long id;
    private String name;
    @SerializedName("regions")
    private List<City> city;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCityList() {
        return city;
    }

    public void setCityList(List<City> city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class City implements IPickerViewItem {
        /**
         * name : 城市
         * area : ["东城区","西城区","崇文区","昌平区"]
         */
        private long id;
        private String name;
        @SerializedName("regions")
        private List<Area> area;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

//        public List<String> getArea() {
//            return area;
//        }
//
//        public void setArea(List<String> area) {
//            this.area = area;
//        }

        public List<Area> getArea() {
            return area;
        }

        public void setArea(List<Area> area) {
            this.area = area;
        }

        @Override
        public String getPickerViewText() {
            return this.name;
        }

        public static class Area implements IPickerViewItem {
            private long id;
            private String name;

            @Override
            public String toString() {
                return name;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String getPickerViewText() {
                return this.name;
            }
        }
    }

}
