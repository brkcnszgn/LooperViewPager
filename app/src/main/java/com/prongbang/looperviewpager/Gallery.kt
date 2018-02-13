package com.prongbang.looperviewpager

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mdev on 2/13/2018 AD.
 */
data class Gallery(
        var id: Int,
        var name: String,
        var image: String
) : Parcelable {

    constructor() : this(0, "", "")

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(name)
        writeString(image)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Gallery> = object : Parcelable.Creator<Gallery> {
            override fun createFromParcel(source: Parcel): Gallery = Gallery(source)
            override fun newArray(size: Int): Array<Gallery?> = arrayOfNulls(size)
        }
    }
}