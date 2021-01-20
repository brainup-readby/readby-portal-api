package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

import javax.persistence.Column
import javax.persistence.Transient

@Getter
@Setter
class MasSubjectsDTO implements Serializable{

    @Column(name = 'SUBJECT_ID')
    @JsonProperty(value = 'SUBJECT_ID')
    private Long subjectId

    @Column(name = 'SUBJECT_NAME')
    @JsonProperty(value = 'SUBJECT_NAME')
    private String subjectName

    @Column(name = 'SUBJECT_CODE')
    @JsonProperty(value = 'SUBJECT_CODE')
    private String subjectCode

    @Column(name = 'SUBJECT_PRICE')
    @JsonProperty(value = 'SUBJECT_PRICE')
    private Long subjectPrice

    @JsonProperty(value = 'icon_path')
    private String iconPath


    @Column(name = 'STREAM_ID')
    @JsonProperty(value = 'STREAM_ID')
    private Long streamId

    @Column(name = 'YEAR_ID')
    @JsonProperty(value = 'YEAR_ID')
    private Long yearId

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive

    @Transient
    @JsonProperty(value = 'STREAM_CODE')
    private String streamCode

    @Transient
    @JsonProperty(value = 'STREAM_NAME')
    private String streamName

    @Transient
    @JsonProperty(value = 'YEAR')
    private Long year

    @Transient
    @JsonProperty(value = 'YEAR_DISPLAY_NAME')
    private String yearDisplayName

    @Column(name = 'COURSE_ID')
    @JsonProperty(value = 'COURSE_ID')
    private Long courseId
}
