package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*


@Entity
@Table(name = "absences")
data class Absence(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = -1,


        ) {
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
        @JoinColumn(name = "session_id", referencedColumnName = "id")
        @JsonIgnoreProperties(value = ["absences", "group"], allowSetters = true)
        var session: Session? = null

        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
        @JoinColumn(name = "subject_id", referencedColumnName = "id")
        @JsonIgnoreProperties(value = ["group"], allowSetters = true)
        var subject: Subject? = null

        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
        @JoinColumn(name = "student_id")
        @JsonIgnoreProperties(value = ["absences", "group", "grades"], allowSetters = true)
        var student: Student? = null
}