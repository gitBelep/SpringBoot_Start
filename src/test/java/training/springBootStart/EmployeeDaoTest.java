package training.springBootStart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeDaoTest {

    @Autowired
    private EmployeeDao dao;


    @Test
    void testSaveAndFindEmployeeByName() {
        Employee eA = new Employee("Alma");
        Employee eB = new Employee("Benő");
        dao.saveEmployee(eA);
        dao.saveEmployee(eB);

        assertEquals(2L, dao.findEmployeeByName("Benő").getId());
        assertEquals("Alma", dao.listEmployees().get(0).getName());
    }

    @Test
    void testSumNameLengthByStartLetter() {
        Employee eA = new Employee("Altamírád");
        Employee eB = new Employee("Benő");
        Employee eC = new Employee("Bud");
        dao.saveEmployee(eA);
        dao.saveEmployee(eB);
        dao.saveEmployee(eC);

        List<Integer> ints = dao.listEmployeesNamesLengthWithStartingLetter("B"); //or "b" is the same
        assertEquals(3,  ints.get(0));        //"Bud"
        assertEquals(5,  ints.get(1));        //length of "Benő" is 5, because of 'ő'
        assertEquals(0,  dao.listEmployeesNamesLengthWithStartingLetter("Q").size());  //empty List
    }

    @Test
    void testFindNameByPart() {
        Employee eA = new Employee("Altamírád");
        Employee eB = new Employee("Benő");
        Employee eC = new Employee("Bud");
        dao.saveEmployee(eA);
        dao.saveEmployee(eB);
        dao.saveEmployee(eC);

        assertEquals("Bud", dao.listNamesByPart("d").get(0));  //Bud, Altamírád
        assertEquals("Altamírád", dao.listNamesByPart("tamír").get(0));
        assertEquals(2, dao.listNamesByPart("B").size());
        assertEquals(1, dao.listNamesByPart("Bu").size());
        assertEquals(0, dao.listNamesByPart("Alx").size());
    }

    @Test
    void testCountNamesWithStartLetter() {
        Employee eA = new Employee("Altamírád");
        Employee eB = new Employee("Benő");
        Employee eC = new Employee("Bud");
        dao.saveEmployee(eA);
        dao.saveEmployee(eB);
        dao.saveEmployee(eC);

        assertEquals(2,  dao.countEmployeeNamesWithStartingLetter("B"));
        assertEquals(0,  dao.countEmployeeNamesWithStartingLetter("Q"));
    }

}
