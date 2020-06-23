package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'user_details')
@Getter
@Setter
class UserDetails implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userid

    @Column(name = 'USERNAME')
    @JsonProperty(value = 'USERNAME')
    private String userName

    @Column(name = 'ROLE_ID')
    @JsonProperty(value = 'ROLE_ID')
    private Long roleId

    @Column(name = 'FIRST_NAME')
    @JsonProperty(value = 'FIRST_NAME')
    private String firstName

    @Column(name = 'MIDDLE_NAME')
    @JsonProperty(value = 'MIDDLE_NAME')
    private String middleName

    @Column(name = 'LAST_NAME')
    @JsonProperty(value = 'LAST_NAME')
    private String lastName

    @Column(name = 'LOGIN_PASSWORD')
    @JsonProperty(value = 'LOGIN_PASSWORD')
    private String loginPassword

    @Column(name = 'CLASS_ID')
    @JsonProperty(value = 'CLASS_ID')
    private Long classId

    @Column(name = 'MOBILE_NO')
    @JsonProperty(value = 'MOBILE_NO')
    private Long mobileNo

    @Column(name = 'EMAIL_ID')
    @JsonProperty(value = 'EMAIL_ID')
    private String emailId

    @Column(name = 'CITY')
    @JsonProperty(value = 'CITY')
    private String city

    @Column(name = 'STATE')
    @JsonProperty(value = 'STATE')
    private String state

    @Column(name = 'PINCODE')
    @JsonProperty(value = 'PINCODE')
    private String pincode

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive

    @Column(name = 'CREATED_AT')
    @JsonProperty(value = 'CREATED_AT')
    private Timestamp createdAt = new Timestamp(new Date().getTime())

    @Column(name = 'CREATED_BY')
    @JsonProperty(value = 'CREATED_BY')
    private String createdBy

    @Column(name = 'UPDATED_AT')
    @JsonProperty(value = 'UPDATED_AT')
    private Timestamp updatedAt = new Timestamp(new Date().getTime())

    @Column(name = 'UPDATED_BY')
    @JsonProperty(value = 'UPDATED_BY')
    private String updatedBy

    @Column(name = 'DEVICE_ID')
    @JsonProperty(value = 'DEVICE_ID')
    private String deviceId

    @Column(name = 'SESSION_TOKEN')
    @JsonProperty(value = 'SESSION_TOKEN')
    private String sessionToken

}
