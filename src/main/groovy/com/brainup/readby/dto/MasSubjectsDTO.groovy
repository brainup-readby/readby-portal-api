package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

import javax.persistence.Column

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
}
