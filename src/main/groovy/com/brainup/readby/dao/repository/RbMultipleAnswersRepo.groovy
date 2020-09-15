package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbMultipleAnswers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbMultipleAnswersRepo extends JpaRepository<RbMultipleAnswers,Long> {

    def RbMultipleAnswers findByQuestionId(long aLong)
}