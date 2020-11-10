package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbStudentStudyState
import com.brainup.readby.dao.entity.ReadbyFeedback
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReadbyFeedbackRepo extends JpaRepository<ReadbyFeedback,Long> {

}