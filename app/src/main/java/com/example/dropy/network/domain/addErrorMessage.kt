package com.example.dropy.domain

fun addErrorMessage(errorList:List<String>,errorMessage:String):List<String>{
    return if (errorList.contains(errorMessage)){
        errorList
    }else{
        errorList + errorMessage
    }
}