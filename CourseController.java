package ex.google.faculty_schedule_preference.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {
   @Autowired private CourseRepository courseRepository;
    
    
    /*********************
            Courses
    *********************/

    @GetMapping(path="/courses")
    public String getCourses(Model model)
    {
        Course course = new Course();
        model.addAttribute("course", courseRepository.findAll());
        model.addAttribute("id",course.getId());
        model.addAttribute("prefix",course.getPrefix());
        model.addAttribute("name", course.getName());
        model.addAttribute("units", course.getUnits());
        model.addAttribute("type", course.getType());

        return "courses";
    } //part A
    @PostMapping("/courses")
    public String submitCourses(@ModelAttribute("course") Course course) {
        System.out.println(course);
        return "courses";
    } //part B

    @GetMapping("courses/edit/{id}")
    public String showEditCourseForm(@PathVariable("id") int id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Course Id:" + id));
        model.addAttribute("course", course);
        return "edit_courses";
    }

    @PostMapping("courses/update/{id}")
    public String updateStudent(@PathVariable("id") int id, Course course, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            return "courses";
        }

        courseRepository.save(course);
        model.addAttribute("course", courseRepository.findAll());
        return "courses";
    }
    @GetMapping("courses/new")
    public String showNewCourseForm(Course course)
    {
        return "new-courses";
    }

    @PostMapping("courses/save")
    public String addNewCourse(Course course, BindingResult result, Model model) {
         if (result.hasErrors()) {
          return "courses";
          }
        courseRepository.save(course);
        return "redirect:/courses";
    }
    /****************
        ends courses
    ***************/
}
