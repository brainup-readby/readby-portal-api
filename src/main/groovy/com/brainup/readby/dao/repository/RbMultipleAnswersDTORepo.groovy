package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbMultipleAnswers
import com.brainup.readby.dao.entity.RbMultipleAnswersDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbMultipleAnswersDTORepo extends JpaRepository<RbMultipleAnswersDTO,Long> {

}