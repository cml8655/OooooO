package com.cml.second.app.baby.fragment;

import android.content.Context;
import android.view.View;

import com.cml.newframe.explosioncollect.ExplossionCollect;
import com.cml.second.app.baby.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/8/2.
 * demo from explosioncolect
 */
public class ExplosionCollectFragment extends BaseFragment {

    private ExplossionCollect explossionCollect;

    @Bind(R.id.explossion_collect_container)
    View collectView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        explossionCollect = ExplossionCollect.createExplossionCollect(getActivity());
    }

    @Override
    public void onDetach() {
        ExplossionCollect.removeExplossionCollect(getActivity());
        explossionCollect = null;
        super.onDetach();
    }

    @OnClick({R.id.explossion_collect_del, R.id.explossion_collect_img})
    public void onClick(View v) {
        explossionCollect.start(v, collectView);
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_explossion_collect;
    }
}
