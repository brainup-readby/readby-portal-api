package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
public class User {

    @JsonProperty(value = 'user')
    private String user

    @JsonProperty(value = 'pwd')
    private String pwd

    @JsonProperty(value = 'token')
    private String token
}
