package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasTopic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasTopicRepo extends JpaRepository<MasTopic,Long> {

    def MasTopic findByTopicId(long topicId)
}