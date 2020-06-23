package com.brainup.readby.controller

import com.brainup.readby.dao.entity.MasGlobalConfig
import com.brainup.readby.dao.entity.UserDetails
import com.brainup.readby.service.StudentService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping(value = "/readBy")
@Slf4j
class ReadByRestController {

    @Autowired
    StudentService studentService

    @GetMapping(value = "/getMasGlobalConfig")
    ResponseEntity getMasGlobalConfig() {
        List<MasGlobalConfig> masGlobalConfig = studentService.findByIsActive("t")
        log.info("size of MasGlobalConfig: " + masGlobalConfig.size())
        ResponseEntity.status(HttpStatus.OK).body(masGlobalConfig)
    }

    @GetMapping(value = "/sendOTP")
    ResponseEntity sendOTP(@RequestParam Map<String, String> map) {
        log.info "calling sendOtp service for mobile no. ${map.get("mobileNo")}"
        String otp = studentService.sendOTP(map)
        log.info "Generated OTP: ${otp}"
        ResponseEntity.status(HttpStatus.OK).body(otp)
    }

    @GetMapping(value = "/verifyOTP")
    ResponseEntity verifyOTP(@RequestParam Map<String, String> map) {
        log.info "calling verifyOTP service for mobile no. ${map.get("mobileNo")} and otp ${map.get("otp")}"
        Boolean otpFlag = studentService.verifyOTP(map)
        log.info "Verified OTP: ${otpFlag}"
        ResponseEntity.status(HttpStatus.OK).body(otpFlag)
    }

    @GetMapping(value = "/resendOTP")
    ResponseEntity resendOTP(@RequestParam Map<String, String> map) {
        log.info "calling resendOTP service for mobile no. ${map.get("mobileNo")}"
        String otp = studentService.resendOTP(map)
        log.info "Generated OTP: ${otp}"
        ResponseEntity.status(HttpStatus.OK).body(otp)
    }

    @PostMapping(value = "/registerStudent")
    ResponseEntity registerStudent(@RequestBody UserDetails userDetails) {
        log.info "calling registerStudent service for ${userDetails.firstName}"
        if(studentService.existsByMobileNo(userDetails.mobileNo)){
            log.info "${userDetails.mobileNo} mobile number already registered."
            ResponseEntity.status(HttpStatus.CREATED).body("${userDetails.mobileNo} mobile number already registered.")
        }else{
            UserDetails userDetailsDao = studentService.registerStudent(userDetails)
            log.info "Student registered with Id: ${userDetailsDao.userid}"
            ResponseEntity.status(HttpStatus.CREATED).body(userDetailsDao)
        }
    }

    @GetMapping(value = "/loginStudent")
    ResponseEntity loginStudent(@RequestParam Map<String, String> map) {
        log.info "calling loginStudent service for mobile no. ${map.get("mobileNo")}"
        if(studentService.existsByMobileNo(map.get("mobileNo").toLong())){
            String otp = studentService.sendOTP(map)
            log.info "Generated OTP: ${otp}"
            ResponseEntity.status(HttpStatus.OK).body(otp)
        }else{
            log.info "${map.get("mobileNo")} is not registered."
            ResponseEntity.status(HttpStatus.CREATED).body("${map.get("mobileNo")} is not registered.")
        }
    }
}
