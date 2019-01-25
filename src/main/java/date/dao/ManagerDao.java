package date.dao;

import date.model.Manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ManagerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public int save(Manager manager) {
        entityManager.persist(manager);
        return manager.getManagerId();
    }

    public Manager update(Manager manager) {
        return entityManager.merge(manager);
    }

    public void delete(int id) {
        final Manager manager = entityManager.find(Manager.class, id);
        if (manager != null) {
            entityManager.remove(manager);
        }
    }

    public Manager findById(int id) {
        return entityManager.find(Manager.class, id);
    }

    public List<Manager> findAll() {
        final Query query = entityManager.createQuery("SELECT m FROM Manager m");

        return (List<Manager>) query.getResultList();
    }


}
