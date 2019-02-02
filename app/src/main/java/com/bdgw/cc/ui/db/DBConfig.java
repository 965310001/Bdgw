package com.bdgw.cc.ui.db;

import com.bdgw.cc.R;

import me.goldze.common.utils.Utils;

/**
 * @author GuoFeng
 * @date : 2019/1/24 21:16
 * @description:
 */
public interface DBConfig {
    String DB_NAME = Utils.getApplication().getString(R.string.app_name) + ".db";

}
