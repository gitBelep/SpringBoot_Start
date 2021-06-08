package training.springBootStart;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    public Employee findEmployeeByName(String name) {
        return entityManager.createQuery(
                "select e from Employee e where e.name = :name", Employee.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    public List<Employee> listEmployees(){
        return entityManager.createQuery(
                "select e from Employee e order by e.name", Employee.class)
                .getResultList();
    }

    public List<Integer> listEmployeesNamesLengthWithStartingLetter(String letter) {
        return entityManager.createQuery(
                "SELECT LENGTH(e.name) FROM Employee e WHERE SUBSTRING(e.name, 1, 1) = :letter ORDER BY e.name DESC", Integer.class)
                .setParameter("letter", letter)
                .getResultList();
    }

    public List<String> listNamesByPart(String part) {
        return entityManager.createQuery(
                "SELECT e.name FROM Employee e WHERE LOCATE( :letter, e.name, 1) >= 1 ORDER BY e.name DESC", String.class)
                .setParameter("letter", part)
                .getResultList();
    }

    public long countEmployeeNamesWithStartingLetter(String letter) {
        return entityManager.createQuery(
                "SELECT COUNT(e.name) FROM Employee e WHERE SUBSTRING(e.name, 1, 1) = :letter", Long.class)
                .setParameter("letter", letter)
                .getSingleResult();
    }

}
