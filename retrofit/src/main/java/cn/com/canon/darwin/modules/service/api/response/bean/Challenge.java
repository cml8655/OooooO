package cn.com.canon.darwin.modules.service.api.response.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cmlBeliever on 2016/6/27.
 */
public class Challenge implements Parcelable {
    public boolean isSeason;
    public Integer challengeId;
    public String challengeNo;
    public String name;
    public String title;
    public String comment;
    public Integer clearConditionUser;
    public Integer clearConditionTeacher;
    public String imageUrl;
    public String photoUrl;
    public Integer challengeStatus;
    public Integer showClearFlg;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isSeason ? (byte) 1 : (byte) 0);
        dest.writeValue(this.challengeId);
        dest.writeString(this.challengeNo);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.comment);
        dest.writeValue(this.clearConditionUser);
        dest.writeValue(this.clearConditionTeacher);
        dest.writeString(this.imageUrl);
        dest.writeString(this.photoUrl);
        dest.writeValue(this.challengeStatus);
        dest.writeValue(this.showClearFlg);
    }

    public Challenge() {
    }

    protected Challenge(Parcel in) {
        this.isSeason = in.readByte() != 0;
        this.challengeId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.challengeNo = in.readString();
        this.name = in.readString();
        this.title = in.readString();
        this.comment = in.readString();
        this.clearConditionUser = (Integer) in.readValue(Integer.class.getClassLoader());
        this.clearConditionTeacher = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imageUrl = in.readString();
        this.photoUrl = in.readString();
        this.challengeStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.showClearFlg = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Challenge> CREATOR = new Parcelable.Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(Parcel source) {
            return new Challenge(source);
        }

        @Override
        public Challenge[] newArray(int size) {
            return new Challenge[size];
        }
    };
}
