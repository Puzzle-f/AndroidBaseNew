package com.example.alculator;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculation implements Parcelable {
    Integer firstNumber;
    Integer lastNumber;
    String operator = "";

    Calculation(int firstNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    public Integer sum() {
        return firstNumber = firstNumber + lastNumber;
    }

    public Integer minus() {
        return firstNumber = firstNumber - lastNumber;
    }

    public Integer multi() {
        return firstNumber = firstNumber * lastNumber;
    }

    public Integer division() {
        return firstNumber = firstNumber / lastNumber;
    }

    public String result() {
        String i = "";
        switch (operator) {
            case "+":
                i = sum().toString();
                break;
            case "-":
                i = minus().toString();
                break;
            case "*":
                i = multi().toString();
                break;
            case "/":
                i = division().toString();
                break;
        }
        return i;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (firstNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(firstNumber);
        }
        if (lastNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(lastNumber);
        }
        dest.writeString(operator);
    }

    public static final Creator<Calculation> CREATOR = new Creator<Calculation>() {
        @Override
        public Calculation createFromParcel(Parcel in) {
            return new Calculation(in);
        }

        @Override
        public Calculation[] newArray(int size) {
            return new Calculation[size];
        }
    };

    protected Calculation(Parcel in) {
        if (in.readByte() == 0) {
            firstNumber = null;
        } else {
            firstNumber = in.readInt();
        }
        if (in.readByte() == 0) {
            lastNumber = null;
        } else {
            lastNumber = in.readInt();
        }
        operator = in.readString();
    }
}
