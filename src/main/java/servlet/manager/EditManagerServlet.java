package servlet.manager;

import authentication.CheckExists;
import date.dao.ManagerDao;
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

@WebServlet(urlPatterns = "/edit-manager")
public class EditManagerServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(EditManagerServlet.class);
    private static final String TEMPLATE_NAME = "manager-manager-edit";
    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private ManagerDao managerDao;
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
        Manager editedManager = managerDao.findById(id);
        session.setAttribute("editedManager", editedManager);
        model.put("manager", editedManager);

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
        Manager editedManager = (Manager) session.getAttribute("editedManager");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastname");
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        if (checkExists.checkManagerExists(email)) {

            if (editedManager.getManagerEmail().equals(email)) {
                updateManager(editedManager, email, name, lastName);
                LOG.info("Manager has bean update");
                model.put("manager", editedManager);
                model.put("SuccesUpdate", "Manager has bean update");
                loadTemplate(writer, model, template);
            } else {
                LOG.info("Email is already busy");
                model.put("manager", editedManager);
                model.put("FailedUpdate", "Email is already busy. Try again.");
                loadTemplate(writer, model, template);
            }
        } else {
            updateManager(editedManager, email, name, lastName);
            LOG.info("Manager has bean update");
            model.put("manager", editedManager);
            model.put("SuccesUpdate", "Manager has bean update");
            loadTemplate(writer, model, template);
        }

    }

    private void updateManager(Manager editedManager, String email, String name, String lastName) {
        editedManager.setManagerEmail(email);
        editedManager.setManagerName(name);
        editedManager.setManagerLastname(lastName);

        managerDao.update(editedManager);
    }

    private void loadTemplate(PrintWriter writer, Map<String, Object> model, Template template) throws IOException {
        try {
            LOG.info("Load template manager-manager-edit");
            template.process(model, writer);
        } catch (TemplateException e) {
            LOG.warn("Failed load template manager-manager-edit");
        }
    }
}
