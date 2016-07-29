package com.cml.second.app.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cml.newframe.activeandroid.Category;
import com.cml.newframe.activeandroid.Item;
import com.cml.second.app.baby.R;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/7/29.
 */
public class ActiveAndroidFragment extends BaseFragment {

    @Bind(R.id.result)
    TextView resultView;

    @OnClick({R.id.addData, R.id.delData, R.id.modifyData, R.id.queryData})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.addData:
                insertData();
                queryData();
                break;
            case R.id.delData:
                showToast("删除数据");
                break;
            case R.id.queryData:
                showToast("queryitem");
                queryItemData();
                break;
            case R.id.modifyData:
                showToast("修改数据");
                break;
        }


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        queryData();
    }


    private void insertData() {
        Category model = new Category();
        int random = (int) (Math.random() * 100);
        model.age = random;
        model.nickName = "nickname" + random;
        model.userName = "username" + random;
        model.save();

        Item couponModel = new Item();
        couponModel.coupoName = "coupopnname" + random;
        couponModel.count = random;
        couponModel.c = model;
        couponModel.save();
    }


    private void queryData() {
        List<Category> users = Category.getUsers();
        StringBuffer buffer = new StringBuffer("----------查询结果------------").append("\n");
        for (Category user : users) {
            buffer.append(user.getId()).append(",").append(user.nickName).append(",getId:").append(user.getId());
            buffer.append("\n");
            buffer.append("models:").append(user.getModel());
            buffer.append("\n");
        }
        resultView.setText(buffer.toString());
    }

    private void queryItemData() {
        resultView.setText("");
        List<Item> items = Item.getItems();
        StringBuffer buffer = new StringBuffer("----------查询item结果------------").append("\n");
        for (Item item : items) {
            buffer.append(item).append("\n");
        }
        resultView.setText(buffer.toString());
    }


    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }


    @Override
    protected int getContainerRes() {
        return R.layout.fragment_active_android;
    }
}
