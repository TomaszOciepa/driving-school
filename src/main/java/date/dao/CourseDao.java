package date.dao;

import date.model.Course;
import date.model.Instructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public int save(Course course) {
        entityManager.persist(course);
        return course.getCourseId();
    }

    public Course update(Course course) {
        return entityManager.merge(course);
    }

    public void delete(int id) {
        final Course course = entityManager.find(Course.class, id);
        if (course != null) {
            entityManager.remove(course);
        }
    }

    public Course findById(int id) {
        return entityManager.find(Course.class, id);
    }

    public List<Course> findAll() {
        final Query query = entityManager.createQuery("SELECT i FROM Course i");

        return (List<Course>) query.getResultList();
    }


}
