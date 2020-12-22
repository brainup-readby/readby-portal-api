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
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_topics_status')
@Getter
@Setter
@ToString
class MasTopicStatus implements Serializable{

    @Id
    @Column(name = 'TOPIC_STATUS_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'TOPIC_STATUS_ID')
    private Long topicStatusId

    @Column(name = 'TOPIC_ID')
    @JsonProperty(value = 'TOPIC_ID')
    private Long topicId

    @Column(name = 'SUBJECT_ID')
    @JsonProperty(value = 'SUBJECT_ID')
    private Long subjectId

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userid

    @Column(name = 'TOPIC_SUBSCRIPTION')
    @JsonProperty(value = 'TOPIC_SUBSCRIPTION')
    private String topicSubscription

    @Column(name = 'VIDEO_STATUS')
    @JsonProperty(value = 'VIDEO_STATUS')
    private String videoStatus

    @Column(name = 'TEST_STATUS')
    @JsonProperty(value = 'TEST_STATUS')
    private String testStatus

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

    /*@OneToOne
    @JoinColumn (name="TOPIC_ID",insertable = false,updatable = false)
    //@JsonProperty(value = 'MAS_TOPIC_STATUS')
    private MasTopic masTopic*/
}
