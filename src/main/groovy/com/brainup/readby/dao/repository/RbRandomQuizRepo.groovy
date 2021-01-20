package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.RbRandomQuiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbRandomQuizRepo extends JpaRepository<RbRandomQuiz,Long> {

    def List<RbRandomQuiz> findBySubjectId(long aLong)
}