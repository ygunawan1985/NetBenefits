package com.example.netbenefitsapp.model

data class User(
    var userName : String = "",
    var email : String = "",
    var firstName : String = "",
    var lastName : String = "",
    var ssn : String = "",
    var company : String = "",
    var insurance : String = "",
    var balance : Int)