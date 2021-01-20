package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.*

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'rb_random_quiz')
@Getter
@Setter
class RbRandomQuiz implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'QUIZ_ID')
    @JsonProperty(value = 'QUIZ_ID')
    private Long quizId

    @Column(name = 'SUBJECT_ID')
    @JsonProperty(value = 'SUBJECT_ID')
    private Long subjectId

    @Column(name = 'QUSTN_CODE')
    @JsonProperty(value = 'QUSTN_CODE')
    private String qustnCode

    @Column(name = 'QUSTN_DSC')
    @JsonProperty(value = 'QUSTN_DSC')
    private String qustnDsc

    @Column(name = 'OPTN_1')
    @JsonProperty(value = 'OPTN_1')
    private String optn1

    @Column(name = 'OPTN_2')
    @JsonProperty(value = 'OPTN_2')
    private String optn2

    @Column(name = 'OPTN_3')
    @JsonProperty(value = 'OPTN_3')
    private String optn3

    @Column(name = 'OPTN_4')
    @JsonProperty(value = 'OPTN_4')
    private String optn4

    @Column(name = 'CRRCT_OPTN')
    @JsonProperty(value = 'CRRCT_OPTN')
    private String crrctOptn

}
