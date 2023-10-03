package com.example.dropy.network.models.createUser

data class Dropyuser(
    val email: String?,
    val first_name: String?,
    val groups: List<Any>?,
    val id: Int?,
    val is_active: Boolean?,
    val is_staff: Boolean?,
    val is_superuser: Boolean?,
    val last_login: Any?,
    val last_name: String?,
    val password: String?,
    val phone_number: Int?,
    val profile_image: Any?,
    val user_name: String?,
    val user_permissions: List<Any>?
)