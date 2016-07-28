package cn.com.canon.darwin.modules.service.api;

import java.util.Map;

import cn.com.canon.darwin.modules.service.api.response.ChallengeResp;
import cn.com.canon.darwin.modules.service.api.response.FavoriteResp;
import cn.com.canon.darwin.modules.service.api.response.MemberStatusResp;
import cn.com.canon.darwin.modules.service.api.response.TimelineResp;
import cn.com.canon.darwin.modules.service.api.response.UnReadCountResp;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by cmlBeliever on 2015/11/17.
 * API请求
 */
public interface ApiService {

    //    @POST(ApiConstant.Api.USER)
    @FormUrlEncoded
    Observable<String> getUsers(@Field("name") String name);

    @FormUrlEncoded
    @POST(ApiConst.Api.TIMELINE)
    Observable<TimelineResp> timeline(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(ApiConst.Api.MEMBER_STATUS)
    Observable<MemberStatusResp> memberStatus(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(ApiConst.Api.UNREAD_MSG_COUNT)
    Observable<UnReadCountResp> unReadMsgCount(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(ApiConst.Api.PHOTO_LIKE_ADD)
    Observable<FavoriteResp> photoLikeAdd(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(ApiConst.Api.PHOTO_LIKE_CANCEL)
    Observable<FavoriteResp> photoLikeCancel(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(ApiConst.Api.INITIALIZE)
    Observable<String> initialize(@FieldMap Map<String, String> param);

    @FormUrlEncoded
    @POST(ApiConst.Api.CHALLENGE_LIST)
    Observable<ChallengeResp> challengeList(@FieldMap Map<String, String> param);
}
