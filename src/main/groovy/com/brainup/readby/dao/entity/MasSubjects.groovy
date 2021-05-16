package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString

import javax.persistence.*
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_subjects')
@Getter
@Setter
@ToString
class MasSubjects implements Serializable{

    @Id
    @Column(name = 'SUBJECT_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'SUBJECT_ID')
    private Long subjectId

    @Column(name = 'SUBJECT_NAME')
    @JsonProperty(value = 'SUBJECT_NAME')
    private String subjectName

    @Column(name = 'SUBJECT_CODE')
    @JsonProperty(value = 'SUBJECT_CODE')
    private String subjectCode

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

    @Column(name = 'STREAM_ID')
    @JsonProperty(value = 'STREAM_ID')
    private Long streamId

    @Column(name = 'YEAR_ID')
    @JsonProperty(value = 'YEAR_ID')
    private Long yearId

   /* @Column(name = 'COURSE_ID')
    @JsonProperty(value = 'COURSE_ID')
    private Long courseId*/

   /* @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "COURSE_ID"), name = "COURSE_ID",insertable = false,updatable = false)
    private MasCourses masCourses*/

    @OneToMany(mappedBy = "masSubjects", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(value = 'MAS_CHAPTERS')
    private List<MasChapters> masChapters

    @Column(name = 'SUBJECT_PRICE')
    @JsonProperty(value = 'SUBJECT_PRICE')
    private Long subjectPrice

    @Transient
    @JsonProperty(value = "SUBJECT_PROGRESS")
    private Double percentage

    @Transient
    @JsonProperty(value = "MAS_STREAM")
    MasStream masStream

    @Transient
    @JsonProperty(value = 'MAS_COURSE_YEAR')
    private MasCourseYear masCourseYear

    @Transient
    @JsonProperty(value = 'COURSE_ID')
    private Long courseId

    @Transient
    @JsonProperty(value = 'SRC_SUB_ID')
    private Long srcSubId

}
