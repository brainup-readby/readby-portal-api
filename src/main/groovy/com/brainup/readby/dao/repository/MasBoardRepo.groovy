package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasSubjects
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import javax.transaction.Transactional

@Repository
interface MasBoardRepo extends JpaRepository<MasBoard,Long>{

    def List<MasBoard> findByIsActiveIgnoreCase(String isActive)

    MasBoard findByBoardId(long aLong)

    @Transactional
    def void deleteByBoardId(long l)

    MasBoard findByBoardIdAndIsActive(long aLong, String s)
}