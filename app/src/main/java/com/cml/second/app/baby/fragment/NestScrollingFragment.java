package com.cml.second.app.baby.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cml.second.app.baby.R;
import com.cml.second.app.common.widget.recyclerview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class NestScrollingFragment extends BaseFragment {

    @Bind(R.id.nestsrolling_recycler)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<RecycerviewFragment.DataModel> data = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            List<String> childNodes = new ArrayList<>();
            int childSize = (int) (Math.random() * 10 + 5);
            for (int j = 0; j < childSize; j++) {
                childNodes.add("child:" + i + "-" + j);
            }
            RecycerviewFragment.DataModel model = new RecycerviewFragment.DataModel("title" + i, childNodes);
            data.add(new RecycerviewFragment.DataModel("title" + i, null));
            data.add(model);
        }

        recyclerView.setAdapter(new RecycerviewFragment.MyAdapter(data, getActivity()));

    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_nest_scrolling;
    }

    @Override
    public int getTitle() {
        return R.string.title_nestscrolling;
    }
}
