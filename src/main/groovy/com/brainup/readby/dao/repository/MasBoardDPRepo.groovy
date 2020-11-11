package com.brainup.readby.dao.repository


import com.brainup.readby.dao.entity.MasBoardDP
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasBoardDPRepo extends JpaRepository<MasBoardDP,Long> {

}