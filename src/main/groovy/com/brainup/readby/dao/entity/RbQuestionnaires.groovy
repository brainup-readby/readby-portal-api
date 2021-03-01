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
@Table(name = 'rb_questionnaires')
@Getter
@Setter
class RbQuestionnaires implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = 'QID')
    @JsonProperty(value = 'QID')
    private Long qId

    @Column(name = 'TOPIC_ID')
    @JsonProperty(value = 'TOPIC_ID')
    private Long topicId

    @Column(name = 'QNAME')
    @JsonProperty(value = 'QNAME')
    private String qName

    @Column(name = 'DETAIL_DESC')
    @JsonProperty(value = 'DETAIL_DESC')
    private String detailDesc

    @Column(name = 'QCOUNT')
    @JsonProperty(value = 'QCOUNT')
    private Integer qCount

    @Column(name = 'IMAGEPATH')
    @JsonProperty(value = 'IMAGEPATH')
    private String imagePath

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

    @OneToMany(mappedBy = "rbQuestionnaires", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(value = 'RB_QUESTIONS')
    private List<RbQuestions> rbQuestions

    @Column(name = 'RANDOMQCOUNT')
    @JsonProperty(value = 'RANDOMQCOUNT')
    private Integer randomQCount

    @Column(name = 'MAX_MARKS')
    @JsonProperty(value = 'MAX_MARKS')
    private Integer maxMarks

    @Column(name = 'PASSING_MARKS')
    @JsonProperty(value = 'PASSING_MARKS')
    private Integer passingMarks

    @Transient
    @JsonProperty(value = 'PERQUESTMARKS')
    private Integer perQuestMarks

    @Transient
    @JsonProperty(value = 'SUBJECT_ID')
    private Long subjectId

}
