package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.MasStream
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasStreamRepo extends JpaRepository<MasStream,Long> {

    MasStream findByStreamId(long streamId)

    def List<MasStream> findByIsActiveIgnoreCase(String isActive)

}