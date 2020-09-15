package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbStudentAnswers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbStudentAnswersRepo extends JpaRepository<RbStudentAnswers,Long> {

}