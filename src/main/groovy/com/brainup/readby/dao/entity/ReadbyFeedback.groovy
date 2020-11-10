package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString

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
@Table(name = 'readby_feedback')
@Getter
@Setter
@ToString
class ReadbyFeedback implements Serializable{

    @Id
    @Column(name = 'FEEDBACK_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'FEEDBACK_ID')
    private Long feedbackId

    @Column(name = 'MOBILE_NO')
    @JsonProperty(value = 'MOBILE_NO')
    private Long mobileNo

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userId

    @Column(name = 'LEARNING')
    @JsonProperty(value = 'LEARNING')
    private Integer learning

    @Column(name = 'USABILITY')
    @JsonProperty(value = 'USABILITY')
    private Integer usability

    @Column(name = 'CONTENT')
    @JsonProperty(value = 'CONTENT')
    private Integer content

    @Column(name = 'COMMENTS')
    @JsonProperty(value = 'COMMENTS')
    private String comments

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
