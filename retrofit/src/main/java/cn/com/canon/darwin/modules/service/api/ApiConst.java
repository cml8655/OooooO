package cn.com.canon.darwin.modules.service.api;

/**
 * Created by cmlBeliever on 2016/6/12.
 */
public interface ApiConst {
    int DEFAULT_TIME_OUT = 15;

    interface ApiCode {
        int SUCCESS = 200;
        int LOSE_TOKEN = 401;
    }

    interface Api {
        String TIMELINE = "TIMELINE.do";
        String MEMBER_STATUS = "MEMBER_STATUS.do";
        String UNREAD_MSG_COUNT = "UNREAD_MSG_COUNT.do";
        String PHOTO_LIKE_ADD = "PHOTO_LIKE_ADD.do";
        String PHOTO_LIKE_CANCEL = "PHOTO_LIKE_CANCEL.do";
        String INITIALIZE = "INITIALIZE.do";
        String CHALLENGE_LIST = "CHALLENGE_LIST.do";
    }

    interface ApiId {
        String TIMELINE = "API_0012";
        String MEMBER_STATUS = "API_0038";
        String UNREAD_MSG_COUNT = "API_0062";
        String PHOTO_LIKE_ADD = "API_0048";
        String PHOTO_LIKE_CANCEL = "API_0053";
        String INITIALIZE = "API_0070";
        String CHALLENGE_LIST = "API_0013";
    }
}
