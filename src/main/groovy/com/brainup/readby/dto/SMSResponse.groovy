package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class SMSResponse implements Serializable{

    @JsonProperty(value = 'responseCode')
    private String responseCode

    @JsonProperty(value = 'response')
    private String response

}
