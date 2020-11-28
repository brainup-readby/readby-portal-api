package com.brainup.readby.dao.entity

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

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_stream_lkp')
@Getter
@Setter
@ToString
class MasStreamLkp implements Serializable{

    @Id
    @Column(name = 'STREAM_LKP_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'STREAM_ID')
    private Long streamId

    @Column(name = 'STREAM_LKP_CODE')
    @JsonProperty(value = 'STREAM_CODE')
    private String streamCode

    @Column(name = 'STREAM_LKP_NAME')
    @JsonProperty(value = 'STREAM_NAME')
    private String streamName

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive
}
