package com.brainup.readby.dao.entity

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
@Table(name = 'rb_student_answers')
@Getter
@Setter
class RbStudentAnswers implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'ROW_ID')
    @JsonProperty(value = 'ROW_ID')
    private Long rowId

    @Column(name = 'QUESTION_ID')
    @JsonProperty(value = 'QUESTION_ID')
    private Long questionId

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userId

    @Column(name = 'GIVEN_ANSWER')
    @JsonProperty(value = 'GIVEN_ANSWER')
    private Long givenAnswer

    @Column(name = 'ANSWER_AT')
    @JsonProperty(value = 'ANSWER_AT')
    private Timestamp answerAt = new  Timestamp(new Date().getTime())

    @Column(name = 'ANSWERED_BY')
    @JsonProperty(value = 'ANSWERED_BY')
    private Long answeredBy

    @Transient
    @JsonProperty(value = 'TOPIC_ID')
    private Long topicId
}
