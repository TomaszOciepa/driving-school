package servlet.manager;

import date.dao.StudentDao;
import freemarker.TemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet( urlPatterns = "add-student-toinstructor")
public class AddStudentToInstructorServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddStudentToCourseServlet.class);
    private static final String TEMPLATE_NAME = "manager-instructor-add-student";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private StudentDao studentDao;
}
