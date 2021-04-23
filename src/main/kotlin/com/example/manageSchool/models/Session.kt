package com.example.manageSchool.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sessions")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = -1,


    var date: Date? = null,

    var startTime: String? = null,

    var endTime: String? = null,

    @ManyToOne(fetch = FetchType.EAGER,cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @JsonIgnoreProperties("groups")
    var subject: Subject? = null,

    @ManyToOne(fetch = FetchType.EAGER ,cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = ["subjects", "students"], allowSetters = true)
    var group: Group? = null,

    @OneToMany(fetch = FetchType.LAZY ,cascade = [CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH], mappedBy = "session")
    @JsonIgnoreProperties("session", allowSetters = true)
    var absences: MutableSet<Absence>? = mutableSetOf()
){

}