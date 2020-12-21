package com.brainup.readby.service

import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasBoardDP
import com.brainup.readby.dao.entity.MasCourseYear
import com.brainup.readby.dao.entity.MasCourses
import com.brainup.readby.dao.entity.MasCoursesType
import com.brainup.readby.dao.entity.MasStream
import com.brainup.readby.dao.entity.MasStreamLkp
import com.brainup.readby.dao.entity.MasYearLkp
import com.brainup.readby.dao.entity.UserDetails
import com.brainup.readby.dao.entity.UserTransactionDetails
import com.brainup.readby.dao.repository.MasBoardDPRepo
import com.brainup.readby.dao.repository.MasBoardRepo
import com.brainup.readby.dao.repository.MasChapterRepo
import com.brainup.readby.dao.repository.MasCourseYearRepo
import com.brainup.readby.dao.repository.MasCoursesRepo
import com.brainup.readby.dao.repository.MasCoursesTypeRepo
import com.brainup.readby.dao.repository.MasStreamLkpRepo
import com.brainup.readby.dao.repository.MasStreamRepo
import com.brainup.readby.dao.repository.MasSubjectsRepo
import com.brainup.readby.dao.repository.MasTopicRepo
import com.brainup.readby.dao.repository.MasYearLkpRepo
import com.brainup.readby.dao.repository.UserDetailsRepo
import com.brainup.readby.dao.repository.UserTransactionDetailsRepo
import com.brainup.readby.dto.MasCoursesDTO
import com.brainup.readby.dto.UserDetailsDTO
import com.brainup.readby.util.AmazonClient
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.sql.Timestamp

@Service
@Slf4j
class AdminService {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @Autowired
    UserDetailsRepo userDetailsRepo

    @Autowired
    UserTransactionDetailsRepo userTransactionDetailsRepo

    @Autowired
    MasCoursesRepo masCoursesRepo

    @Autowired
    MasSubjectsRepo masSubjectsRepo

    @Autowired
    MasChapterRepo masChapterRepo

    @Autowired
    MasTopicRepo masTopicRepo

    @Autowired
    MasStreamRepo masStreamRepo

    @Autowired
    MasCourseYearRepo masCourseYearRepo

    @Autowired
    MasCoursesTypeRepo masCoursesTypeRepo

    @Autowired
    MasBoardDPRepo masBoardDPRepo

    @Autowired
    MasBoardRepo masBoardRepo

    @Autowired
    MasStreamLkpRepo masStreamLkpRepo

    @Autowired
    MasYearLkpRepo masYearLkpRepo


    def Map<String, Long> getDashBoardDetail() {
        Map<String, Long> map = new LinkedHashMap<>()
        long userCount = userDetailsRepo.count()
        log.info "Total Users: ${userCount}"
        map.put("Total Users", userCount)

        long courseCount = masCoursesRepo.count()
        log.info "Total Courses: ${courseCount}"
        map.put("Total Courses", courseCount)

        long subjectCount = masSubjectsRepo.count()
        log.info "Total Subjects: ${subjectCount}"
        map.put("Total Subjects", subjectCount)

        long chapterCount = masChapterRepo.count()
        log.info "Total Chapter: ${chapterCount}"
        map.put("Total Chapter", chapterCount)

        long topicCount = masTopicRepo.count()
        log.info "Total Topic: ${topicCount}"
        map.put("Total Topic", topicCount)
        map
    }

    def List<MasCourses> getCourseList() {

        masCoursesRepo.findByIsActiveOrderByCourseIdDesc("t")

    }

    def MasCourses addCourses(MultipartFile file,String masCourse) {
        String fileurl = this.amazonClient.uploadFile(file)
        log.info "fileurl: ${fileurl}"
        MasCourses masCourses = new ObjectMapper().readValue(masCourse, MasCourses.class)
        masCourses.iconPath = fileurl
        return saveMasCourses(masCourses)

    }

    def List<MasCoursesType> getCourseType() {
        masCoursesTypeRepo.findAll()
    }

    def List<MasBoardDP> getBoardList() {
        masBoardDPRepo.findAll()
    }

    def MasCourses editCourses(MultipartFile file, String masCourse) {
        MasCourses masCourses = new ObjectMapper().readValue(masCourse, MasCourses.class)
        String fileurl = masCourses.iconPath
        if(file!=null && file.size > 0)
         fileurl = this.amazonClient.uploadFile(file)
        log.info "fileurl: ${fileurl}"
        masCourses.iconPath = fileurl
        return saveMasCourses(masCourses)
    }

    def MasCourses saveMasCourses(MasCourses masCourses) {

        MasCourses masCoursesDB = masCoursesRepo.save(masCourses)
        List<MasStream> masStreamList = masCourses.masStream
        for (MasStream ms : masStreamList) {
            ms.masCourses = masCoursesDB
            masStreamRepo.save(ms)
        }

        List<MasCourseYear> masCourseYearList = masCoursesDB.masCourseYear
        for (MasCourseYear my : masCourseYearList) {
            my.masCourses = masCoursesDB
            masCourseYearRepo.save(my)
        }
        masCoursesDB
    }

    def String deleteCourse(long courseId) {
        try {
            log.info "Deleting course record for course id ${courseId}"
            masCoursesRepo.deleteByCourseId(courseId)
            "Successfully deleted record for course id ${courseId}"
        }catch(Exception ex){
            log.error(ex)
            log.error "Exception occured while deleting course record for course id ${courseId}"
        }
    }

    def List<MasStreamLkp> getMasStreamList() {
        masStreamLkpRepo.findByIsActiveIgnoreCase("t")
    }

    def List<MasYearLkp> getMasYearList() {
        masYearLkpRepo.findByIsActiveIgnoreCase("t")
    }

    def List<UserTransactionDetails> getUserTransactionList() {
        List<UserTransactionDetails> userTransactionDetailsList = userTransactionDetailsRepo.findAll(Sort.by(Sort.Direction.DESC,"userTransId"))
        List<UserTransactionDetails> utdli = new ArrayList<>()
        for(UserTransactionDetails utd : userTransactionDetailsList){
            MasCourses masCourses = masCoursesRepo.findByCourseId(utd.courseId)
            MasCoursesDTO masCoursesDTO = new MasCoursesDTO()
            masCoursesDTO.courseId = masCourses.courseId
            masCoursesDTO.courseCode = masCourses.courseCode
            masCoursesDTO.courseName = masCourses.courseName
            masCoursesDTO.coursePrice = masCourses.coursePrice
            utd.masCourses = masCoursesDTO

            UserDetails userDetails = userDetailsRepo.findByUserid(utd.userid)
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO()
            userDetailsDTO.userName = userDetails.userName
            userDetailsDTO.firstName = userDetails.firstName
            userDetailsDTO.middleName = userDetails.middleName
            userDetailsDTO.lastName = userDetails.lastName
            userDetailsDTO.mobileNo = userDetails.mobileNo
            userDetailsDTO.emailId = userDetails.emailId
            utd.userDetails = userDetailsDTO
            utdli.add(utd)

        }
        return utdli
    }

    def MasBoard saveBoard(MasBoard masBoard) {
        masBoard.createdBy = "readby-admin"
        masBoardRepo.save(masBoard)
    }

    def MasBoard editBoard(MasBoard masBoard) {
        masBoard.updatedBy = "readby-admin"
        masBoard.updatedAt = new Timestamp(new Date().getTime())
        masBoardRepo.save(masBoard)
    }

    def String deleteBoard(long aLong) {
        try {
            log.info "Deleting board record for board id ${aLong}"
            masBoardRepo.deleteByBoardId(aLong)
            "Successfully deleted record for board id ${aLong}"
        }catch(Exception ex){
            log.error(ex)
            log.error "Exception occured while deleting course record for course id ${aLong}"
        }

    }
}
