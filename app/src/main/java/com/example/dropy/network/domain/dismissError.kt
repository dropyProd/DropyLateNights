package com.example.dropy.domain

fun dismissError(errorList:List<String>,errorMessage:String):List<String>{
    return errorList.filterNot { it == errorMessage }
}