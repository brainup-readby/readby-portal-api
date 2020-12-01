package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class MasCoursesDTO implements Serializable{

    @JsonProperty(value = 'COURSE_ID')
    private Long courseId

    @JsonProperty(value = 'COURSE_CODE')
    private String courseCode

    @JsonProperty(value = 'COURSE_NAME')
    private String courseName

    @JsonProperty(value = 'COURSE_PRICE')
    private Long coursePrice
}
