package com.cml.second.app.baby.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.animator.ScaleInAnimator;
import com.cml.second.app.baby.animator.adapter.ScaleInAnimationAdapter;
import com.cml.second.app.common.widget.recyclerview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * Created by cmlBeliever on 2017/9/6.
 */

public class CustomRecyclerViewAnimator extends BaseFragment {

    @Bind(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));//只支持 LinearLayoutManager
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));//GridLayoutManager 专用
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new ScaleInAnimator(new OvershootInterpolator()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setLayoutManager(new ImageGridLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        final List<DataModel> data = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            List<String> childNodes = new ArrayList<>();
            int childSize = (int) (Math.random() * 10 + 5);
            for (int j = 0; j < childSize; j++) {
                childNodes.add("child:" + i + "-" + j);
            }
            DataModel model = new DataModel("title" + i);
            data.add(model);
        }

        recyclerView.setAdapter(new ScaleInAnimationAdapter(new MyAdapter(data, getActivity())));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> childNodes = new ArrayList<>();
                int childSize = (int) (Math.random() * 10 + 5);
                for (int j = 0; j < childSize; j++) {
                    childNodes.add("child:" + j);
                }
                DataModel model = new DataModel("title");
                data.add(model);
                recyclerView.getAdapter().notifyItemInserted(data.size() - 1);
                handler.postDelayed(this, 500);
            }
        }, 500);
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_custom_recyclerview;
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        List<DataModel> items;
        private Context context;

        int width, height;

        public MyAdapter(List<DataModel> items, Context context) {
            this.items = items;
            this.context = context;
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            width = metrics.widthPixels;
            height = metrics.heightPixels;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.adapter_custom_recycler_animator, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            DataModel model = items.get(position);
//            if (position % 2 == 1) {
//                holder.textView.setBackground(new ColorDrawable(Color.BLUE));
//            } else {
//                holder.textView.setBackground(new ColorDrawable(Color.GREEN));
//            }
            holder.textView.setText(model.nodeName);
//            holder.containerView.setLayoutParams(new ViewGroup.LayoutParams((int) (width * 0.6), height));
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    public static class DataModel {
        public String nodeName;

        public DataModel(String nodeName) {
            this.nodeName = nodeName;
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewGroup containerView;


        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.header);
            containerView = (ViewGroup) itemView.findViewById(R.id.container);
        }
    }
}
