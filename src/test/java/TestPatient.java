import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.views.patient.PatientSetting1;
import com.glucoclock.views.patient.PatientSignUp1;
import com.vaadin.flow.component.select.Select;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPatient {
    PatientService testService;

    @Before
    public void SetUpData(PatientService testService) {
        this.testService = testService;
        testService.bulkcreate();
    }

    @Test
    public void testHello() {

        PatientSetting1 test = new PatientSetting1(testService);
        Assertions.assertEquals("Hello World", test.hello());
    }

    @Test
    public void testAnotherHello() {
        PatientSignUp1 test = new PatientSignUp1();
        Assertions.assertEquals("Hello World",test.hello());
    }
}
