package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString

import javax.persistence.*
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_stream')
@Getter
@Setter
@ToString
class MasStream implements Serializable{

    @Id
    @Column(name = 'STREAM_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'STREAM_ID')
    private Long streamId

    @Column(name = 'STREAM_CODE')
    @JsonProperty(value = 'STREAM_CODE')
    private String streamCode

    @Column(name = 'STREAM_NAME')
    @JsonProperty(value = 'STREAM_NAME')
    private String streamName

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

    @ManyToOne
    @JoinColumn( name = "COURSE_ID")
    private MasCourses masCourses

    /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "SUBSCRIPTION_ID"), name = "SUBSCRIPTION_ID",insertable = false,updatable = false)
    private UserSubscriptions userSubscriptions*/
}
