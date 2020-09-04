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

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'rb_student_study_state')
@Getter
@Setter
class RbStudentStudyState implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'STATE_ID')
    @JsonProperty(value = 'STATE_ID')
    private Long stateId

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userId

    @Column(name = 'TOPIC_ID')
    @JsonProperty(value = 'TOPIC_ID')
    private Long topicId

    @Column(name = 'VIDEO_LEFT_TIME')
    @JsonProperty(value = 'VIDEO_LEFT_TIME')
    private Long videoLeftTime
}
