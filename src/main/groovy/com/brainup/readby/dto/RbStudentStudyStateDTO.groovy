package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class RbStudentStudyStateDTO implements Serializable{

    @JsonProperty(value = 'STATE_ID')
    private Long stateId

    @JsonProperty(value = 'USER_ID')
    private Long userId

    @JsonProperty(value = 'TOPIC_ID')
    private Long topicId

    @JsonProperty(value = 'VIDEO_LEFT_TIME')
    private Long videoLeftTime
}
