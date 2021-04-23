package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.*
import javax.persistence.*

@Entity
@Table(name = "groups")
data class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = -1,

    val name: String? = null,

    val level: Int? = null,

    @OneToMany(fetch = FetchType.LAZY ,cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH], mappedBy = "group")
    @JsonIgnoreProperties(value=["group", "absences"], allowSetters = true)
    val students: MutableSet<Student>? = mutableSetOf<Student>(),

) {

    @ManyToMany(fetch = FetchType.LAZY ,cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinTable(
        name = "group_subject",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "subject")]
    )
    @JsonIgnoreProperties("groups", allowSetters = true)
    val subjects: MutableSet<Subject> = mutableSetOf<Subject>()
}