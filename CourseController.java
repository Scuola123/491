package ex.google.faculty_schedule_preference.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {
    //@Autowired private CourseService courseService;

    @Autowired private CourseRepository courseRepository;
    CourseController(CourseRepository repository) {
        this.courseRepository = repository;
    }


    @GetMapping(path="/courses")
    public String getCourses(Model model)
    {
        Course course = new Course();
        model.addAttribute("course", courseRepository.findAll());
        model.addAttribute("id",course.getId());
        model.addAttribute("course_name", course.getName());
        model.addAttribute("prefix", course.getPrefix());
        return "course/index";
    } 
    @PostMapping("/courses")
    public String suCourses(@ModelAttribute("course") Course course) {
        System.out.println(course);
        return "course/index";
    } 
}
