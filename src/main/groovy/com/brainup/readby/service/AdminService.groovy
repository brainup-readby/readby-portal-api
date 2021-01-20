package com.brainup.readby.service

import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasBoardDP
import com.brainup.readby.dao.entity.MasChapters
import com.brainup.readby.dao.entity.MasCourseYear
import com.brainup.readby.dao.entity.MasCourses
import com.brainup.readby.dao.entity.MasCoursesType
import com.brainup.readby.dao.entity.MasStream
import com.brainup.readby.dao.entity.MasStreamLkp
import com.brainup.readby.dao.entity.MasSubjects
import com.brainup.readby.dao.entity.MasTopic
import com.brainup.readby.dao.entity.MasYearLkp
import com.brainup.readby.dao.entity.UserDetails
import com.brainup.readby.dao.entity.UserTransactionDetails
import com.brainup.readby.dao.repository.MasBoardDPRepo
import com.brainup.readby.dao.repository.MasBoardRepo
import com.brainup.readby.dao.repository.MasChapterRepo
import com.brainup.readby.dao.repository.MasChaptersDTORepo
import com.brainup.readby.dao.repository.MasCourseYearRepo
import com.brainup.readby.dao.repository.MasCoursesRepo
import com.brainup.readby.dao.repository.MasCoursesTypeRepo
import com.brainup.readby.dao.repository.MasStreamLkpRepo
import com.brainup.readby.dao.repository.MasStreamRepo
import com.brainup.readby.dao.repository.MasSubjectsRepo
import com.brainup.readby.dao.repository.MasTopicDTORepo
import com.brainup.readby.dao.repository.MasTopicRepo
import com.brainup.readby.dao.repository.MasYearLkpRepo
import com.brainup.readby.dao.repository.UserDetailsRepo
import com.brainup.readby.dao.repository.UserTransactionDetailsRepo
import com.brainup.readby.dto.MasChaptersDTO
import com.brainup.readby.dto.MasCoursesDTO
import com.brainup.readby.dto.MasSubjectsDTO
import com.brainup.readby.dto.MasTopicDTO
import com.brainup.readby.dto.UserDetailsDTO
import com.brainup.readby.util.AmazonClient
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.sql.Timestamp
import java.util.stream.Collectors

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
    MasTopicDTORepo masTopicDTORepo

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

    @Autowired
    MasChaptersDTORepo masChaptersDTORepo


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

        List<MasCourses> masCoursesList = masCoursesRepo.findAll()
        List<MasCourses> li = new ArrayList<MasCourses>()

        for(MasCourses masCourses : masCoursesList){
            if(masCourses.boardId != null) {
                MasBoardDP masBoardDP = masBoardDPRepo.findByBoardId(masCourses.boardId)
                masCourses.boardName = masBoardDP.boardName
            }
            li.add(masCourses)
        }
        li
    }

    def MasCourses addCourses(MultipartFile file, String masCourse) {
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
        if (file != null && file.size > 0)
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
            MasCourses masCourses1 = masCoursesRepo.findByCourseId(courseId)
            masCourses1.isActive = "f"
            masCoursesRepo.save(masCourses1)
            "Successfully deleted record for course id ${courseId}"
        } catch (Exception ex) {
            log.error(ex)
            log.error "Exception occured while deleting course record for course id ${courseId}"
        }
    }

    def List<MasStreamLkp> getMasStreamList(Long courseId) {
        masStreamLkpRepo.findByIsActiveIgnoreCaseAndCourseId("t",courseId)
    }

    def List<MasYearLkp> getMasYearList(Long courseId) {
        masYearLkpRepo.findByIsActiveIgnoreCaseAndCourseId("t",courseId)
    }

    def List<UserTransactionDetails> getUserTransactionList() {
        List<UserTransactionDetails> userTransactionDetailsList = userTransactionDetailsRepo.findAll(Sort.by(Sort.Direction.DESC, "userTransId"))
        List<UserTransactionDetails> utdli = new ArrayList<>()
        for (UserTransactionDetails utd : userTransactionDetailsList) {
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
            MasBoard masBoard = masBoardRepo.findByBoardId(aLong)
            masBoard.isActive = "f"
            masBoardRepo.save(masBoard)
            "Successfully deleted record for board id ${aLong}"
        } catch (Exception ex) {
            log.error(ex)
            log.error "Exception occured while deleting board record for board id ${aLong}"
        }

    }

    def List<UserDetails> getUserList() {

        userDetailsRepo.findAll()
    }

    def List<MasSubjectsDTO> getSubjectList() {

        List<MasSubjects> masSubjectsList = masSubjectsRepo.findAll()
        List<MasSubjectsDTO> li = new ArrayList<>()
        for (MasSubjects masSubjects : masSubjectsList) {
            MasSubjectsDTO masSubjectsDTO = new MasSubjectsDTO()
            masSubjectsDTO.subjectId = masSubjects.subjectId
            masSubjectsDTO.subjectName = masSubjects.subjectName
            masSubjectsDTO.subjectCode = masSubjects.subjectCode
            masSubjectsDTO.subjectPrice = masSubjects.subjectPrice
            masSubjectsDTO.iconPath = masSubjects.iconPath
            masSubjectsDTO.streamId = masSubjects.streamId
            MasStream masStream = masStreamRepo.findByStreamId(masSubjects.streamId)

            if(masStream.masCourses != null && masStream.masCourses.courseId !=null)
            masSubjectsDTO.courseId = masStream.masCourses.courseId

            masSubjectsDTO.streamName = masStream.streamName
            masSubjectsDTO.streamCode = masStream.streamCode
            masSubjectsDTO.yearId = masSubjects.yearId
            MasCourseYear masCourseYear = masCourseYearRepo.findByYearId(masSubjects.yearId)
            masSubjectsDTO.year = masCourseYear.year
            masSubjectsDTO.yearDisplayName = masCourseYear.displayName
            masSubjectsDTO.isActive = masSubjects.isActive
            li.add(masSubjectsDTO)
        }
        li
    }

    def MasSubjects addSubjects(MultipartFile multipartFile, String masSubjects) {

        String fileurl = this.amazonClient.uploadFile(multipartFile)
        log.info "fileurl: ${fileurl}"
        MasSubjects masSubjects1 = new ObjectMapper().readValue(masSubjects, MasSubjects.class)
        masSubjects1.iconPath = fileurl
        masSubjects1.createdBy = "Admin"
       /* MasCourses masCoursesDB = masCoursesRepo.findByCourseId(masSubjects1.courseId)

        MasStream ms = masSubjects1.masStream
        ms.masCourses = masCoursesDB
        MasStream masStream = masStreamRepo.save(ms)
        masSubjects1.streamId = masStream.streamId

        MasCourseYear my = masSubjects1.masCourseYear
         my.masCourses = masCoursesDB
        MasCourseYear masCourseYear = masCourseYearRepo.save(my)
        masSubjects1.yearId = masCourseYear.yearId8*/

        return masSubjectsRepo.save(masSubjects1)
    }

    def MasSubjects editSubjects(MultipartFile file, String masSubjects) {

        MasSubjects masSubjects1 = new ObjectMapper().readValue(masSubjects, MasSubjects.class)
        String fileurl = masSubjects1.iconPath
        if (file != null && file.size > 0)
            fileurl = this.amazonClient.uploadFile(file)
        log.info "fileurl: ${fileurl}"
        masSubjects1.iconPath = fileurl
        masSubjects1.updatedBy = "Admin"
      /*  MasCourses masCoursesDB = masCoursesRepo.findByCourseId(masSubjects1.courseId)

        MasStream ms = masSubjects1.masStream
        ms.masCourses = masCoursesDB
        MasStream masStream = masStreamRepo.save(ms)
        masSubjects1.streamId = masStream.streamId

        MasCourseYear my = masSubjects1.masCourseYear
        my.masCourses = masCoursesDB
        MasCourseYear masCourseYear = masCourseYearRepo.save(my)
        masSubjects1.yearId = masCourseYear.yearId*/

        return masSubjectsRepo.save(masSubjects1)
    }

    def String deleteSubject(long aLong) {
        try {
            log.info "Deleting subject record for subject id ${aLong}"
            MasSubjects masSubjects = masSubjectsRepo.findBySubjectId(aLong)
            masSubjects.isActive = "f"
            masSubjectsRepo.save(masSubjects)
            "Successfully deleted record for subject id ${aLong}"
        } catch (Exception ex) {
            log.error(ex)
            log.error "Exception occured while deleting subject record for subject id ${aLong}"
        }
    }

    def List<MasChaptersDTO> getChapterList() {

        masChaptersDTORepo.findAll()

    }

    def MasChaptersDTO addChapters(MultipartFile multipartFile, String masChapters) {

        String fileurl = this.amazonClient.uploadFile(multipartFile)
        log.info "fileurl: ${fileurl}"
        MasChaptersDTO masChapters1 = new ObjectMapper().readValue(masChapters, MasChaptersDTO.class)
        masChapters1.iconPath = fileurl
        masChapters1.createdBy = "readby-admin"
        return masChaptersDTORepo.save(masChapters1)

    }

    def MasChaptersDTO editChapters(MultipartFile file, String masChapters) {
        MasChaptersDTO masChapters1 = new ObjectMapper().readValue(masChapters, MasChaptersDTO.class)
        String fileurl = masChapters1.iconPath
        if (file != null && file.size > 0)
            fileurl = this.amazonClient.uploadFile(file)
        log.info "fileurl: ${fileurl}"
        masChapters1.iconPath = fileurl
        masChapters1.updatedBy = "readby-admin"
        masChapters1.updatedAt = new Timestamp(new Date().getTime())
        return masChaptersDTORepo.save(masChapters1)
    }

    def String deleteChapter(long aLong) {

        try {
            log.info "Deleting chapter record for chapter id ${aLong}"
            MasChaptersDTO masChaptersDTO = masChaptersDTORepo.findByChapterId(aLong)
            masChaptersDTO.isActive = "f"
            masChaptersDTORepo.save(masChaptersDTO)
            "Successfully deleted record for chapter id ${aLong}"
        } catch (Exception ex) {
            log.error(ex)
            log.error "Exception occured while deleting chapter record for chapter id ${aLong}"
        }

    }

    def List<MasTopicDTO> getTopicList() {
        masTopicDTORepo.findAll()
    }

    def MasTopicDTO addTopics(MultipartFile multipartFile, String masTopics) {

        String fileurl = this.amazonClient.uploadFile(multipartFile)
        log.info "fileurl: ${fileurl}"
        MasTopicDTO masTopic = new ObjectMapper().readValue(masTopics, MasTopicDTO.class)
        masTopic.iconPath = fileurl
        masTopic.createdBy = "readby-admin"
        return masTopicDTORepo.save(masTopic)
    }

    def MasTopicDTO editTopics(MultipartFile file, String masTopics) {
        MasTopicDTO masTopic = new ObjectMapper().readValue(masTopics, MasTopicDTO.class)
        String fileurl = masTopic.iconPath
        if (file != null && file.size > 0)
            fileurl = this.amazonClient.uploadFile(file)
        log.info "fileurl: ${fileurl}"
        masTopic.iconPath = fileurl
        masTopic.updatedBy = "readby-admin"
        masTopic.updatedAt = new Timestamp(new Date().getTime())
        return masTopicDTORepo.save(masTopic)
    }

    def String deleteTopic(long aLong) {
        try {
            log.info "Deleting topic record for topic id ${aLong}"
            MasTopicDTO masTopic = masTopicDTORepo.findByTopicId(aLong)
            masTopic.isActive = "f"
            masTopicDTORepo.save(masTopic)
            "Successfully deleted record for topic id ${aLong}"
        } catch (Exception ex) {
            log.error(ex)
            log.error "Exception occured while deleting course record for topic id ${aLong}"
        }

    }

    def List<MasChaptersDTO> getChaptersBySubject(Map<String, String> map) {
        masChaptersDTORepo.findBySubjectId(map.get("subjectId").toLong())
    }

    def List<MasTopicDTO> getTopicsByChapter(Map<String, String> map) {
        masTopicDTORepo.findByChapterId(map.get("chapterId").toLong())
    }

    def List<MasSubjectsDTO> getSubjectByStreamOrYear(Map<String, String> map) {
        List<MasSubjects> masSubjectsList
        if(map.get("streamId") != null){
            masSubjectsList = masSubjectsRepo.findByStreamId(map.get("streamId").toLong())
        }else{
            masSubjectsList = masSubjectsRepo.findByYearId(map.get("yearId").toLong())
        }
        List<MasSubjectsDTO> li = new ArrayList<>()
        for (MasSubjects masSubjects : masSubjectsList) {
            MasSubjectsDTO masSubjectsDTO = new MasSubjectsDTO()
            masSubjectsDTO.subjectId = masSubjects.subjectId
            masSubjectsDTO.subjectName = masSubjects.subjectName
            masSubjectsDTO.subjectCode = masSubjects.subjectCode
            masSubjectsDTO.subjectPrice = masSubjects.subjectPrice
            masSubjectsDTO.iconPath = masSubjects.iconPath
            masSubjectsDTO.streamId = masSubjects.streamId
            MasStream masStream = masStreamRepo.findByStreamId(masSubjects.streamId)

            if(masStream.masCourses != null && masStream.masCourses.courseId !=null)
            masSubjectsDTO.courseId = masStream.masCourses.courseId
            
            masSubjectsDTO.streamName = masStream.streamName
            masSubjectsDTO.streamCode = masStream.streamCode
            masSubjectsDTO.yearId = masSubjects.yearId
            MasCourseYear masCourseYear = masCourseYearRepo.findByYearId(masSubjects.yearId)
            masSubjectsDTO.year = masCourseYear.year
            masSubjectsDTO.yearDisplayName = masCourseYear.displayName
            masSubjectsDTO.isActive = masSubjects.isActive
            li.add(masSubjectsDTO)
        }
        li
    }

    def MasStreamLkp addStream(MasStreamLkp masStreamLkp) {
        masStreamLkpRepo.save(masStreamLkp)
    }

    def MasYearLkp addYear(MasYearLkp masYearLkp) {
        masYearLkpRepo.save(masYearLkp)
    }
}
