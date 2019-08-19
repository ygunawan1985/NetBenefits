package com.example.netbenefitsapp.model

import android.os.Parcel
import android.os.Parcelable

class Photo : Parcelable{

    var name: String? = null
    var url: String? = null

    constructor(name: String, url: String) {
        this.name = name
        this.url = url
    }

    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        url = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Photo> = object : Parcelable.Creator<Photo> {
            override fun createFromParcel(`in`: Parcel): Photo {
                return Photo(`in`)
            }

            override fun newArray(size: Int): Array<Photo?> {
                return arrayOfNulls(size)
            }
        }
    }
}