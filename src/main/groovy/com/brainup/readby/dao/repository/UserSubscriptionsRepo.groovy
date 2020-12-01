package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.UserSubscriptions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserSubscriptionsRepo extends JpaRepository<UserSubscriptions,Long> {

    def UserSubscriptions findBySubscriptionId(long aLong)
}