package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.RbMultipleAnswers
import com.brainup.readby.dao.entity.RbMultipleOptions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RbMultipleOptionsRepo extends JpaRepository<RbMultipleOptions,Long> {

    RbMultipleOptions findByOptionId(long aLong)
}