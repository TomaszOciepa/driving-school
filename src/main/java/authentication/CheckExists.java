package authentication;

import date.dao.ManagerDao;
import date.model.Manager;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


public class CheckExists {
    @Inject
    private ManagerDao managerDao;

    public boolean checkManagerExists(String email){

        List<Manager> managersList = managerDao.findAll();
        List<Manager> managersEmail = managersList.stream()
                .filter(m -> m.getManagerEmail() == email)
                .collect(Collectors.toList());

        if(!managersEmail.isEmpty()){
            return true;
        }else {
            return false;
        }

    }



}