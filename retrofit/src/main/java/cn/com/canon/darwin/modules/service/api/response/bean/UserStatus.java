package cn.com.canon.darwin.modules.service.api.response.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cmlBeliever on 2016/6/27.
 */
public class UserStatus implements Parcelable {
    public Integer stageId;
    public Integer clearChallenges;
    public Integer allChallenges;
    public Integer nextChallenge;
    public String stageTitle;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.stageId);
        dest.writeValue(this.clearChallenges);
        dest.writeValue(this.allChallenges);
        dest.writeValue(this.nextChallenge);
        dest.writeString(this.stageTitle);
    }

    public UserStatus() {
    }

    protected UserStatus(Parcel in) {
        this.stageId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.clearChallenges = (Integer) in.readValue(Integer.class.getClassLoader());
        this.allChallenges = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nextChallenge = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stageTitle = in.readString();
    }

    public static final Parcelable.Creator<UserStatus> CREATOR = new Parcelable.Creator<UserStatus>() {
        @Override
        public UserStatus createFromParcel(Parcel source) {
            return new UserStatus(source);
        }

        @Override
        public UserStatus[] newArray(int size) {
            return new UserStatus[size];
        }
    };
}
