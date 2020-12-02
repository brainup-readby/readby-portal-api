package com.brainup.readby.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

import javax.persistence.Column

@Getter
@Setter
class UserDetailsDTO implements Serializable{

    @JsonProperty(value = 'USERNAME')
    private String userName

    @JsonProperty(value = 'FIRST_NAME')
    private String firstName

    @JsonProperty(value = 'MIDDLE_NAME')
    private String middleName

    @JsonProperty(value = 'LAST_NAME')
    private String lastName

    @JsonProperty(value = 'MOBILE_NO')
    private Long mobileNo

    @JsonProperty(value = 'EMAIL_ID')
    private String emailId
}
