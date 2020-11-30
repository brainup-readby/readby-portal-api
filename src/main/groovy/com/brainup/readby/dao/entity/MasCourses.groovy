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
@Table(name = 'mas_courses')
@Getter
@Setter
class MasCourses implements Serializable{

    @Id
    @Column(name = 'COURSE_ID')
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(value = 'COURSE_ID')
    private Long courseId

    @Column(name = 'BOARD_ID')
    @JsonProperty(value = 'BOARD_ID')
    private Long boardId

    @Column(name = 'COURSE_TYPE_ID')
    @JsonProperty(value = 'COURSE_TYPE_ID')
    private Long courseTypeId

    @Column(name = 'COURSE_CODE')
    @JsonProperty(value = 'COURSE_CODE')
    private String courseCode

    @Column(name = 'COURSE_NAME')
    @JsonProperty(value = 'COURSE_NAME')
    private String courseName

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

    /*@OneToMany(mappedBy = "masCourses", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(value = 'MAS_SUBJECTS')
    private List<MasSubjects> masSubjects*/

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "COURSE_TYPE_ID"), name = "COURSE_TYPE_ID",insertable = false,updatable = false)
    @JsonProperty(value = 'MAS_COURSE_TYPE')
    private MasCoursesType masCoursesType

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COURSE_TYPE_ID", referencedColumnName = "COURSE_TYPE_ID")
    @JsonProperty(value = 'MAS_COURSE_TYPE')
    private MasCoursesType masCoursesType
*/
    @OneToMany(mappedBy = "masCourses" ,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonProperty(value = 'MAS_STREAM')
    private List<MasStream> masStream

    @OneToMany(mappedBy = "masCourses", fetch = FetchType.LAZY)
    @JsonProperty(value = 'MAS_COURSE_YEAR')
    private List<MasCourseYear> masCourseYear

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "BOARD_ID"), name = "BOARD_ID",insertable = false,updatable = false)
    private MasBoard masBoard

}
