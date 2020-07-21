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
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import java.sql.Timestamp

@Canonical
@EqualsAndHashCode
@Entity
@Table(name = 'mas_boards')
@Getter
@Setter
@ToString
class MasBoard implements Serializable{

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

    @OneToMany(mappedBy = "masBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(value = 'MAS_COURSE')
    private List<MasCourses> masCourse
}
