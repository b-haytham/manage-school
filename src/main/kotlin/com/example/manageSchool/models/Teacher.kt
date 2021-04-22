package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "teachers")
data class Teacher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1,

    @OneToOne(cascade = [CascadeType.ALL])
    var user: User? = null,

    @OneToOne(mappedBy = "teacher", cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JsonIgnoreProperties("teacher")
    var subject: Subject? = null
)
