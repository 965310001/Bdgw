package com.bdgw.cc.ui.home.bean;

/**
 * @author GuoFeng
 * @date : 2019/1/28 17:48
 * @description:
 */
public class GoodsConfigBean {

    private int keyPropId;
    /**
     * 参数key
     */
    private String keyProp;
    private int valueId;
    /**
     * 参数value
     */
    private String value;

    public GoodsConfigBean(String keyProp, String value) {
        this.keyProp = keyProp;
        this.value = value;
    }

    public int getKeyPropId() {
        return keyPropId;
    }

    public void setKeyPropId(int keyPropId) {
        this.keyPropId = keyPropId;
    }

    public String getKeyProp() {
        return keyProp;
    }

    public void setKeyProp(String keyProp) {
        this.keyProp = keyProp;
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
