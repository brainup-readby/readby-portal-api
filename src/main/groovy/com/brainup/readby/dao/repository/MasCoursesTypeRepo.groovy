package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasCoursesType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasCoursesTypeRepo extends JpaRepository<MasCoursesType,Long> {

}