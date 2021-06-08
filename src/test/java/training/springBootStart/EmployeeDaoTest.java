package training.springBootStart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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


}
