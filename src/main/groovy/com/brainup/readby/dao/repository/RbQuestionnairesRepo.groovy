package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbQuestionnaires
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbQuestionnairesRepo extends JpaRepository<RbQuestionnaires,Long> {

    def RbQuestionnaires findByTopicId(Long topicId)

    def List<RbQuestionnaires> findByIsActive(String isActive)

    //def RbQuestionnaires findByQId(long qId)

    def RbQuestionnaires findByqId(long l)

    //def RbQuestionnaires findTopByTopicIdOrderByqIdDesc(long aLong)

    def RbQuestionnaires findFirstByTopicIdOrderByCreatedAtDesc(long aLong)

    def RbQuestionnaires findTopByTopicIdOrderByCreatedAtDesc(long aLong)

    def RbQuestionnaires findTopByTopicIdAndIsActiveOrderByCreatedAtDesc(long aLong, String s)
}