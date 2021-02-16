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
@Table(name = 'rb_questions')
@Getter
@Setter
class RbQuestionsImage implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'QUESTIONID')
    @JsonProperty(value = 'QUESTIONID')
    private Long questionId

    @Column(name = 'QUESTIONTITLE')
    @JsonProperty(value = 'QUESTIONTITLE')
    private String questionTitle

    @Column(name = 'QUESTIONDESC')
    @JsonProperty(value = 'QUESTIONDESC')
    private String questionDesc

    @Column(name = "QUESTIONNAIR_ID")
    @JsonProperty(value = 'QUESTIONNAIR_ID')
    private Long rbQuestionnaires

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive

    @Column(name = 'ISANSWERED')
    @JsonProperty(value = 'ISANSWERED')
    private String isAnswered

    @Column(name = 'ISMANDATORY')
    @JsonProperty(value = 'ISMANDATORY')
    private String isMandatory

    @Column(name = 'ISRANDOMIZED')
    @JsonProperty(value = 'ISRANDOMIZED')
    private String isRandomized

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

    @Column(name = 'QIMAGEPATH')
    @JsonProperty(value = 'QIMAGEPATH')
    private String qImagePath

}
