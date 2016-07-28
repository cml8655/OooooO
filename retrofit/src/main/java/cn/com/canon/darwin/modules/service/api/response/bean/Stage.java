package cn.com.canon.darwin.modules.service.api.response.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmlBeliever on 2016/6/27.
 */
public class Stage implements Parcelable {
    public boolean isSeason;
    public Integer stageId;
    public String title;
    public Integer clearChallenge;
    public String comment;
    public String imageUrl;
    public String stageName;
    public Integer clearChallenges;
    public Integer allChallenges;
    public Integer showLevelUpFlg;
    public List<Challenge> challengeList = new ArrayList<>(1);


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isSeason ? (byte) 1 : (byte) 0);
        dest.writeValue(this.stageId);
        dest.writeString(this.title);
        dest.writeValue(this.clearChallenge);
        dest.writeString(this.comment);
        dest.writeString(this.imageUrl);
        dest.writeString(this.stageName);
        dest.writeValue(this.clearChallenges);
        dest.writeValue(this.allChallenges);
        dest.writeValue(this.showLevelUpFlg);
        dest.writeTypedList(this.challengeList);
    }

    public Stage() {
    }

    protected Stage(Parcel in) {
        this.isSeason = in.readByte() != 0;
        this.stageId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.clearChallenge = (Integer) in.readValue(Integer.class.getClassLoader());
        this.comment = in.readString();
        this.imageUrl = in.readString();
        this.stageName = in.readString();
        this.clearChallenges = (Integer) in.readValue(Integer.class.getClassLoader());
        this.allChallenges = (Integer) in.readValue(Integer.class.getClassLoader());
        this.showLevelUpFlg = (Integer) in.readValue(Integer.class.getClassLoader());
        this.challengeList = in.createTypedArrayList(Challenge.CREATOR);
    }

    public static final Parcelable.Creator<Stage> CREATOR = new Parcelable.Creator<Stage>() {
        @Override
        public Stage createFromParcel(Parcel source) {
            return new Stage(source);
        }

        @Override
        public Stage[] newArray(int size) {
            return new Stage[size];
        }
    };
}
