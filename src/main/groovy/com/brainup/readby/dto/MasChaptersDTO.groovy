package com.brainup.readby.dto

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
@Table(name = 'mas_chapters')
@Getter
@Setter
@ToString
class MasChaptersDTO implements Serializable{

    @Id
    @Column(name = 'CHAPTER_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'CHAPTER_ID')
    private Long chapterId

    @Column(name = 'CHAPTER_NAME')
    @JsonProperty(value = 'CHAPTER_NAME')
    private String chapterName

    @Column(name = 'CHAPTER_CODE')
    @JsonProperty(value = 'CHAPTER_CODE')
    private String chapterCode

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

    @Column(name = 'icon_path')
    @JsonProperty(value = 'icon_path')
    private String iconPath

    @Column(name = 'SUBJECT_ID')
    @JsonProperty(value = 'SUBJECT_ID')
    private Long subjectId
}
