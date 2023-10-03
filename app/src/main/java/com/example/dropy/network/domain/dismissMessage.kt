package com.example.dropy.domain

fun dismissMessage(messageList:List<String>,message:String):List<String>{
    return messageList.filterNot { it == message }
}