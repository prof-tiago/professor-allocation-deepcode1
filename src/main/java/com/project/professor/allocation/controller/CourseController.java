package com.project.professor.allocation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.service.CourseService;

@RestController
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}
}
