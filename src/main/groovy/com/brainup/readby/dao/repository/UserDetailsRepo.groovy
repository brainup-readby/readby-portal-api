package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDetailsRepo extends JpaRepository<UserDetails,Long> {

    def boolean existsByMobileNo(long mobileNo)

    def UserDetails findByMobileNo(long mobileNo)

    def UserDetails findTopByMobileNoOrderByUseridDesc(long mobileNo)
}