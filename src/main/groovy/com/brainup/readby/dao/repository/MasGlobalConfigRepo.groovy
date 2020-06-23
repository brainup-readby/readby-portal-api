package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasGlobalConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasGlobalConfigRepo extends JpaRepository<MasGlobalConfig,Long>{

    def List<MasGlobalConfig> findByIsActive(String isActive)
}