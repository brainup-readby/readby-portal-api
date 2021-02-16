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
@Table(name = 'rb_multiple_answers')
@Getter
@Setter
class RbMultipleAnswers implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'ANSWER_ID')
    @JsonProperty(value = 'ANSWER_ID')
    private Long answerId

    @Column(name = 'QUESTION_ID')
    @JsonProperty(value = 'QUESTION_ID')
    private Long questionId

    @Column(name = 'CORRECT_OPTION')
    @JsonProperty(value = 'CORRECT_OPTION')
    private String correctOption

    @Column(name = 'marks')
    @JsonProperty(value = 'Marks')
    private Integer marks

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
