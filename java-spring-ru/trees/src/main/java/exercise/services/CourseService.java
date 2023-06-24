package exercise.services;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    public List<Course> getPreviousCourses(long rootCourseId) {
        Course rootCourse = courseRepository.findById(rootCourseId);
        String path = rootCourse.getPath();
        if (path != null) {
            String[] ids = path.split("\\.");
            return courseRepository.findCoursesByIds(ids);
        } else {
            return new ArrayList<>();
        }

    }
}
