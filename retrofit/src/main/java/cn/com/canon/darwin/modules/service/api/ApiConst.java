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
        String TIMELINE = "darwin_member_timeline.do";
        String MEMBER_STATUS = "darwin_member_status.do";
        String UNREAD_MSG_COUNT = "darwin_unread_message_count.do";
        String PHOTO_LIKE_ADD = "darwin_photo_like_add.do";
        String PHOTO_LIKE_CANCEL = "darwin_photo_like_cancel.do";
        String INITIALIZE = "darwin_initialize.do";
        String CHALLENGE_LIST = "darwin_member_challenge_list.do";
    }

    interface ApiId {
        String TIMELINE = "DARWIN_API_0012";
        String MEMBER_STATUS = "DARWIN_API_0038";
        String UNREAD_MSG_COUNT = "DARWIN_API_0062";
        String PHOTO_LIKE_ADD = "DARWIN_API_0048";
        String PHOTO_LIKE_CANCEL = "DARWIN_API_0053";
        String INITIALIZE = "DARWIN_API_0070";
        String CHALLENGE_LIST = "DARWIN_API_0013";
    }
}
