package com.example.netbenefitsapp.model

import android.os.Parcel
import android.os.Parcelable

//class User() : Parcelable {
//
//    var userName : String = ""
//    var email : String = ""
//    var firstName : String = ""
//    var lastName : String = ""
//    var ssn : String = ""
//    var company : String = ""
//    var insurance : String = ""
//    var balance : Int = 0
//
//    constructor(parcel: Parcel) : this() {
//        userName = parcel.readString().toString()
//        email = parcel.readString().toString()
//        firstName = parcel.readString().toString()
//        lastName = parcel.readString().toString()
//        ssn = parcel.readString().toString()
//        company = parcel.readString().toString()
//        insurance = parcel.readString().toString()
//        balance = parcel.readInt()
//    }
//
//    constructor(userName: String, email: String, firstName: String, lastName: String, ssn: String, company: String, insurance: String, balance: Int) : this() {
//        this.userName = userName
//        this.email = email
//        this.firstName = firstName
//        this.lastName = lastName
//        this.ssn = ssn
//        this.company = company
//        this.insurance = insurance
//        this.balance = balance
//    }
//
//    override fun writeToParcel(dest: Parcel?, p1: Int) {
//        dest?.writeValue(userName)
//        dest?.writeValue(email)
//        dest?.writeValue(firstName)
//        dest?.writeValue(lastName)
//        dest?.writeValue(ssn)
//        dest?.writeValue(company)
//        dest?.writeValue(insurance)
//        dest?.writeValue(balance)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<User> {
//        override fun createFromParcel(parcel: Parcel): User {
//            return User(parcel)
//        }
//
//        override fun newArray(size: Int): Array<User?> {
//            return arrayOfNulls(size)
//        }
//    }
//}

class User : Parcelable {

    var userName: String? = null
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var ssn: String? = null
    var company: String? = null
    var insurance: String? = null
    var balance: Int = 0

    constructor()

    constructor(
        userName: String,
        email: String,
        firstName: String,
        lastName: String,
        ssn: String,
        company: String,
        insurance: String,
        balance: Int
    ) {
        this.userName = userName
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
        this.ssn = ssn
        this.company = company
        this.insurance = insurance
        this.balance = balance
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