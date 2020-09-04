package com.brainup.readby.dao.entity

import com.brainup.readby.dto.MasBoardDTO
import com.brainup.readby.dto.MasCoursesDTO
import com.brainup.readby.dto.MasStreamDTO
import com.brainup.readby.dto.RbStudentStudyStateDTO
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
@Table(name = 'user_subscriptions')
@Getter
@Setter
class UserSubscriptions implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'SUBSCRIPTION_ID')
    @JsonProperty(value = 'SUBSCRIPTION_ID')
    private Long subscriptionId

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userid

    @Column(name = 'STREAM_ID')
    @JsonProperty(value = 'STREAM_ID')
    private Long streamId

    @Column(name = 'YEAR_ID')
    @JsonProperty(value = 'YEAR_ID')
    private Long yearId

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

    @Column(name = 'IS_EXPIRED')
    @JsonProperty(value = 'IS_EXPIRED')
    private String isExpired

    /*@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="COURSE_ID")
    @JsonProperty(value = 'MAS_COURSE')
    private MasCourses masCourses*/

    @Column(name = 'COURSE_ID')
    @JsonProperty(value = 'COURSE_ID')
    private Long courseId

    @Column(name = 'BOARD_ID')
    @JsonProperty(value = 'BOARD_ID')
    private Long boardId


    @Column(name = 'MOBILE_NO',length = 10)
    @JsonProperty(value = 'MOBILE_NO')
    private Long mobileNo

    @Column(name = 'COURSE_STREAM_ID')
    @JsonProperty(value = 'COURSE_STREAM_ID')
    private Long courseStreamId

    @Transient
    @JsonProperty(value = "MAS_STREAM")
    MasStreamDTO masStream

    @Transient
    @JsonProperty(value = "MAS_COURSE_YEAR")
    MasCourseYear masCourseYear

    @Transient
    @JsonProperty(value = "MAS_BOARD")
    MasBoardDTO masBoard

    @Transient
    @JsonProperty(value = "MAS_COURSE")
    MasCoursesDTO masCourses

    @Transient
    @JsonProperty(value = "STUDENT_STUDY_STATE")
    RbStudentStudyStateDTO rbStudentStudyState

    @Column(name = 'INSTITUTION_NAME')
    @JsonProperty(value = 'INSTITUTION_NAME')
    private String institutionName

}
