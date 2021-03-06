package com.kidscademy.quiz.instruments;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.kidscademy.quiz.app.App;
import com.kidscademy.quiz.model.StorageObject;
import com.kidscademy.quiz.app.Storage;

import js.util.Strings;

/**
 * Model class for musical instrument.
 *
 * @author Iulian Rotaru
 */
public class Instrument implements StorageObject, Parcelable {
    /**
     * Index into instruments array from storage. Note that instruments storage is sorted by rank, descendant.
     */
    private int index;
    /**
     * Instrument name used internally by application logic. Is not meant to be displayed on user interface
     * but to identify instruments and attached resources like image file from assert.
     */
    private String name;

    private String localeName;

    private int rank;

    /**
     * Counter incremented every time this instrument is used by a quiz challenge with correct answer.
     */
    private int quizCounter;

    private String picturePath;

    public Instrument() {
    }

    /**
     * Test constructor.
     *
     * @param index
     * @param name
     */
    public Instrument(int index, String name) {
        this.index = 0;
        this.name = name;
        this.localeName = name;
    }

    public Instrument(Parcel parcel) {
        this.index = parcel.readInt();
        this.name = parcel.readString();
        this.localeName = parcel.readString();
        this.rank = parcel.readInt();
        this.quizCounter = parcel.readInt();
        this.picturePath = parcel.readString();
    }

    @Override
    public void onCreate(Storage storage) {
        assert picturePath == null;
        picturePath = Strings.concat("image/", name, ".png");

        assert localeName == null;
        Context context = App.instance().getApplicationContext();
        int stringId = context.getResources().getIdentifier(name, "string", context.getPackageName());
        if (stringId != 0) {
            localeName = context.getResources().getString(stringId);
        }
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getLocaleName() {
        return localeName;
    }

    public int getRank() {
        return rank;
    }

    public int getQuizCounter() {
        return quizCounter;
    }

    public void incrementQuizCounter() {
        ++this.quizCounter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Instrument other = (Instrument) obj;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(index);
        parcel.writeString(name);
        parcel.writeString(localeName);
        parcel.writeInt(rank);
        parcel.writeInt(quizCounter);
        parcel.writeString(picturePath);
    }

    public static final Parcelable.Creator<Instrument> CREATOR = new Parcelable.Creator<Instrument>() {
        public Instrument createFromParcel(Parcel parcel) {
            return new Instrument(parcel);
        }

        public Instrument[] newArray(int size) {
            return new Instrument[size];
        }
    };
}
