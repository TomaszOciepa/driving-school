package authentication;

import date.dao.ManagerDao;
import date.model.Manager;

import java.util.List;
import java.util.stream.Collectors;

public class DownloadUserToDatabase {

    ManagerDao managerDao = new ManagerDao();


    public Manager downloadManager(String email) {

        List<Manager> managersList = managerDao.findAll();
        List<Manager> managersEmail = managersList.stream()
                .filter(m -> m.getManagerEmail().equals(email))
                .collect(Collectors.toList());

        Manager manager = new Manager();
        return manager = managersEmail.get(0);
    }
}
