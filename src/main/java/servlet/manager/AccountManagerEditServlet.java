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

@WebServlet(urlPatterns = "/my-account-edit")
public class AccountManagerEditServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AccountManagerEditServlet.class);
    private static final String TEMPLATE_NAME = "manager-my-account-edit";

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


        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password =req.getParameter("password");
        String name =req.getParameter("name");
        String lastName =req.getParameter("lastname");



    }

    private boolean checkExistsEmail(String email){
        if (checkExists.checkManagerExists(email) || checkExists.checkInstructorExists(email) || checkExists.checkStudentExists(email)){
            return false;
        } else{
            return true;
        }
    }
}
