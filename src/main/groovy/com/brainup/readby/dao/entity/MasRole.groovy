package com.brainup.readby.dao.entity

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter
import lombok.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_role')
@Getter
@Setter
@ToString
class MasRole implements Serializable{

    @Id
    @Column(name = 'ROLE_ID')
    @JsonProperty(value = 'ROLE_ID')
    private Long roleId

    @Column(name = 'ROLE_CODE')
    @JsonProperty(value = 'ROLE_CODE')
    private String roleCode

    @Column(name = 'ROLE_NAME')
    @JsonProperty(value = 'ROLE_NAME')
    private String roleName

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive
}
