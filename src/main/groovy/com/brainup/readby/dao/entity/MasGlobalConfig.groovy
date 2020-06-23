package com.brainup.readby.dao.entity

import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

import javax.persistence.*

import com.fasterxml.jackson.annotation.JsonProperty

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_globalconfig')
@Getter
@Setter
class MasGlobalConfig {

    @Id
    @Column(name = 'CONFIG_ID')
    @JsonProperty(value = 'CONFIG_ID')
    private Long configId

    @Column(name = 'CONFIG_NAME')
    @JsonProperty(value = 'CONFIG_NAME')
    private String configName

    @Column(name = 'CONFIG_VALUE')
    @JsonProperty(value = 'CONFIG_VALUE')
    private String configValue

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive
}

