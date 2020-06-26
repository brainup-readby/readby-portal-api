package com.brainup.readby.service

import com.brainup.readby.dao.entity.MasGlobalConfig
import com.brainup.readby.dao.entity.OtpInfo
import com.brainup.readby.dao.entity.UserDetails
import com.brainup.readby.dao.repository.MasGlobalConfigRepo
import com.brainup.readby.dao.repository.OtpInfoRepo
import com.brainup.readby.dao.repository.UserDetailsRepo
import com.brainup.readby.proxy.ServiceCall
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.sql.Timestamp

@Service
class StudentService {

    @Autowired
    MasGlobalConfigRepo masGlobalConfigRepo

    @Autowired
    ServiceCall serviceCall

    @Autowired
    OtpInfoRepo otpInfoRepo

    @Autowired
    UserDetailsRepo userDetailsRepo

    List<MasGlobalConfig> findByIsActive(String isActive){
        List<MasGlobalConfig> masGlobalConfig = masGlobalConfigRepo.findByIsActive(isActive)
        return masGlobalConfig
    }

    def String sendOTP(Map<String,String> map) {
        String otpValue = 4222//serviceCall.getServiceResult()
        OtpInfo otpInfo = new OtpInfo(
                otp: otpValue,
                mobileNo: map.get("mobileNo").toLong(),
                retryLimit: 3,
                createdBy: "read_by",
                updatedBy: "read_by",
                isUsed: "f"
        )
        otpInfoRepo.save(otpInfo)
        return otpValue
    }

    def boolean verifyOTP(Map<String, String> map) {
        boolean otpFlag = otpInfoRepo.existsByOtpAndMobileNoAndIsUsed(map.get("otp"),map.get("mobileNo").toLong(),'f')
        if(otpFlag){
            OtpInfo otpInfo = otpInfoRepo.findTopByOtpAndMobileNoAndIsUsedOrderByOtpIdDesc(map.get("otp"),map.get("mobileNo").toLong(),'f')
            otpInfo.isUsed = 't'
            otpInfoRepo.save(otpInfo)
        }
        otpFlag
    }

    def String resendOTP(Map<String, String> map) {

        String otpValue = 3222//serviceCall.getServiceResult()
        OtpInfo otpInfo = otpInfoRepo.findTopByMobileNoAndIsUsedOrderByOtpIdDesc(map.get("mobileNo").toLong(),'f')
        if(otpInfo.retryLimit>0) {
            Integer retryLimit = otpInfo.retryLimit
            otpInfo.retryLimit = retryLimit - 1
            otpInfoRepo.save(otpInfo)
            otpValue
        }else{
            return "you have exceeded the limit "
        }

    }

    def UserDetails registerStudent(UserDetails userDetails) {
        userDetailsRepo.save(userDetails)
    }

    def String loginStudent(Map<String, String> stringStringMap) {

    }

    def boolean existsByMobileNo(long mobileNo) {
        userDetailsRepo.existsByMobileNo(mobileNo)
    }

    def UserDetails verifyLoginOTP(Map<String, String> map) {
        userDetailsRepo.findTopByMobileNoOrderByUseridDesc(map.get("mobileNo").toLong())
    }
}
