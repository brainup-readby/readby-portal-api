package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasTopicStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasTopicStatusRepo extends JpaRepository<MasTopicStatus,Long> {

    def boolean existsByTopicIdAndUserid(long aLong1, long aLong2)

    def MasTopicStatus findByTopicIdAndUserid(long topicId,long userId)

    def MasTopicStatus findTopByTopicIdAndUseridOrderByTopicStatusIdDesc(long aLong1, long aLong2)

    def List<MasTopicStatus> findByUserid(long aLong)
}