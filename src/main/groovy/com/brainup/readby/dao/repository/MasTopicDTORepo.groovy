package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasSubjects
import com.brainup.readby.dto.MasTopicDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasTopicDTORepo extends JpaRepository<MasTopicDTO,Long> {

    def MasTopicDTO findByTopicId(long l)

    List<MasTopicDTO> findByChapterId(long aLong)
}