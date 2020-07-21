package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_course_type')
@Getter
@Setter
class MasCoursesType implements Serializable{


    @Id
    @Column(name = 'COURSE_TYPE_ID')
    @JsonProperty(value = 'COURSE_TYPE_ID')
    private Long courseTypeId

    @Column(name = 'COURSE_TYPE_NAME')
    @JsonProperty(value = 'COURSE_TYPE_NAME')
    private String courseTypeName

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive

    @OneToMany(mappedBy = "masCoursesType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(value = 'MAS_COURSES')
    private List<MasCourses> masCourses
}
