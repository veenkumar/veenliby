package com.veen.veenkumar

import android.os.Parcel
import android.os.Parcelable

class BindData(
    var value1: String?,
    var value2: String?,
    var value3: String?,
    var value4: String?,
    var value5: String?,
    var value6: String?,
    var value7: String?,
    var value8: String?,
    var value9: String?,
    var value10: String?,
    var value11: String?,
    var value12: String?,
    var value13: String?,
    var value14: String?,

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(value1)
        parcel.writeString(value2)
        parcel.writeString(value3)
        parcel.writeString(value4)
        parcel.writeString(value5)
        parcel.writeString(value6)
        parcel.writeString(value7)
        parcel.writeString(value8)
        parcel.writeString(value9)
        parcel.writeString(value10)
        parcel.writeString(value11)
        parcel.writeString(value12)
        parcel.writeString(value13)
        parcel.writeString(value14)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BindData> {
        override fun createFromParcel(parcel: Parcel): BindData {
            return BindData(parcel)
        }

        override fun newArray(size: Int): Array<BindData?> {
            return arrayOfNulls(size)
        }
    }
}
