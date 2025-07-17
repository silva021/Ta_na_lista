package com.silva021.tanalista.data.dto

import com.silva021.tanalista.domain.model.User

data class UserDTO(
    val userUid: String = "",
    val email: String = "",
    val name: String = ""
)

fun UserDTO.mapFrom(): User {
    return User(
        userUid = this.userUid,
        email = this.email,
        name = this.name
    )
}