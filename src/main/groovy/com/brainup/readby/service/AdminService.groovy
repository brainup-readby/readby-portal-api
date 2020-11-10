package com.brainup.readby.service

import com.brainup.readby.dao.entity.MasBoard
import com.brainup.readby.dao.entity.MasCourseYear
import com.brainup.readby.dao.entity.MasCourses
import com.brainup.readby.dao.entity.MasCoursesType
import com.brainup.readby.dao.entity.MasStream
import com.brainup.readby.dao.repository.MasBoardRepo
import com.brainup.readby.dao.repository.MasChapterRepo
import com.brainup.readby.dao.repository.MasCourseYearRepo
import com.brainup.readby.dao.repository.MasCoursesRepo
import com.brainup.readby.dao.repository.MasCoursesTypeRepo
import com.brainup.readby.dao.repository.MasStreamRepo
import com.brainup.readby.dao.repository.MasSubjectsRepo
import com.brainup.readby.dao.repository.MasTopicRepo
import com.brainup.readby.dao.repository.UserDetailsRepo
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Slf4j
class AdminService {

    @Autowired
    UserDetailsRepo userDetailsRepo

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
    MasBoardRepo masBoardRepo

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

        masCoursesRepo.findAll()

    }

    def MasCourses addCourses(MasCourses masCourses) {

        MasCourses masCoursesDB = masCoursesRepo.save(masCourses)
        List<MasStream> masStreamList = masCoursesDB.masStream
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

    def List<MasCoursesType> getCourseType() {
        masCoursesTypeRepo.findAll()
    }

    def List<MasBoard> getBoardList() {
        masBoardRepo.findAll()
    }
}
