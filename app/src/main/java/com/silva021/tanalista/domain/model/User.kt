package com.pgolcursos.biblequiz.domain.model

data class User(
    val userUid: String,
    val name: String,
    val email: String,
    val numberContact: String,
    val pixKey: String,
    val balance: Float,
    val currentStage: Int,
)