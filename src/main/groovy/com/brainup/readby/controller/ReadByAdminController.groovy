package com.brainup.readby.controller

import com.brainup.readby.config.ResponseObject
import com.brainup.readby.dao.entity.MasBoardDP
import com.brainup.readby.dao.entity.MasCourses
import com.brainup.readby.dao.entity.MasCoursesType
import com.brainup.readby.dao.entity.MasStream
import com.brainup.readby.dao.entity.MasStreamLkp
import com.brainup.readby.dao.entity.MasYearLkp
import com.brainup.readby.dao.entity.UserTransactionDetails
import com.brainup.readby.exception.BadRequestException
import com.brainup.readby.service.AdminService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(value = "/readBy/admin")
@Slf4j
class ReadByAdminController {

    @Autowired
    AdminService adminService

    @GetMapping(value = "/getDashBoardDetail")
    ResponseEntity getDashBoardDetail(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getDashBoardDetail service for admin."
            Map<String, Long> resMap = adminService.getDashBoardDetail()
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

    @PostMapping(value = "/addCourses",consumes = "multipart/form-data",produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> addCourses(@RequestParam("file") MultipartFile file, @RequestParam("masCourse") String masCourses) {
        try {
            log.info "calling addCourses service for admin."
            MasCourses masCoursesDB = adminService.addCourses(file, masCourses)
           // ResponseObject responseObject = new ResponseObject()
           // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("course added successfully",HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/editCourses",consumes = "multipart/form-data",produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> editCourses(@RequestParam("file") MultipartFile file, @RequestParam("masCourse") String masCourses) {
        try {
            log.info "calling editCourses service for admin."
            MasCourses masCoursesDB = adminService.editCourses(file, masCourses)
           // ResponseObject responseObject = new ResponseObject()
           // responseObject.data = masCoursesDB
          //  ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("course edited successfully",HttpStatus.CREATED)


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

    @GetMapping(value = "/deleteCourse")
    ResponseEntity deleteCourse(@RequestParam Map<String, String> map) {
        try {
            log.info "calling deleteCourse service for admin where courseId: ${map.get("courseId")}."
            String msg = adminService.deleteCourse(map.get("courseId").toLong())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = msg
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
            List<MasBoardDP> masBoardList = adminService.getBoardList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masBoardList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getMasStreamList")
    ResponseEntity getMasStreamList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getMasStreamList service for admin."
            List<MasStreamLkp> masStreamList = adminService.getMasStreamList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masStreamList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getMasYearList")
    ResponseEntity getMasYearList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getMasStreamList service for admin."
            List<MasYearLkp> masYearLkpList = adminService.getMasYearList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masYearLkpList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getUserTransactionList")
    ResponseEntity getUserTransactionList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getMasStreamList service for admin."
            List<UserTransactionDetails> userTransactionDetails = adminService.getUserTransactionList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = userTransactionDetails
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }
}
