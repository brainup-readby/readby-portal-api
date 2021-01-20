package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import org.omg.PortableServer.ServantLocator

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
@Table(name = 'rb_randon_quiz_result')
@Getter
@Setter
class RbRandomQuizResult implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'QUIZ_RESULT_ID')
    @JsonProperty(value = 'QUIZ_RESULT_ID')
    private Long quizResultId

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userId

    @Column(name = 'MOBILE_NO')
    @JsonProperty(value = 'MOBILE_NO')
    private Long mobileNo

    @Column(name = 'TOTAL_NO_QSTN')
    @JsonProperty(value = 'TOTAL_NO_QSTN')
    private Integer totalNoQstn

    @Column(name = 'TOTAL_NO_QSTN_ATMPT')
    @JsonProperty(value = 'TOTAL_NO_QSTN_ATMPT')
    private Integer totalNoQstnAtmpt

    @Column(name = 'TOTAL_NO_CORRCT_ANS')
    @JsonProperty(value = 'TOTAL_NO_CORRCT_ANS')
    private Integer totalNoCorrctAns

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
}
