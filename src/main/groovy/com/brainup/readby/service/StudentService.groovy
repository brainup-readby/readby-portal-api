package com.brainup.readby.service

import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasCourseYear
import com.brainup.readby.dao.entity.MasCourses
import com.brainup.readby.dao.entity.MasGlobalConfig
import com.brainup.readby.dao.entity.MasRole
import com.brainup.readby.dao.entity.MasStream
import com.brainup.readby.dao.entity.MasSubjects
import com.brainup.readby.dao.entity.MasTopic
import com.brainup.readby.dao.entity.OtpInfo
import com.brainup.readby.dao.entity.RbMultipleAnswers
import com.brainup.readby.dao.entity.RbQuestionnaires
import com.brainup.readby.dao.entity.RbStudentAnswers
import com.brainup.readby.dao.entity.RbStudentReport
import com.brainup.readby.dao.entity.RbStudentStudyState
import com.brainup.readby.dao.entity.ReadbyFeedback
import com.brainup.readby.dao.entity.UserDetails
import com.brainup.readby.dao.entity.UserLoginDetails
import com.brainup.readby.dao.entity.UserSubscriptions
import com.brainup.readby.dao.entity.UserTransactionDetails
import com.brainup.readby.dao.repository.MasBoardRepo
import com.brainup.readby.dao.repository.MasCourseYearRepo
import com.brainup.readby.dao.repository.MasCoursesRepo
import com.brainup.readby.dao.repository.MasGlobalConfigRepo
import com.brainup.readby.dao.repository.MasRoleRepo
import com.brainup.readby.dao.repository.MasStreamRepo
import com.brainup.readby.dao.repository.MasSubjectsRepo
import com.brainup.readby.dao.repository.MasTopicRepo
import com.brainup.readby.dao.repository.OtpInfoRepo
import com.brainup.readby.dao.repository.RbMultipleAnswersRepo
import com.brainup.readby.dao.repository.RbQuestionnairesRepo
import com.brainup.readby.dao.repository.RbStudentAnswersRepo
import com.brainup.readby.dao.repository.RbStudentReportRepo
import com.brainup.readby.dao.repository.RbStudentStudyStateRepo
import com.brainup.readby.dao.repository.ReadbyFeedbackRepo
import com.brainup.readby.dao.repository.UserDetailsRepo
import com.brainup.readby.dao.repository.UserLoginDetailsRepo
import com.brainup.readby.dao.repository.UserSubscriptionsRepo
import com.brainup.readby.dao.repository.UserTransactionDetailsRepo
import com.brainup.readby.dto.MasBoardDTO
import com.brainup.readby.dto.MasCoursesDTO
import com.brainup.readby.dto.MasStreamDTO
import com.brainup.readby.dto.RbStudentStudyStateDTO
import com.brainup.readby.dto.SMSResponse
import com.brainup.readby.proxy.ServiceCall
import com.brainup.readby.util.ReadByUtil
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
    ReadByUtil readByUtil

    @Autowired
    OtpInfoRepo otpInfoRepo

    @Autowired
    MasTopicRepo masTopicRepo

    @Autowired
    UserDetailsRepo userDetailsRepo

    @Autowired
    UserLoginDetailsRepo userLoginDetailsRepo

    @Autowired
    MasBoardRepo masBoardRepo

    @Autowired
    UserSubscriptionsRepo userSubscriptionsRepo

    @Autowired
    MasStreamRepo masStreamRepo

    @Autowired
    MasCourseYearRepo masCourseYearRepo

    @Autowired
    UserTransactionDetailsRepo userTransactionDetailsRepo

    @Autowired
    MasCoursesRepo masCoursesRepo

    @Autowired
    MasSubjectsRepo masSubjectsRepo

    @Autowired
    MasRoleRepo masRoleRepo

    @Autowired
    RbStudentStudyStateRepo rbStudentStudyStateRepo

    @Autowired
    RbQuestionnairesRepo rbQuestionnairesRepo

    @Autowired
    RbStudentAnswersRepo rbStudentAnswersRepo

    @Autowired
    RbMultipleAnswersRepo rbMultipleAnswersRepo

    @Autowired
    RbStudentReportRepo rbStudentReportRepo

    @Autowired
    ReadbyFeedbackRepo readbyFeedbackRepo

    @Value('${readby.otp.url}')
    private String otpUrl

    @Value('${student.maxmarks}')
    private String maxMarks

    @Value('${student.percentagethreshold}')
    private String percentagethreshold

    @Value('${readby.mid}')
    private String mid

    @Value('${readby.mkey}')
    private String mkey

    List<MasGlobalConfig> findByIsActive(String isActive) {
        List<MasGlobalConfig> masGlobalConfig = masGlobalConfigRepo.findByIsActiveIgnoreCase(isActive)
        return masGlobalConfig
    }

    def String sendOTP(Map<String, String> map) {

        int randomPIN = (int) (Math.random() * 9000) + 1000
        log.info "randomOTP ${randomPIN}"
        map.put("randomPIN", String.valueOf(randomPIN))
        SMSResponse otpValue = serviceCall.getServiceResult(otpUrl, map)
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

        OtpInfo otpInfo = otpInfoRepo.findTopByMobileNoAndIsUsedOrderByOtpIdDesc(map.get("mobileNo").toLong(), 'f')
        if (otpInfo != null) {
            int randomPIN = (int) (Math.random() * 9000) + 1000
            log.info "randomOTP ${randomPIN}"
            map.put("randomPIN", String.valueOf(randomPIN))
            SMSResponse otpValue = serviceCall.getServiceResult(otpUrl, map)
            if (otpInfo.retryLimit > 0) {
                Integer retryLimit = otpInfo.retryLimit
                otpInfo.retryLimit = retryLimit - 1
                otpInfo.otp = String.valueOf(randomPIN)
                otpInfoRepo.save(otpInfo)
                String.valueOf(randomPIN)
            } else {
                return "you have exceeded the limit "
            }
        } else {
            return "Use send otp option first."
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
        return mapuserSubscriptions(userDetails)
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

    def UserDetails getUserDetails(Map<String, String> map) {
        UserDetails userDetails = userDetailsRepo.findByUserid(map.get("userId").toLong())
        return mapuserSubscriptions(userDetails)
    }

    private UserDetails mapuserSubscriptions(UserDetails userDetails) {
        if (userDetails.userSubscriptions != null && userDetails.userSubscriptions.size() > 0) {
            List<UserSubscriptions> userSubscriptions = userDetails.userSubscriptions
            List<UserSubscriptions> userSubscriptionsli = new ArrayList<>()
            log.info "Number of user subscription ${userSubscriptions.size()}"
            for (UserSubscriptions us : userSubscriptions) {
                RbStudentStudyState rbStudentStudyStateDao = rbStudentStudyStateRepo.findByUserId(us.userid)
                RbStudentStudyStateDTO rbStudentStudyStateDTO = new RbStudentStudyStateDTO()
                if (rbStudentStudyStateDao != null) {
                    System.out.println("Student study state id: " + rbStudentStudyStateDao.stateId)
                    rbStudentStudyStateDTO.stateId = rbStudentStudyStateDao.stateId
                    System.out.println(rbStudentStudyStateDTO.stateId)
                    rbStudentStudyStateDTO.userId = rbStudentStudyStateDao.userId
                    rbStudentStudyStateDTO.topicId = rbStudentStudyStateDao.topicId
                    rbStudentStudyStateDTO.videoLeftTime = rbStudentStudyStateDao.videoLeftTime
                }
                us.rbStudentStudyState = rbStudentStudyStateDTO
                MasStream masStreamDao = masStreamRepo.findByStreamId(us.streamId)
                List<MasSubjects> masSubjectsList = masSubjectsRepo.findByStreamId(us.streamId)
                MasStreamDTO masStreamDTO = new MasStreamDTO()
                if (masStreamDTO != null) {
                    masStreamDTO.streamId = masStreamDao.streamId
                    masStreamDTO.streamName = masStreamDao.streamName
                    masStreamDTO.streamCode = masStreamDao.streamCode
                    masStreamDTO.masSubjects = masSubjectsList
                }
                us.masStream = masStreamDTO
                MasCourseYear masCourseYearDao = masCourseYearRepo.findByYearId(us.yearId)
                us.masCourseYear = masCourseYearDao
                MasBoard masBoardDao = masBoardRepo.findByBoardId(us.boardId)
                MasBoardDTO masBoardDTO = new MasBoardDTO()
                if (masBoardDao != null) {
                    masBoardDTO.boardId = masBoardDao.boardId
                    masBoardDTO.boardName = masBoardDao.boardName
                    masBoardDTO.boardCode = masBoardDao.boardCode
                }
                us.masBoard = masBoardDTO
                MasCourses masCoursesDao = masCoursesRepo.findByCourseId(us.courseId)
                MasCoursesDTO masCoursesDTO = new MasCoursesDTO()
                if (masCoursesDao != null) {
                    masCoursesDTO.courseId = masCoursesDao.courseId
                    masCoursesDTO.courseName = masCoursesDao.courseName
                    masCoursesDTO.courseCode = masCoursesDao.courseCode
                }
                us.masCourses = masCoursesDTO
                userSubscriptionsli.add(us)
            }
            userDetails.userSubscriptions = userSubscriptionsli
        }
        userDetails
    }

    def List<MasRole> findRoleByIsActive(String isActive) {
        masRoleRepo.findByIsActive(isActive)
    }

    def RbStudentStudyState saveRbStudentStudyState(RbStudentStudyState rbStudentStudyState) {
        return rbStudentStudyStateRepo.save(rbStudentStudyState)
    }

    def RbQuestionnaires getQuestionByTopic(Map<String, String> map) {
        rbQuestionnairesRepo.findByTopicId(map.get("topicId").toLong())
    }

    def RbStudentReport saveStudentAnswer(List<RbStudentAnswers> rbStudentAnswers) {
        int totalMarksScored = 0
        List<RbStudentAnswers> rbStudentAnswered = rbStudentAnswersRepo.saveAll(rbStudentAnswers)
        System.out.println("List of student answer.." + rbStudentAnswered.size())
        log.info("List of student answer.." + rbStudentAnswered.size())
        for (RbStudentAnswers obj : rbStudentAnswered) {
            System.out.println("findByQuestionId...." + obj.questionId)
            log.info("findByQuestionId...." + obj.questionId)
            RbMultipleAnswers rbMultipleAnswers = rbMultipleAnswersRepo.findByQuestionId(obj.questionId)
            System.out.println("rbMultipleAnswers.correctOptionId.." + rbMultipleAnswers.correctOptionId + "obj.givenAnswer" + obj.givenAnswer)
            log.info("rbMultipleAnswers.correctOptionId.." + rbMultipleAnswers.correctOptionId + "obj.givenAnswer" + obj.givenAnswer)
            if (rbMultipleAnswers.correctOptionId == obj.givenAnswer) {
                totalMarksScored = totalMarksScored + rbMultipleAnswers.marks
                System.out.println("totalMarksScored.." + totalMarksScored)
                log.info("totalMarksScored.." + totalMarksScored)
            }
        }
        System.out.println("maxMarks.." + maxMarks.toInteger())
        log.info("maxMarks.." + maxMarks.toInteger())
        int percentage = totalMarksScored / maxMarks.toInteger() * 100
        System.out.println("percentage.." + percentage)
        log.info("percentage.." + percentage)
        String result
        System.out.println("percentagethreshold.." + percentagethreshold.toInteger())
        log.info("percentagethreshold.." + percentagethreshold.toInteger())
        if (percentage > percentagethreshold.toInteger()) {
            result = "pass"
        } else {
            result = "fail"
        }
        RbStudentReport rbStudentReport = new RbStudentReport(
                topicId: rbStudentAnswers.get(0).topicId,
                userId: rbStudentAnswers.get(0).userId,
                totalMarksObtained: totalMarksScored,
                maximumMarks: maxMarks.toInteger(),
                totalPercentage: percentage,
                overallResult: result
        )
        System.out.println("topicid and userid..." + rbStudentAnswers.get(0).topicId + "and" + rbStudentAnswers.get(0).userId)
        log.info("topicid and userid..." + rbStudentAnswers.get(0).topicId + "and" + rbStudentAnswers.get(0).userId)
        rbStudentReportRepo.save(rbStudentReport)

    }

    def UserTransactionDetails saveUserTransaction(UserTransactionDetails userTransactionDetails) {
        String orderId = "readby_" + readByUtil.getRandomNumberString()
        userTransactionDetails.orderId = orderId
        userTransactionDetails.createdBy = "read_by"
        userTransactionDetails.updatedBy = "read_by"
        UserTransactionDetails ut = userTransactionDetailsRepo.save(userTransactionDetails)
        String checksum = readByUtil.paytmChecksum(mid, orderId, mkey,userTransactionDetails.userid, userTransactionDetails.transactionAmount)
        ut.checksumVal = checksum
        UserTransactionDetails utdb = userTransactionDetailsRepo.save(userTransactionDetails)
        return utdb
    }

    def UserTransactionDetails updateUserTransaction(UserTransactionDetails userTransactionDetails) {

        return userTransactionDetailsRepo.save(userTransactionDetails)
    }

    def MasTopic updateTopicFlag(Map<String, String> map) {
        System.out.println(map.get("TOPIC_ID"))
        System.out.println(map.get("VIDEO_STATUS"))
        System.out.println(map.get("TEST_STATUS"))
        MasTopic masTopic = masTopicRepo.findByTopicId(map.get("TOPIC_ID").toLong())
        masTopic.videoStatus = map.get("VIDEO_STATUS").toString()
        masTopic.testStatus = map.get("TEST_STATUS").toString()
        masTopicRepo.save(masTopic)

    }

    def boolean getLoginDetail(Map<String, String> map) {

        UserLoginDetails userLoginDetails = userLoginDetailsRepo.findByMobileNo(map.get("mobileNo").toLong())
        if (userLoginDetails == null) {
            UserDetails userDetails = userDetailsRepo.findTopByMobileNoOrderByUseridDesc(map.get("mobileNo").toLong())
            UserLoginDetails uld = new UserLoginDetails(
                    userid: userDetails.userid,
                    mobileNo: map.get("mobileNo").toLong(),
                    createdBy: "readby",
                    loginFlag: "t"
            )
            userLoginDetailsRepo.save(uld)
            true
        } else if (userLoginDetails.loginFlag.equalsIgnoreCase("f")) {
            userLoginDetails.loginFlag = "t"
            userLoginDetails.updatedBy = "readby"
            userLoginDetails.updatedAt = new Timestamp(new Date().getTime())
            userLoginDetailsRepo.save(userLoginDetails)
            true
        } else {
            false
        }
    }

    def UserLoginDetails getLogoutDetail(Map<String, String> map) {
        UserLoginDetails userLoginDetails = userLoginDetailsRepo.findByMobileNo(map.get("mobileNo").toLong())
        userLoginDetails.loginFlag = "f"
        userLoginDetails.updatedBy = "readby"
        userLoginDetails.updatedAt = new Timestamp(new Date().getTime())
        return userLoginDetailsRepo.save(userLoginDetails)

    }

    def ReadbyFeedback saveFeedBack(ReadbyFeedback readbyFeedback) {
        readbyFeedbackRepo.save(readbyFeedback)
    }
}
