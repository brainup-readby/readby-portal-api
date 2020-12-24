package com.brainup.readby.dao.repository

import com.brainup.readby.dto.MasChaptersDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasChaptersDTORepo extends JpaRepository<MasChaptersDTO,Long> {

    def MasChaptersDTO findByChapterId(long l)
}