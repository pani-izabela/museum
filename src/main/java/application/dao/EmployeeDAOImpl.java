package application.dao;

import application.model.Client;
import application.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;


    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        try {
            return em.merge(employee);
        } catch (NoResultException e) {
            return null;
        }
    }
}
