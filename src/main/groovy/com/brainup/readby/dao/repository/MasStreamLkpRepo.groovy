package com.brainup.readby.dao.repository


import com.brainup.readby.dao.entity.MasStreamLkp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasStreamLkpRepo extends JpaRepository<MasStreamLkp,Long> {

    def List<MasStreamLkp> findByIsActiveIgnoreCase(String s)

    List<MasStreamLkp> findByIsActiveIgnoreCaseAndCourseId(String s, long aLong)
}