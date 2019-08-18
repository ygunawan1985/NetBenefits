package com.example.netbenefitsapp.model

import android.os.Parcel
import android.os.Parcelable

class User : Parcelable {

    var userName: String? = null
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var ssn: String? = null
    var company: String? = null
    var insurance: String? = null
    var balance: Int = 0
    var companyLogoUrl: String? = null

    constructor()

    constructor(
        userName: String,
        email: String,
        firstName: String,
        lastName: String,
        ssn: String,
        company: String,
        insurance: String,
        balance: Int,
        companyLogoUrl: String
    ) {
        this.userName = userName
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
        this.ssn = ssn
        this.company = company
        this.insurance = insurance
        this.balance = balance
        this.companyLogoUrl = companyLogoUrl
    }

    protected constructor(`in`: Parcel) {
        userName = `in`.readString()
        email = `in`.readString()
        firstName = `in`.readString()
        lastName = `in`.readString()
        ssn = `in`.readString()
        company = `in`.readString()
        insurance = `in`.readString()
        balance = `in`.readInt()
        companyLogoUrl = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(userName)
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(ssn)
        parcel.writeString(company)
        parcel.writeString(insurance)
        parcel.writeInt(balance)
        parcel.writeString(companyLogoUrl)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(`in`: Parcel): User {
                return User(`in`)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }
}