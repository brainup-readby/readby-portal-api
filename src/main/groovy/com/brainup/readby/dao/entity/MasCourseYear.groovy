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
    @JsonProperty(value = 'YEAR_ID')
    private Long yearId

    @Column(name = 'YEAR')
    @JsonProperty(value = 'YEAR')
    private Long year

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "COURSE_ID"), name = "COURSE_ID",insertable = false,updatable = false)
    private MasCourses masCourses

    @Column(name = 'DISPLAY_NAME')
    @JsonProperty(value = 'DISPLAY_NAME')
    private String displayName

}
