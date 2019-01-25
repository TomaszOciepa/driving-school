package authentication;

import date.dao.ManagerDao;
import date.model.Manager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CheckPassword {

    @Inject
    private ManagerDao managerDao;

    public boolean checkManagerPassword(String email, String password) {

        List<Manager> managersList = managerDao.findAll();
        List<Manager> managersEmail = managersList.stream()
                .filter(m -> m.getManagerEmail().equals(email))
                .collect(Collectors.toList());

        if (managersEmail.get(0).getManagerEmail().equals(email) && managersEmail.get(0).getManagerPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
