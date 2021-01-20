package com.brainup.readby.dao.repository;

import com.brainup.readby.dao.entity.MasYearLkp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasYearLkpRepo extends JpaRepository<MasYearLkp,Long> {
    List<MasYearLkp> findByIsActiveIgnoreCase(String isActive);

    List<MasYearLkp> findByIsActiveIgnoreCaseAndCourseId(String s, Long aLong);
}
