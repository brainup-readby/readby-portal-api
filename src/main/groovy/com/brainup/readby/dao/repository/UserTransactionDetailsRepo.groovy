package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.UserTransactionDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserTransactionDetailsRepo extends JpaRepository<UserTransactionDetails,Long> {

}