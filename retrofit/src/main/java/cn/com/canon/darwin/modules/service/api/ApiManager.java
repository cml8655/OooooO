package cn.com.canon.darwin.modules.service.api;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.com.canon.darwin.modules.service.api.converter.JsonAndStringConverter;
import cn.com.canon.darwin.modules.service.api.interceptor.HttpLoggingInterceptor;
import cn.com.canon.darwin.modules.service.api.response.BaseResp;
import cn.com.canon.darwin.modules.service.api.response.ChallengeResp;
import cn.com.canon.darwin.modules.service.api.response.MemberStatusResp;
import cn.com.canon.darwin.modules.service.api.response.result.ChallengeResult;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by cmlBeliever on 2015/11/17.
 */
public class ApiManager {

    private static final String TAG = ApiManager.class.getSimpleName();

    private static Retrofit retrofit;
    private static ApiService apiService;

    public static void init(final String baseUrl) {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(new JsonAndStringConverter()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
            //retrofit.client().setCache(new Cache());
            retrofit.client().setConnectTimeout(ApiConst.DEFAULT_TIME_OUT, TimeUnit.SECONDS);
            retrofit.client().interceptors().add(new HttpLoggingInterceptor());
        }
    }

    public static <T> T createService(Class<T> target) {
        return retrofit.create(target);
    }

    public static ApiService getApiService() {
        if (null != apiService) {
            return apiService;
        }
        return retrofit.create(ApiService.class);
    }

    public static Observable<MemberStatusResp> memberStatus(String token, String userId, String authCode, String targetUserId) {
        Map<String, String> param = new HashMap<>();
        param.put("token", token);
        param.put("userId", userId);
        param.put("authCd", authCode);
        if (null != targetUserId) {
            param.put("targetUserId", targetUserId);
        }

        return getApiService().memberStatus(param);
    }


    public static Observable<String> initialize(String version, String syncTime, String authCode) {
        Map<String, String> param = new HashMap<>();
        param.put("version", version);
        param.put("device", "4");
        param.put("authCd", authCode);
        param.put("syncTime", syncTime);
        return getApiService().initialize(param);
    }


    public static Observable<ChallengeResp> challengeList(String autchCode, String token, String userId, int stageCount) {
        Map<String, String> param = new HashMap<>();
        param.put("searchType", "1");//全部级别数据
        param.put("token", token);
        param.put("userId", userId);
//        param.put("seasonStageCount", stageCount + "");
        param.put("authCd", autchCode);

        return getApiService().challengeList(param);
    }

}
