package com.vk.dukan.model

data class UserDetails(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val authorities: List<Any>,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean,
    val password: String,
    val username: String
)