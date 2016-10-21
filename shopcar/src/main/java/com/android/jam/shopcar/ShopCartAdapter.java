package com.android.jam.shopcar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jam on 16/10/21 下午12:07.
 * Describe:
 */

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.MyViewHolder> {

    private List<GoodsBean> goodsBeanList;
    private Context context;

    public ShopCartAdapter(List<GoodsBean> goodsBeanList, Context context) {
        this.goodsBeanList = goodsBeanList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GoodsBean goodsBean = goodsBeanList.get(position);
        holder.tvGoodsName.setText(goodsBean.getGoodsName());
        holder.checkboxShopCart.setChecked(goodsBean.isSelected);
        holder.checkboxShopCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goodsBean.setSelected(isChecked);
            }
        });
    }


    public void addGoods(List<GoodsBean> list) {
        if (goodsBeanList == null) goodsBeanList = new ArrayList<>();
        goodsBeanList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 获取所有选中的商品列表
     *
     * @return
     */
    public List<GoodsBean> getAllSelectedGoods() {
        List<GoodsBean> allSelectedGoods = new ArrayList<>();
        for (GoodsBean g : goodsBeanList) {
            if (g.isSelected) allSelectedGoods.add(g);
        }
        return allSelectedGoods;
    }

    @Override
    public int getItemCount() {
        if (goodsBeanList == null)
            return 0;
        return goodsBeanList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_goodsName)
        TextView tvGoodsName;
        @BindView(R.id.checkbox_shopCart)
        CheckBox checkboxShopCart;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
