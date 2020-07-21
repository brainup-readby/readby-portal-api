package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasCourses
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasCoursesRepo  extends JpaRepository<MasCourses,Long> {

    def MasCourses findByCourseId(long aLong)
}