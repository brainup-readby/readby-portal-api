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
@Table(name = 'rb_student_report')
@Getter
@Setter
class RbStudentReport implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'REPORT_ID')
    @JsonProperty(value = 'REPORT_ID')
    private Long reportId

    @Column(name = 'TOPIC_ID')
    @JsonProperty(value = 'TOPIC_ID')
    private Long topicId

    @Column(name = 'USER_ID')
    @JsonProperty(value = 'USER_ID')
    private Long userId

    @Column(name = 'TOTAL_MARKS_OBTAINED')
    @JsonProperty(value = 'TOTAL_MARKS_OBTAINED')
    private Long totalMarksObtained

    @Column(name = 'MAXIMUM_MARKS')
    @JsonProperty(value = 'MAXIMUM_MARKS')
    private Long maximumMarks

    @Column(name = 'TOTAL_PERCENTAGE')
    @JsonProperty(value = 'TOTAL_PERCENTAGE')
    private Integer totalPercentage

    @Column(name = 'OVERALL_RESULT')
    @JsonProperty(value = 'OVERALL_RESULT')
    private String overallResult

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
