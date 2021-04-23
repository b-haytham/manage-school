package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonUnwrapped
import javax.persistence.*

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1,

    @OneToOne(cascade = [CascadeType.ALL])
    @JsonIgnoreProperties("id")
    @JsonUnwrapped
    var user: User? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH], mappedBy = "student")
    @JsonIgnoreProperties(value =["student", "session"], allowSetters = true)
    var absences: MutableSet<Absence>? = mutableSetOf()

){
    @ManyToOne(fetch = FetchType.LAZY ,cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = ["students", "subjects"], allowSetters = true)
    var group: Group? = null
}