package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasRoleRepo extends JpaRepository<MasRole,Long> {

    def List<MasRole> findByIsActive(String s)
}