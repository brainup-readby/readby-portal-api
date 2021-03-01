package com.brainup.readby.controller

import com.brainup.readby.config.ResponseObject
import com.brainup.readby.dao.entity.*
import com.brainup.readby.dto.MasChaptersDTO
import com.brainup.readby.dto.MasSubjectsDTO
import com.brainup.readby.dto.MasTopicDTO
import com.brainup.readby.exception.BadRequestException
import com.brainup.readby.service.AdminService
import com.brainup.readby.service.StudentService
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

    @Autowired
    StudentService studentService

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

    @PostMapping(value = "/addCourses", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> addCourses(@RequestParam("file") MultipartFile file, @RequestParam("masCourse") String masCourses) {
        try {
            log.info "calling addCourses service for admin."
            MasCourses masCoursesDB = adminService.addCourses(file, masCourses)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("course added successfully", HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/editCourses", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> editCourses(@RequestParam("file") MultipartFile file, @RequestParam("masCourse") String masCourses) {
        try {
            log.info "calling editCourses service for admin."
            MasCourses masCoursesDB = adminService.editCourses(file, masCourses)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = masCoursesDB
            //  ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("course edited successfully", HttpStatus.CREATED)


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
            List<MasStreamLkp> masStreamList = adminService.getMasStreamList(map.get("courseId").toLong())
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
            List<MasYearLkp> masYearLkpList = adminService.getMasYearList(map.get("courseId").toLong())
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
            log.info "calling getUserTransactionList service for admin."
            List<UserTransactionDetails> userTransactionDetails = adminService.getUserTransactionList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = userTransactionDetails
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/saveBoard")
    ResponseEntity saveBoard(@RequestBody MasBoard masBoard) {
        try {
            log.info "calling saveBoard service for admin."
            MasBoard masBoard1 = adminService.saveBoard(masBoard)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masBoard1
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/editBoard")
    ResponseEntity editBoard(@RequestBody MasBoard masBoard) {
        try {
            log.info "calling editBoard service for admin."
            MasBoard masBoard1 = adminService.editBoard(masBoard)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masBoard1
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @DeleteMapping(value = "/deleteBoard")
    ResponseEntity deleteBoard(@RequestParam Map<String, String> map) {
        try {
            log.info "calling deleteBoard service for admin."
            String msg = adminService.deleteBoard(map.get("boardId").toLong())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = msg
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getUserList")
    ResponseEntity getUserList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getUserList service for admin."
            List<UserDetails> userDetails = adminService.getUserList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = userDetails
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getSubjectList")
    ResponseEntity getSubjectList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getSubjectList service for admin."
            List<MasSubjectsDTO> masSubjectsList = adminService.getSubjectList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masSubjectsList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/addSubjects", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> addSubjects(@RequestParam("file") MultipartFile file, @RequestParam("masSubjects") String masSubjects) {
        try {
            log.info "calling addSubjects service for admin."
            MasSubjects masSubjects1 = adminService.addSubjects(file, masSubjects)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("Subjects added successfully", HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/editSubjects", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> editSubjects(@RequestParam("file") MultipartFile file, @RequestParam("masSubjects") String masSubjects) {
        try {
            log.info "calling editSubjects service for admin."
            MasSubjects masSubjects1 = adminService.editSubjects(file, masSubjects)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("Subjects edited successfully", HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @DeleteMapping(value = "/deleteSubject")
    ResponseEntity deleteSubject(@RequestParam Map<String, String> map) {
        try {
            log.info "calling deleteSubject service for admin where subjectId: ${map.get("subjectId")}."
            String msg = adminService.deleteSubject(map.get("subjectId").toLong())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = msg
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getChapterList")
    ResponseEntity getChapterList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getChapterList service for admin."
            List<MasChaptersDTO> masChaptersList = adminService.getChapterList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masChaptersList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }


    @PostMapping(value = "/addChapters", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> addChapters(@RequestParam("file") MultipartFile file, @RequestParam("masChapters") String masChapters) {
        try {
            log.info "calling addChapters service for admin."
            MasChaptersDTO masChapters1 = adminService.addChapters(file, masChapters)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("Chapters added successfully", HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/editChapters", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> editChapters(@RequestParam("file") MultipartFile file, @RequestParam("masChapters") String masChapters) {
        try {
            log.info "calling editChapters service for admin."
            MasChaptersDTO masChapters1 = adminService.editChapters(file, masChapters)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("Chapters edited successfully", HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @DeleteMapping(value = "/deleteChapter")
    ResponseEntity deleteChapter(@RequestParam Map<String, String> map) {
        try {
            log.info "calling deleteChapter service for admin where chapterId: ${map.get("chapterId")}."
            String msg = adminService.deleteChapter(map.get("chapterId").toLong())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = msg
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getTopicList")
    ResponseEntity getTopicList(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getTopicList service for admin."
            List<MasTopicDTO> masTopicList = adminService.getTopicList()
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masTopicList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }


    @PostMapping(value = "/addTopics", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> addTopics(@RequestParam("file") MultipartFile file, @RequestParam("masTopics") String masTopics) {
        try {
            log.info "calling addTopics service for admin."
            MasTopicDTO masTopic = adminService.addTopics(file, masTopics)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("Topic added successfully", HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/editTopics", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> editTopics(@RequestParam("file") MultipartFile file, @RequestParam("masTopics") String masTopics) {
        try {
            log.info "calling editTopics service for admin."
            MasTopicDTO masTopic = adminService.editTopics(file, masTopics)
            // ResponseObject responseObject = new ResponseObject()
            // responseObject.data = "Uploaded"
            //ResponseEntity.status(HttpStatus.OK).body(responseObject)
            return new ResponseEntity<Object>("Topic edited successfully", HttpStatus.CREATED)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @DeleteMapping(value = "/deleteTopic")
    ResponseEntity deleteTopic(@RequestParam Map<String, String> map) {
        try {
            log.info "calling deleteTopic service for admin where topicId: ${map.get("topicId")}."
            String msg = adminService.deleteTopic(map.get("topicId").toLong())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = msg
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getChaptersBySubject")
    ResponseEntity getChaptersBySubject(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getTopicList service for admin."
            List<MasChaptersDTO> masChaptersDTOList = adminService.getChaptersBySubject(map)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masChaptersDTOList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getTopicsByChapter")
    ResponseEntity getTopicsByChapter(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getTopicList service for admin."
            List<MasTopicDTO> masTopicDTOList = adminService.getTopicsByChapter(map)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masTopicDTOList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getSubjectByStreamOrYear")
    ResponseEntity getSubjectByStreamOrYear(@RequestParam Map<String, String> map) {
        try {
            log.info "calling getTopicList service for admin."
            List<MasSubjectsDTO> masTopicDTOList = adminService.getSubjectByStreamOrYear(map)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masTopicDTOList
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/addStream")
    ResponseEntity addStream(@RequestBody MasStreamLkp masStreamLkp) {
        try {

            MasStreamLkp masStreamLkp1 = adminService.addStream(masStreamLkp)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masStreamLkp1
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception ex) {
            log.error " ${ex.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/addYear")
    ResponseEntity addYear(@RequestBody MasYearLkp masYearLkp) {
        try {

            MasYearLkp masYearLkp1 = adminService.addYear(masYearLkp)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = masYearLkp1
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception ex) {
            log.error " ${ex.message}"
            throw new BadRequestException(e.message)
        }
    }

    @GetMapping(value = "/getLogoutDetail")
    ResponseEntity getLogoutDetail(@RequestParam Map<String, String> map) {
        try {
            UserLoginDetails userLoginDetails = studentService.getLogoutDetail(map)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = userLoginDetails
            ResponseEntity.status(HttpStatus.OK).body(responseObject)
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/saveQuestionAnswer")
    ResponseEntity saveQuestionAnswer(@RequestBody RbQuestionnaires rbQuestionnaires) {
        try {

            String rbQuestionnaires1 = adminService.addRbQuestionnaires(rbQuestionnaires)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = rbQuestionnaires1
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception ex) {
            log.error " ${ex.message}"
            throw new BadRequestException(ex.message)
        }
    }

    @PostMapping(value = "/uploadQuestImage", consumes = "multipart/form-data", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<Object> uploadQuestImage(@RequestParam("file") MultipartFile file, @RequestParam("rbQuestion") String rbQuestion) {
        try {
            log.info "calling addTopics service for admin."
            String rbQuestionnaires1 = adminService.uploadQuestImage(file, rbQuestion)
            return new ResponseEntity<Object>("Image added successfully", HttpStatus.CREATED)

        } catch (Exception ex) {
            log.error " ${ex.message}"
            throw new BadRequestException(ex.message)
        }
    }


    @GetMapping(value = "/getQuestionList")
    ResponseEntity getQuestionList(@RequestParam Map<String, String> map) {
        try {
            List<RbQuestionnaires> rbQuestionnaires = adminService.getQuestionList(map)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = rbQuestionnaires
            ResponseEntity.status(HttpStatus.OK).body(responseObject)
        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @PostMapping(value = "/editQuestionAnswer")
    ResponseEntity editQuestionAnswer(@RequestBody RbQuestionnaires rbQuestionnaires) {
        try {

            String rbQuestionnaires1 = adminService.editQuestionAnswer(rbQuestionnaires)
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = rbQuestionnaires1
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception ex) {
            log.error " ${ex.message}"
            throw new BadRequestException(ex.message)
        }
    }

    @DeleteMapping(value = "/deleteQuestionair")
    ResponseEntity deleteQuestionair(@RequestParam Map<String, String> map) {
        try {
            log.info "calling deleteQuestionair service for admin."
            String msg = adminService.deleteQuestionair(map.get("qId").toLong())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = msg
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }

    @DeleteMapping(value = "/deleteQuestion")
    ResponseEntity deleteQuestion(@RequestParam Map<String, String> map) {
        try {
            log.info "calling deleteQuestion service for admin."
            String msg = adminService.deleteQuestion(map.get("questionId").toLong())
            ResponseObject responseObject = new ResponseObject()
            responseObject.data = msg
            ResponseEntity.status(HttpStatus.OK).body(responseObject)

        } catch (Exception e) {
            log.error " ${e.message}"
            throw new BadRequestException(e.message)
        }
    }
}


