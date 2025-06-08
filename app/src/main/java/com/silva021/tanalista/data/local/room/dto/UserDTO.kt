package com.pgolcursos.biblequiz.data.model

import com.pgolcursos.biblequiz.domain.model.User

data class UserDTO(
    val userUid: String = "",
    val email: String = "",
    val name: String = "",
    val pixKey: String = "",
    val balance: Float = 0f,
    val numberContact: String = "",
    val currentStage: Int = 1,
)

fun UserDTO.mapFrom(): User {
    return User(
        userUid = this.userUid,
        email = this.email,
        name = this.name,
        pixKey = pixKey,
        balance = this.balance,
        currentStage = this.currentStage,
        numberContact = this.numberContact
    )
}