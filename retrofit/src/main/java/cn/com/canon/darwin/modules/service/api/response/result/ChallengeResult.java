package cn.com.canon.darwin.modules.service.api.response.result;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import cn.com.canon.darwin.modules.service.api.response.bean.Challenge;
import cn.com.canon.darwin.modules.service.api.response.bean.Stage;
import cn.com.canon.darwin.modules.service.api.response.bean.UserStatus;

/**
 * Created by cmlBeliever on 2016/6/27.
 */
public class ChallengeResult implements Parcelable {
    public UserStatus userStatus;
    public List<Challenge> seasonChallengeList = new ArrayList<>();
    public List<Stage> stageList = new ArrayList<>();
    public Stage nextStage;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.userStatus, flags);
        dest.writeTypedList(this.seasonChallengeList);
        dest.writeTypedList(this.stageList);
        dest.writeParcelable(this.nextStage, flags);
    }

    public ChallengeResult() {
    }

    protected ChallengeResult(Parcel in) {
        this.userStatus = in.readParcelable(UserStatus.class.getClassLoader());
        this.seasonChallengeList = in.createTypedArrayList(Challenge.CREATOR);
        this.stageList = in.createTypedArrayList(Stage.CREATOR);
        this.nextStage = in.readParcelable(Stage.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChallengeResult> CREATOR = new Parcelable.Creator<ChallengeResult>() {
        @Override
        public ChallengeResult createFromParcel(Parcel source) {
            return new ChallengeResult(source);
        }

        @Override
        public ChallengeResult[] newArray(int size) {
            return new ChallengeResult[size];
        }
    };
}
