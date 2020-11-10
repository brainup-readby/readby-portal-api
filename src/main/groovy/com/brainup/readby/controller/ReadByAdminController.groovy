package com.brainup.readby.controller

import com.brainup.readby.config.ResponseObject
import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasCourses
import com.brainup.readby.dao.entity.MasCoursesType
import com.brainup.readby.dao.entity.UserSubscriptions
import com.brainup.readby.dao.repository.MasCoursesRepo
import com.brainup.readby.exception.BadRequestException
import com.brainup.readby.service.AdminService
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
@RequestMapping(value = "/readBy/admin")
@Slf4j
class ReadByAdminController {

    @Autowired
    AdminService adminService

    @GetMapping(value = "/getDashBoardDetail")
    ResponseEntity getDashBoardDetail(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getDashBoardDetail service for admin."
            Map<String,Long> resMap = adminService.getDashBoardDetail()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = resMap
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getCourseList")
    ResponseEntity getCourseList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getCourseList service for admin."
            List<MasCourses> courseList = adminService.getCourseList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = courseList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/addCourses")
    ResponseEntity addCourses(@RequestBody MasCourses masCourses) {
        try {
            log.info "calling addCourses service for admin."
            MasCourses masCoursesDB = adminService.addCourses(masCourses)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masCoursesDB
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }

    }

    @GetMapping(value = "/getCourseType")
    ResponseEntity getCourseType(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getCourseType service for admin."
            List<MasCoursesType> masCoursesTypeList = adminService.getCourseType()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masCoursesTypeList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getBoardList")
    ResponseEntity getBoardList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getBoardList service for admin."
            List<MasBoard> masBoardList = adminService.getBoardList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masBoardList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }
}
