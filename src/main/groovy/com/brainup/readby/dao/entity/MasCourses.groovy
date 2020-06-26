package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.*
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_courses')
@Getter
@Setter
class MasCourses implements Serializable{

    @Id
    @Column(name = 'CLASS_ID')
    @JsonProperty(value = 'CLASS_ID')
    private Long classId

    @Column(name = 'COURSE_CODE')
    @JsonProperty(value = 'COURSE_CODE')
    private String courseCode

    @Column(name = 'COURSE_NAME')
    @JsonProperty(value = 'COURSE_NAME')
    private String courseName

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive

    @Column(name = 'CREATED_AT')
    @JsonProperty(value = 'CREATED_AT')
    @JsonIgnore
    private Timestamp createdAt = new Timestamp(new Date().getTime())

    @Column(name = 'CREATED_BY')
    @JsonProperty(value = 'CREATED_BY')
    @JsonIgnore
    private String createdBy

    @Column(name = 'UPDATED_AT')
    @JsonProperty(value = 'UPDATED_AT')
    @JsonIgnore
    private Timestamp updatedAt = new Timestamp(new Date().getTime())

    @Column(name = 'UPDATED_BY')
    @JsonProperty(value = 'UPDATED_BY')
    @JsonIgnore
    private String updatedBy

    @Column(name = 'icon_path')
    @JsonProperty(value = 'icon_path')
    private String iconPath

    @OneToMany(mappedBy = "masCourses", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(value = 'MAS_SUBJECTS')
    private List<MasSubjects> masSubjects
}
