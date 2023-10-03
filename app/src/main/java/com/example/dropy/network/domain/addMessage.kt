package com.example.dropy.domain

fun addMessage(messageList:List<String>,message:String):List<String>{
    return if (messageList.contains(message)){
        messageList
    }else{
        messageList + message
    }
}