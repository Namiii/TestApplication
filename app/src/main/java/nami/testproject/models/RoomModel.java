package nami.testproject.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RoomModel implements Parcelable {
    public String name;
    public String price;
    public ArrayList<String> notes;
    public int roomsLeft;
    public boolean isSelected;

    public RoomModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeStringList(this.notes);
        dest.writeInt(this.roomsLeft);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected RoomModel(Parcel in) {
        this.name = in.readString();
        this.price = in.readString();
        this.notes = in.createStringArrayList();
        this.roomsLeft = in.readInt();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<RoomModel> CREATOR = new Creator<RoomModel>() {
        @Override
        public RoomModel createFromParcel(Parcel source) {
            return new RoomModel(source);
        }

        @Override
        public RoomModel[] newArray(int size) {
            return new RoomModel[size];
        }
    };
}
