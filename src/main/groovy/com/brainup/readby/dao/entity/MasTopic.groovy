package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_topics')
@Getter
@Setter
@ToString
class MasTopic implements Serializable{

    @Id
    @Column(name = 'TOPIC_ID')
    @JsonProperty(value = 'TOPIC_ID')
    private Long topicId

    @Column(name = 'TOPIC_NAME')
    @JsonProperty(value = 'TOPIC_NAME')
    private String topicName

    @Column(name = 'TOPIC_CODE')
    @JsonProperty(value = 'TOPIC_CODE')
    private String topicCode

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

    @Column(name = 'VIDEO_URL')
    @JsonProperty(value = 'VIDEO_URL')
    private String videoUrl

    @Column(name = 'BOOK_URL')
    @JsonProperty(value = 'BOOK_URL')
    private String booKUrl

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "CHAPTER_ID"), name = "CHAPTER_ID",insertable = false,updatable = false)
    private MasChapters masChapters
}
