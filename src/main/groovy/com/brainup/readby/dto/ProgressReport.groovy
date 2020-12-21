package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ProgressReport implements Serializable{

    @JsonProperty(value = 'SUBSCRIPTION_ID')
    private Long subscriptionId

    @JsonProperty(value = 'TOTAL_COUNT')
    private Integer totalCount

    @JsonProperty(value = 'TOTAL_PROGRESS_COUNT')
    private Integer totalProgressCount

    @JsonProperty(value = 'COURSE_NAME')
    private String courseName
}
