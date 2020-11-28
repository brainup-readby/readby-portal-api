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
@Table(name = 'mas_year_lkp')
@Getter
@Setter
@ToString
class MasYearLkp implements Serializable{

    @Id
    @Column(name = 'YEAR_LKP_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'YEAR_ID')
    private Long yearId

    @Column(name = 'YEAR')
    @JsonProperty(value = 'YEAR')
    private Long year

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive
}
