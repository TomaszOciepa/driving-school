package date.dao;


import date.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    public int save(Student student) {
        entityManager.persist(student);
        return student.getStudentId();
    }

    public Student update(Student student) {
        return entityManager.merge(student);
    }

    public void delete(int id) {
        final Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
    }

    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> findAll() {
        final Query query = entityManager.createQuery("SELECT s FROM Student s");

        return (List<Student>) query.getResultList();
    }
}
