package br.com.danieljunior.deolhonaescola.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by danieljunior on 11/01/17.
 */

public class CustomBarEntry implements Parcelable {

    private int x, y;

    public CustomBarEntry(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CustomBarEntry() {
    }

    protected CustomBarEntry(Parcel in) {
        x = in.readInt();
        y = in.readInt();
    }

    public static final Creator<CustomBarEntry> CREATOR = new Creator<CustomBarEntry>() {
        @Override
        public CustomBarEntry createFromParcel(Parcel in) {
            return new CustomBarEntry(in);
        }

        @Override
        public CustomBarEntry[] newArray(int size) {
            return new CustomBarEntry[size];
        }
    };

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
    }
}
