package cn.com.canon.darwin.modules.service.api.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cmlBeliever on 2016/6/12.
 */
public class TimelineReq extends BaseReq {
    public int start;
    public int count;

    public Map<String, String> toMap() {
        Map<String, String> param = new HashMap<>();
        param.put("count", count + "");
        param.put("start", start + "");
        param.put("authCd", authCd);
        param.put("token", token);
        param.put("userId", userId);
        return param;
    }
}
