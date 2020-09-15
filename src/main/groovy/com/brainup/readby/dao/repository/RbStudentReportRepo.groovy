package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbStudentReport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbStudentReportRepo extends JpaRepository<RbStudentReport,Long> {

}