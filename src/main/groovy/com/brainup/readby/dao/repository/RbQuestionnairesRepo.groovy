package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbQuestionnaires
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbQuestionnairesRepo extends JpaRepository<RbQuestionnaires,Long> {

    def RbQuestionnaires findByTopicId(Long topicId)
}