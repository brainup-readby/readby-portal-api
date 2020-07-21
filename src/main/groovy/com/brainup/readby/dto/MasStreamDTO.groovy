package com.brainup.readby.dto

import com.brainup.readby.dao.entity.MasSubjects
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

import javax.persistence.Column

@Getter
@Setter
class MasStreamDTO implements Serializable{

    @JsonProperty(value = 'STREAM_ID')
    private Long streamId

    @JsonProperty(value = 'STREAM_CODE')
    private String streamCode

    @JsonProperty(value = 'STREAM_NAME')
    private String streamName


    @JsonProperty(value = 'MAS_SUBJECT')
    private List<MasSubjects> masSubjects = new ArrayList<>()
}
