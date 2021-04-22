package com.example.manageSchool.models

import javax.persistence.*

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1,

    @OneToOne(cascade = [CascadeType.ALL])
    var user: User? = null
)