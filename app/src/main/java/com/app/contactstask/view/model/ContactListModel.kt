package com.app.contactstask.view.model

class ContactListModel {


    private var name: String? = null
    private  var number:String? = null
    private  var image:String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getNumber(): String? {
        return number
    }

    fun setNumber(number: String) {
        this.number = number
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

}