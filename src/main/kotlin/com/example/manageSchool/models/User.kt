package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1,

    var firstName: String? = null,

    var lasName: String? = null,

    @Column(unique = true)
    var email: String? = null,

    @JsonIgnore
    var password: String? = null,

    @Enumerated(value = EnumType.STRING)
    var role: UserRoles? = null
)
