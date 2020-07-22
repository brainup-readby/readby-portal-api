package com.brainup.readby.service

import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasCourseYear
import com.brainup.readby.dao.entity.MasCourses
import com.brainup.readby.dao.entity.MasGlobalConfig
import com.brainup.readby.dao.entity.MasStream
import com.brainup.readby.dao.entity.MasSubjects
import com.brainup.readby.dao.entity.OtpInfo
import com.brainup.readby.dao.entity.UserDetails
import com.brainup.readby.dao.entity.UserSubscriptions
import com.brainup.readby.dao.repository.MasBoardRepo
import com.brainup.readby.dao.repository.MasCourseYearRepo
import com.brainup.readby.dao.repository.MasCoursesRepo
import com.brainup.readby.dao.repository.MasGlobalConfigRepo
import com.brainup.readby.dao.repository.MasStreamRepo
import com.brainup.readby.dao.repository.MasSubjectsRepo
import com.brainup.readby.dao.repository.OtpInfoRepo
import com.brainup.readby.dao.repository.UserDetailsRepo
import com.brainup.readby.dao.repository.UserSubscriptionsRepo
import com.brainup.readby.dto.MasBoardDTO
import com.brainup.readby.dto.MasCoursesDTO
import com.brainup.readby.dto.MasStreamDTO
import com.brainup.readby.dto.SMSResponse
import com.brainup.readby.proxy.ServiceCall
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import java.sql.Timestamp

@Service
@Slf4j
class StudentService {

    @Autowired
    MasGlobalConfigRepo masGlobalConfigRepo

    @Autowired
    ServiceCall serviceCall

    @Autowired
    OtpInfoRepo otpInfoRepo

    @Autowired
    UserDetailsRepo userDetailsRepo

    @Autowired
    MasBoardRepo masBoardRepo

    @Autowired
    UserSubscriptionsRepo userSubscriptionsRepo

    @Autowired
    MasStreamRepo masStreamRepo

    @Autowired
    MasCourseYearRepo masCourseYearRepo

    @Autowired
    MasCoursesRepo masCoursesRepo

    @Autowired
    MasSubjectsRepo masSubjectsRepo

    @Value('${readby.otp.url}')
    private String otpUrl

    List<MasGlobalConfig> findByIsActive(String isActive) {
        List<MasGlobalConfig> masGlobalConfig = masGlobalConfigRepo.findByIsActiveIgnoreCase(isActive)
        return masGlobalConfig
    }

    def String sendOTP(Map<String, String> map) {

        int randomPIN = (int)(Math.random()*9000)+1000
        log.info"randomOTP ${randomPIN}"
        map.put("randomPIN",String.valueOf(randomPIN))
        //SMSResponse otpValue = serviceCall.getServiceResult(otpUrl, map)
        OtpInfo otpInfo = new OtpInfo(
                otp: String.valueOf(randomPIN),
                mobileNo: map.get("mobileNo").toLong(),
                retryLimit: 3,
                createdBy: "read_by",
                updatedBy: "read_by",
                isUsed: "f"
        )
        otpInfoRepo.save(otpInfo)
        return String.valueOf(randomPIN)
    }

    def boolean verifyOTP(Map<String, String> map) {
        boolean otpFlag = otpInfoRepo.existsByOtpAndMobileNoAndIsUsed(map.get("otp"), map.get("mobileNo").toLong(), 'f')
        if (otpFlag) {
            OtpInfo otpInfo = otpInfoRepo.findTopByOtpAndMobileNoAndIsUsedOrderByOtpIdDesc(map.get("otp"), map.get("mobileNo").toLong(), 'f')
            otpInfo.isUsed = 't'
            otpInfoRepo.save(otpInfo)
        }
        otpFlag
    }

    def String resendOTP(Map<String, String> map) {
        int randomPIN = (int)(Math.random()*9000)+1000
        log.info"randomOTP ${randomPIN}"
        map.put("randomPIN",String.valueOf(randomPIN))
        SMSResponse otpValue = serviceCall.getServiceResult(otpUrl, map)
        OtpInfo otpInfo = otpInfoRepo.findTopByMobileNoAndIsUsedOrderByOtpIdDesc(map.get("mobileNo").toLong(), 'f')
        if (otpInfo.retryLimit > 0) {
            Integer retryLimit = otpInfo.retryLimit
            otpInfo.retryLimit = retryLimit - 1
            otpInfo.otp = String.valueOf(randomPIN)
            otpInfoRepo.save(otpInfo)
            String.valueOf(randomPIN)
        } else {
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
        UserDetails userDetails = userDetailsRepo.findTopByMobileNoOrderByUseridDesc(map.get("mobileNo").toLong())
        List<UserSubscriptions> userSubscriptions = userDetails.userSubscriptions
        List<UserSubscriptions> userSubscriptionsli = new ArrayList<>()
        log.info "Number of user subscription ${userSubscriptions.size()}"
        for (UserSubscriptions us : userSubscriptions) {
            MasStream masStreamDao = masStreamRepo.findByStreamId(us.streamId)
            List<MasSubjects> masSubjectsList = masSubjectsRepo.findByStreamId(us.streamId)
            MasStreamDTO masStreamDTO = new MasStreamDTO()
            masStreamDTO.streamId = masStreamDao.streamId
            masStreamDTO.streamName = masStreamDao.streamName
            masStreamDTO.streamCode = masStreamDao.streamCode
            masStreamDTO.masSubjects = masSubjectsList
            us.masStream = masStreamDTO
            MasCourseYear masCourseYearDao = masCourseYearRepo.findByYearId(us.yearId)
            us.masCourseYear = masCourseYearDao
            MasBoard masBoardDao = masBoardRepo.findByBoardId(us.boardId)
            MasBoardDTO masBoardDTO = new MasBoardDTO()
            masBoardDTO.boardId = masBoardDao.boardId
            masBoardDTO.boardName = masBoardDao.boardName
            masBoardDTO.boardCode = masBoardDao.boardCode
            us.masBoard = masBoardDTO
            MasCourses masCoursesDao = masCoursesRepo.findByCourseId(us.courseId)
            MasCoursesDTO masCoursesDTO = new MasCoursesDTO()
            masCoursesDTO.courseId = masCoursesDao.courseId
            masCoursesDTO.courseName = masCoursesDao.courseName
            masCoursesDTO.courseCode = masCoursesDao.courseCode
            us.masCourses = masCoursesDTO
            userSubscriptionsli.add(us)
        }
        userDetails.userSubscriptions = userSubscriptionsli
        userDetails
    }

    def List<MasBoard> getBoardDetail() {
        masBoardRepo.findByIsActiveIgnoreCase("t")
    }

    def UserDetails findByMobileNo(long mobileNo) {
        userDetailsRepo.findByMobileNo(mobileNo)

    }

    def UserSubscriptions registerStudentSubscription(UserSubscriptions userSubscriptions) {

        userSubscriptionsRepo.save(userSubscriptions)

    }
}
