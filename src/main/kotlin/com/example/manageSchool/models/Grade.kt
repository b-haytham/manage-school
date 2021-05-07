package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "grades")
data class Grade(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = -1,


        var ds: Double? = null,

        var ex: Double? = null,

        var moy: Double? = null,

        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
        @JoinColumn(name = "subject_id", referencedColumnName = "id")
        @JsonIgnoreProperties(value = ["groups"], allowSetters = true)
        var subject: Subject? = null,

        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
        @JoinColumn(name = "student_id", referencedColumnName = "id")
        @JsonIgnoreProperties(value = ["grades", "absences", "group"], allowSetters = true)
        var student: Student? = null
)