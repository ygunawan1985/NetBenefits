package com.example.netbenefitsapp.model

import android.os.Parcel
import android.os.Parcelable

class Video : Parcelable {
    var title: String? = null
    var description: String? = null
    var url: String? = null

    constructor(title: String, description: String, url: String) {
        this.title = title
        this.description = description
        this.url = url
    }

    protected constructor(`in`: Parcel) {
        title = `in`.readString()
        description = `in`.readString()
        url = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(`in`: Parcel): Video {
                return Video(`in`)
            }

            override fun newArray(size: Int): Array<Video?> {
                return arrayOfNulls(size)
            }
        }
    }
}