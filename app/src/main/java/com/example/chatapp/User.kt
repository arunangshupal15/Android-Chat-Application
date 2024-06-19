package com.example.chatapp



class User {

    var name:String?=null
    var email:String?=null
    var uid:String?=null

    constructor(){}

    constructor(NAME: String?,EMAIL:String?,uid:String){

        this.name=NAME
        this.email=EMAIL
        this.uid=uid
    }

}