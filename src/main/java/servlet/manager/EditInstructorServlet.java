package servlet.manager;

import authentication.CheckExists;
import date.dao.InstructorDao;
import date.model.Instructor;
import date.model.Manager;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/edit-instructor")
public class EditInstructorServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(EditInstructorServlet.class);
    private static final String TEMPLATE_NAME = "manager-instructor-edit";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private InstructorDao instructorDao;
    @Inject
    private CheckExists checkExists;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);

        int id = Integer.parseInt(req.getParameter("id"));
        Instructor editedInstructor = instructorDao.findById(id);

        session.setAttribute("editedInstructor", editedInstructor);
        model.put("instructor", editedInstructor);
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        loadTemplate(writer, model, template);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);

        Instructor editedInstructor = (Instructor) session.getAttribute("editedInstructor");

        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String street = req.getParameter("street");
        String city = req.getParameter("city");

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        if (checkExists.checkInstructorExists(email)) {

            if (editedInstructor.getInstructorEmail().equals(email)){
                updateInstructor(editedInstructor, email, name, lastName, phone, street, city);
                LOG.info("Instructor has bean update");
                model.put("instructor", editedInstructor);
                model.put("SuccesUpdate", "Instructor has bean update");
                loadTemplate(writer, model, template);
            }else {
                LOG.info("Email is already busy");
                model.put("instructor", editedInstructor);
                model.put("FailedUpdate", "Email is already busy. Try again.");
                loadTemplate(writer, model, template);
            }
        } else {
            updateInstructor(editedInstructor, email, name, lastName, phone, street, city);
            LOG.info("Instructor has bean update");
            model.put("instructor", editedInstructor);
            model.put("SuccesUpdate", "Instructor has bean update");
            loadTemplate(writer, model, template);
        }
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-new-manager");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-new-manager");
        }
    }

    private void updateInstructor(Instructor editedInstructor, String email, String name, String lastName, String phone, String street, String city) {
        editedInstructor.setInstructorEmail(email);
        editedInstructor.setInstructorName(name);
        editedInstructor.setInstructorLastname(lastName);
        editedInstructor.setInstructorPhone(phone);
        editedInstructor.setInstructorStreet(street);
        editedInstructor.setInstructorCity(city);

        instructorDao.update(editedInstructor);
    }
}
