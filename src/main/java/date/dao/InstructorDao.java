package date.dao;

import date.model.Instructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class InstructorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public int save(Instructor instructor) {
        entityManager.persist(instructor);
        return instructor.getInstructorId();
    }

    public Instructor update(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    public void delete(int id) {
        final Instructor instructor = entityManager.find(Instructor.class, id);
        if (instructor != null) {
            entityManager.remove(instructor);
        }
    }

    public Instructor findById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    public List<Instructor> findAll() {
        final Query query = entityManager.createQuery("SELECT i FROM Instructor i");

        return (List<Instructor>) query.getResultList();
    }
}
