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
@Table(name = 'otp_info')
@Getter
@Setter
class OtpInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'otp_id')
    @JsonProperty(value = 'otp_id')
    private Long otpId

    @Column(name = 'OTP')
    @JsonProperty(value = 'OTP')
    private String otp

    @Column(name = 'EXPIRE')
    @JsonProperty(value = 'EXPIRE')
    private Timestamp expire

    @Column(name = 'MOBILE_NO')
    @JsonProperty(value = 'MOBILE_NO')
    private Long mobileNo

    @Column(name = 'RETRY_LIMIT')
    @JsonProperty(value = 'RETRY_LIMIT')
    private Integer retryLimit

    @Column(name = 'PARENT_OTP_ID')
    @JsonProperty(value = 'PARENT_OTP_ID')
    private Long parentOtpId

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

    @Column(name = 'is_used')
    @JsonProperty(value = 'is_used')
    private String isUsed

}
