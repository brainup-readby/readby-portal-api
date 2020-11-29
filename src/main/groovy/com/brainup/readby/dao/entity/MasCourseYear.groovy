package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_course_years')
@Getter
@Setter
@ToString
class MasCourseYear implements Serializable{

    @Id
    @Column(name = 'YEAR_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'YEAR_ID')
    private Long yearId

    @Column(name = 'YEAR')
    @JsonProperty(value = 'YEAR')
    private Long year

    @ManyToOne
    @JoinColumn( name = "COURSE_ID")
    private MasCourses masCourses

    @Column(name = 'DISPLAY_NAME')
    @JsonProperty(value = 'DISPLAY_NAME')
    private String displayName

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive

}
