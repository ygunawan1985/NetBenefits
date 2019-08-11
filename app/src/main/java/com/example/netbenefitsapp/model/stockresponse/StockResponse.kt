package com.example.netbenefitsapp.model.stockresponse

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StockResponse : Parcelable {

    @SerializedName("date")
    @Expose
    var date: String? = null
    @SerializedName("open")
    @Expose
    var open: Float? = null
    @SerializedName("high")
    @Expose
    var high: Float? = null
    @SerializedName("low")
    @Expose
    var low: Float? = null
    @SerializedName("close")
    @Expose
    var close: Float? = null
    @SerializedName("adjusted_close")
    @Expose
    var adjustedClose: Float? = null
    @SerializedName("volume")
    @Expose
    var volume: Int? = null

    protected constructor(`in`: Parcel) {
        this.date = `in`.readValue(String::class.java.classLoader) as String
        this.open = `in`.readValue(Float::class.java.classLoader) as Float
        this.high = `in`.readValue(Float::class.java.classLoader) as Float
        this.low = `in`.readValue(Float::class.java.classLoader) as Float
        this.close = `in`.readValue(Float::class.java.classLoader) as Float
        this.adjustedClose = `in`.readValue(Float::class.java.classLoader) as Float
        this.volume = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param open
     * @param adjustedClose
     * @param volume
     * @param high
     * @param low
     * @param date
     * @param close
     */
    constructor(
        date: String,
        open: Float?,
        high: Float?,
        low: Float?,
        close: Float?,
        adjustedClose: Float?,
        volume: Int?
    ) : super() {
        this.date = date
        this.open = open
        this.high = high
        this.low = low
        this.close = close
        this.adjustedClose = adjustedClose
        this.volume = volume
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(date)
        dest.writeValue(open)
        dest.writeValue(high)
        dest.writeValue(low)
        dest.writeValue(close)
        dest.writeValue(adjustedClose)
        dest.writeValue(volume)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StockResponse> = object : Creator<StockResponse> {


            override fun createFromParcel(`in`: Parcel): StockResponse {
                return StockResponse(`in`)
            }

            override fun newArray(size: Int): Array<StockResponse?> {
                return arrayOfNulls(size)
            }

        }
    }

}
