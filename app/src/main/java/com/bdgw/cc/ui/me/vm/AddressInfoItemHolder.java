package com.bdgw.cc.ui.me.vm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bdgw.cc.R;
import com.bdgw.cc.ui.Constants;
import com.bdgw.cc.ui.me.bean.AddressInfo;
import com.trecyclerview.holder.AbsHolder;
import com.trecyclerview.holder.AbsItemHolder;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.goldze.common.base.event.LiveBus;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

/**
 * @author GuoFeng
 * @date : 2019/1/26 14:29
 * @description: 管理地址
 */
public class AddressInfoItemHolder extends AbsItemHolder<AddressInfo, AddressInfoItemHolder.ViewHolder> {

    public AddressInfoItemHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_address;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final AddressInfo data) {
        holder.tvName.setText(data.name);
        holder.tvPhone.setText(data.phone);
        holder.tvAddress.setText(data.address.concat(data.details));
        holder.ivDefualt.setSelected(data.isDefault);

        holder.ivDefualt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.ivDefualt.isSelected()) {
                    data.status = "check";
                    LiveBus.getDefault().postEvent(Constants.Me.EVENT_EDIT_ADDRESS_CHANGED, Constants.Me.EVENT_EDIT_ADDRESS_CHANGED, data);
                }
            }
        });
        holder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.status = "del";
                LiveBus.getDefault().postEvent(Constants.Me.EVENT_EDIT_ADDRESS_CHANGED, Constants.Me.EVENT_EDIT_ADDRESS_CHANGED, data);
            }
        });
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("data", data);
                map.put("status", 1);
                ActivityToActivity.toActivity(ARouterConfig.Me.MODIFYACTIVITY, map);
            }
        });
    }

    public static class ViewHolder extends AbsHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.iv_defualt)
        TextView ivDefualt;
        @BindView(R.id.tv_del)
        TextView tvDel;
        @BindView(R.id.tv_edit)
        TextView tvEdit;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
