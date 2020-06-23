package com.brainup.readby.dao.repository

import com.brainup.readby.dao.entity.OtpInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OtpInfoRepo extends JpaRepository<OtpInfo,Long> {

    def boolean existsByOtpAndMobileNoAndIsUsed(String otp, long mobileNo, String flag)

    def OtpInfo findTopByMobileNoAndIsUsedOrderByOtpIdDesc(long mobileNo, String isUsed)

    def OtpInfo findTopByOtpAndMobileNoAndIsUsedOrderByOtpIdDesc(String otp, long mobileNo, String isUsed)
}