package cn.com.canon.darwin.modules.service.api.subscriper;

import com.socks.library.KLog;

import cn.com.canon.darwin.modules.service.api.response.BaseResp;
import rx.Subscriber;

/**
 * Created by cmlBeliever on 2016/6/13.
 */
public abstract class BaseSubscriber<T> extends Subscriber<BaseResp<T>> {

    private static final String TAG = BaseSubscriber.class.getSimpleName();

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        KLog.e(TAG, e);
    }

    public abstract void onSuccess(BaseResp<T> resp);

    @Override
    public void onNext(BaseResp<T> tBaseResp) {
        if (null != tBaseResp) {
            //用户在其他地方登陆
            if (tBaseResp.loseToken()) {
                //TODO 设置信息
                KLog.e(TAG, "===========================================>loseToken!!!!");
            } else if (tBaseResp.success()) {
                onSuccess(tBaseResp);
            } else {
                //其他情况
                KLog.e(TAG, "====>" + tBaseResp.errorMsg);
            }
        }
    }
}
