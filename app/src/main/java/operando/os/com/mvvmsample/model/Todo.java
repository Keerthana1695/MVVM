package operando.os.com.mvvmsample.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {

    public String title;
    public boolean isDone;

    public Todo(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeByte(isDone ? (byte) 1 : (byte) 0);
    }

    protected Todo(Parcel in) {
        this.title = in.readString();
        this.isDone = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
}
