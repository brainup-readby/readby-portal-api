package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasCourses
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import javax.transaction.Transactional

@Repository
interface MasCoursesRepo  extends JpaRepository<MasCourses,Long> {

    def MasCourses findByCourseId(long aLong)

    @Transactional
    void deleteByCourseId(long l)
    
    def List<MasCourses> findByIsActive(String isActive)



    def List<MasCourses> findByIsActiveOrderByCourseIdDesc(String isActive)

    def MasCourses findByCourseIdAndIsActive(long aLong, String isActive)
}