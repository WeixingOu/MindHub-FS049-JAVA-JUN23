package com.mindhub.crud.services.impls;

import com.mindhub.crud.models.Course;
import com.mindhub.crud.repositories.CourseRepository;
import com.mindhub.crud.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
}
