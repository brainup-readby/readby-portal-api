package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.OtpInfo
import com.brainup.readby.dao.entity.RbStudentStudyState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbStudentStudyStateRepo extends JpaRepository<RbStudentStudyState,Long> {

    def RbStudentStudyState findByUserId(long userid)
}