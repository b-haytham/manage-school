package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "subjects")
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = -1,

    var name: String? = null,

    @OneToOne(cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonIgnoreProperties("subject")
    var teacher: Teacher? = null
)