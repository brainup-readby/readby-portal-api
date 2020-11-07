package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.*
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'user_login_details')
@Getter
@Setter
class UserLoginDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'login_id')
    @JsonProperty(value = 'LOGIN_ID')
    private Long loginId

    @Column(name = 'user_id')
    @JsonProperty(value = 'USER_ID')
    private Long userid

    @Column(name = 'mobile_no',length = 10)
    @JsonProperty(value = 'MOBILE_NO')
    private Long mobileNo

    @Column(name = 'created_at')
    @JsonProperty(value = 'CREATED_AT')
    @JsonIgnore
    private Timestamp createdAt = new Timestamp(new Date().getTime())

    @Column(name = 'created_by')
    @JsonProperty(value = 'CREATED_BY')
    @JsonIgnore
    private String createdBy

    @Column(name = 'updated_at')
    @JsonProperty(value = 'UPDATED_AT')
    @JsonIgnore
    private Timestamp updatedAt = new Timestamp(new Date().getTime())

    @Column(name = 'updated_by')
    @JsonProperty(value = 'UPDATED_BY')
    @JsonIgnore
    private String updatedBy

    @Column(name = 'token')
    @JsonProperty(value = 'TOKEN')
    private String token

    @Column(name = 'login_flag')
    @JsonProperty(value = 'LOGIN_FLAG')
    private String loginFlag
}
