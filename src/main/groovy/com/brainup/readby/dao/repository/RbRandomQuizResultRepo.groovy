package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbRandomQuizResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbRandomQuizResultRepo extends JpaRepository<RbRandomQuizResult,Long> {

}