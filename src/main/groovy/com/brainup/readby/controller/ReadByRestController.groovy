package com.brainup.readby.controller

import com.brainup.readby.config.ResponseObject
import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasGlobalConfig
import com.brainup.readby.dao.entity.UserDetails
import com.brainup.readby.dao.entity.UserSubscriptions
import com.brainup.readby.exception.BadRequestException
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
        try {
            List<MasGlobalConfig> masGlobalConfig = studentService.findByIsActive("t")
            log.info("size of MasGlobalConfig: " + masGlobalConfig.size())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masGlobalConfig
            ResponseEntity.status(HttpStatus.OK).body(responseObject)
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/sendOTP")
    ResponseEntity sendOTP(@RequestParam Map<String, String> map) {
        try {
            if (map.get("mobileNo").length() == 10) {
                log.info "calling sendOtp service for mobile no. ${map.get("mobileNo")}"
                String otp = studentService.sendOTP(map)
                log.info "Generated OTP: ${otp}"
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = otp
                ResponseEntity.status(HttpStatus.OK).body(responseObject)
            } else {
                ResponseEntity.status(HttpStatus.OK).body("Please enter 10 digit mobile number")
            }
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/verifyOTP")
    ResponseEntity verifyOTP(@RequestParam Map<String, String> map) {
        try {
            log.info "calling verifyOTP service for mobile no. ${map.get("mobileNo")} and otp ${map.get("otp")}"
            Boolean otpFlag = studentService.verifyOTP(map)
            log.info "Verified OTP: ${otpFlag}"
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = otpFlag
            ResponseEntity.status(HttpStatus.OK).body(responseObject)
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/resendOTP")
    ResponseEntity resendOTP(@RequestParam Map<String, String> map) {
        try {
            if (map.get("mobileNo").length() == 10) {
                log.info "calling resendOTP service for mobile no. ${map.get("mobileNo")}"
                String otp = studentService.resendOTP(map)
                log.info "Generated OTP: ${otp}"
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = otp
                ResponseEntity.status(HttpStatus.OK).body(responseObject)
            } else {
                ResponseEntity.status(HttpStatus.OK).body("Please enter 10 digit mobile number")
            }
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/registerStudentDetail")
    ResponseEntity registerStudent(@RequestBody UserDetails userDetails) {
        try {
            log.info "calling registerStudent service for ${userDetails.firstName}"
            if(userDetails.mobileNo.toString().length() != 10){
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = "${userDetails.mobileNo} is less or greater than 10 digit"
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseObject)
            }
            else if (studentService.existsByMobileNo(userDetails.mobileNo)) {
                log.info "${userDetails.mobileNo} mobile number already registered."
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = "${userDetails.mobileNo} mobile number already registered."
                ResponseEntity.status(HttpStatus.CREATED).body(responseObject)
            } else {
                UserDetails userDetailsDao = studentService.registerStudent(userDetails)
                log.info "Student registered with Id: ${userDetailsDao.userid}"
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = userDetailsDao
                ResponseEntity.status(HttpStatus.CREATED).body(responseObject)
            }
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/loginStudent")
    ResponseEntity loginStudent(@RequestParam Map<String, String> map) {
        try {
            log.info "calling loginStudent service for mobile no. ${map.get("mobileNo")}"
            if (map.get("mobileNo").length() == 10) {
                if (studentService.existsByMobileNo(map.get("mobileNo").toLong())) {
                    String otp = studentService.sendOTP(map)
                    log.info "Generated OTP: ${otp}"
                    ResponseObject responseObject = new ResponseObject()
                    responseObject.data = otp
                    ResponseEntity.status(HttpStatus.OK).body(responseObject)
                } else {
                    log.info "${map.get("mobileNo")} is not registered."
                    ResponseObject responseObject = new ResponseObject()
                    responseObject.data = "${map.get("mobileNo")} is not registered."
                    ResponseEntity.status(HttpStatus.CREATED).body(responseObject)
                }
            } else {
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = "Please enter 10 digit mobile number"
                ResponseEntity.status(HttpStatus.OK).body(responseObject)
            }
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/verifyLoginOTP")
    ResponseEntity verifyLoginOTP(@RequestParam Map<String, String> map) {
        try {
            log.info "calling verifyLoginOTP service for mobile no. ${map.get("mobileNo")} and otp ${map.get("otp")}"
            Boolean otpFlag = studentService.verifyOTP(map)
            log.info "Verified OTP: ${otpFlag}"
            if (otpFlag) {
                UserDetails userDetails = studentService.verifyLoginOTP(map)
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = userDetails
                ResponseEntity.status(HttpStatus.OK).body(responseObject)
            } else {
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = otpFlag
                ResponseEntity.status(HttpStatus.OK).body(responseObject)
            }
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }


    @GetMapping(value = "/getBoardDetail")
    ResponseEntity getBoardDetail(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getBoardDetail service."
            List<MasBoard> masBoard = studentService.getBoardDetail()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masBoard

            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/registerStudentSubscription")
    ResponseEntity registerStudentSubscription(@RequestBody UserSubscriptions userSubscriptions) {
        try {
            log.info "calling registerStudentSubscription service for ${userSubscriptions.mobileNo}"
            if(userSubscriptions.mobileNo.toString().length() != 10){
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = "${userSubscriptions.mobileNo} is less or greater than 10 digit"
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseObject)
            }
            else if (studentService.existsByMobileNo(userSubscriptions.mobileNo)) {
                UserDetails userDetailsDao = studentService.findByMobileNo(userSubscriptions.mobileNo)
                userSubscriptions.userid = userDetailsDao.userid
                UserSubscriptions usdao = studentService.registerStudentSubscription(userSubscriptions)
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = usdao
                ResponseEntity.status(HttpStatus.CREATED).body(responseObject)
            } else {
                log.info "${userSubscriptions.mobileNo} Mobile Number is not registered."
                ResponseObject responseObject = new ResponseObject()
                responseObject.data = "${userSubscriptions.mobileNo} Mobile Number is not registered."
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject)
            }
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }
}