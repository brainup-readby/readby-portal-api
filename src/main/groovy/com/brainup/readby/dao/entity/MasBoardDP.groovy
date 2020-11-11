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
@Table(name = 'mas_boards')
@Getter
@Setter
@ToString
class MasBoardDP {

    @Id
    @Column(name = 'BOARD_ID')
    @JsonProperty(value = 'BOARD_ID')
    private Long boardId

    @Column(name = 'BOARD_NAME')
    @JsonProperty(value = 'BOARD_NAME')
    private String boardName

    @Column(name = 'BOARD_CODE')
    @JsonProperty(value = 'BOARD_CODE')
    private String boardCode

    @Column(name = 'IS_ACTIVE')
    @JsonProperty(value = 'IS_ACTIVE')
    private String isActive
}
