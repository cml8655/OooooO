package cn.com.canon.darwin.modules.service.api.response;

import cn.com.canon.darwin.modules.service.api.ApiConst;

/**
 * Created by cmlBeliever on 2016/6/12.
 */
public class BaseResp<T> {
    public int status;
    public String appId;
    public String errorType;
    public String errorMsg;
    public T results;

    public boolean success() {
        return status == ApiConst.ApiCode.SUCCESS;
    }
    public boolean loseToken() {
        return status == ApiConst.ApiCode.LOSE_TOKEN;
    }
}
