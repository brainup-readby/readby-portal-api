package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbQuestionnaires
import com.brainup.readby.dao.entity.RbQuestions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbQuestionsRepo extends JpaRepository<RbQuestions,Long> {

    def RbQuestions findByQuestionId(long questionId)

    def RbQuestions findTopByQuestionIdOrderByQuestionIdDesc(long questionId)
}