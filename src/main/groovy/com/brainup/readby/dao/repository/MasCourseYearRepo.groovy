package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasCourseYear
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasCourseYearRepo extends JpaRepository<MasCourseYear,Long> {

    MasCourseYear findByYearId(long aLong)
}