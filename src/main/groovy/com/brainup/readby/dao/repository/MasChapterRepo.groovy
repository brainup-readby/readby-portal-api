package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasChapters
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasChapterRepo extends JpaRepository<MasChapters,Long> {

}