package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.UserLoginDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserLoginDetailsRepo extends JpaRepository<UserLoginDetails,Long> {

    UserLoginDetails findByMobileNo(long aLong)
}