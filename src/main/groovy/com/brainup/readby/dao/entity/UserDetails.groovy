package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.*

import javax.persistence.*
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
    @JsonIgnore
    private String loginPassword

    @Column(name = 'MOBILE_NO',length = 10)
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
    @JsonIgnore
    private Timestamp createdAt = new Timestamp(new Date().getTime())

    @Column(name = 'CREATED_BY')
    @JsonProperty(value = 'CREATED_BY')
    @JsonIgnore
    private String createdBy

    @Column(name = 'UPDATED_AT')
    @JsonProperty(value = 'UPDATED_AT')
    @JsonIgnore
    private Timestamp updatedAt = new Timestamp(new Date().getTime())

    @Column(name = 'UPDATED_BY')
    @JsonProperty(value = 'UPDATED_BY')
    @JsonIgnore
    private String updatedBy

    @Column(name = 'DEVICE_ID')
    @JsonProperty(value = 'DEVICE_ID')
    private String deviceId

    @Column(name = 'SESSION_TOKEN')
    @JsonProperty(value = 'SESSION_TOKEN')
    private String sessionToken

   /* @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="COURSE_ID")
    @JsonProperty(value = 'MAS_COURSES')
    private MasCourses masCourses*/

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = 'USER_ID')
    @JsonProperty(value = 'USER_SUBSCRIPTION')
    private List<UserSubscriptions> userSubscriptions = new ArrayList<>()

    @Column(name = 'INSTITUTION_NAME')
    @JsonProperty(value = 'INSTITUTION_NAME')
    private String institutionName

}
