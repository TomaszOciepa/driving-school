package servlet.manager;

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

@WebServlet(urlPatterns = "/delete-manager")
public class DeleteManagerServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteManagerServlet.class);
    private static final String TEMPLATE_NAME = "manager-manager-delete";

    @Inject
    private TemplateProvider templateProvider;
    @Inject
    private ManagerDao managerDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        final PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(true);

        Map<String, Object> model = new HashMap<>();

        Manager managerSession = (Manager) session.getAttribute("user");
        model.put("user", managerSession);

        int id = Integer.parseInt(req.getParameter("id"));
        managerDao.delete(id);

        model.put("SuccesUpdate", "Manager account deleted");
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
