package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasSubjects
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasSubjectsRepo extends JpaRepository<MasSubjects,Long>  {

    def List<MasSubjects> findByStreamId(long streamId)

    def MasSubjects findBySubjectId(long l)
}