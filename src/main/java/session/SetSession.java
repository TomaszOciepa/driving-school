package session;


import date.model.Instructor;
import date.model.Manager;
import date.model.Student;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
public class SetSession {

    private static final String SESSION_ATTRIBUTE_EMAIL = "userEmail";
    private static final String SESSION_ATTRIBUTE_NAME = "userName";
    private static final String SESSION_ATTRIBUTE_LASTNAME = "userLastname";

    public void manager(HttpSession session, Manager manager){
        session.setAttribute(SESSION_ATTRIBUTE_EMAIL, manager.getManagerEmail());
        session.setAttribute(SESSION_ATTRIBUTE_NAME, manager.getManagerName());
        session.setAttribute(SESSION_ATTRIBUTE_LASTNAME, manager.getManagerLastname());
    }

    public void instructor(HttpSession session, Instructor instructor){
        session.setAttribute(SESSION_ATTRIBUTE_EMAIL, instructor.getInstructorEmail());
        session.setAttribute(SESSION_ATTRIBUTE_NAME, instructor.getInstructorName());
        session.setAttribute(SESSION_ATTRIBUTE_LASTNAME, instructor.getInstructorLastname());
    }

    public void student(HttpSession session, Student student){
        session.setAttribute(SESSION_ATTRIBUTE_EMAIL, student.getStudentEmail());
        session.setAttribute(SESSION_ATTRIBUTE_NAME, student.getStudentName());
        session.setAttribute(SESSION_ATTRIBUTE_LASTNAME, student.getStudentLastname());
    }
}
