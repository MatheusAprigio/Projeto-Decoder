package com.ead.course.services;

import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;

import java.util.UUID;

public interface CourseUserService {

    boolean existByCourseAndUserId(CourseModel courseModel, UUID userid);

    CourseUserModel save(CourseUserModel convertToCourseUserModel);

    CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel convertToCourseUserModel);

    void deleteCourseUserByUser(UUID userId);

    boolean existsByUserId(UUID userId);
}
