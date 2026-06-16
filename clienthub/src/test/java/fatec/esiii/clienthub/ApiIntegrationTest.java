package fatec.esiii.clienthub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testConsultarHospedes() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/hospedes", String.class);
        System.out.println("RESPONSE STATUS: " + response.getStatusCode());
        System.out.println("RESPONSE BODY: " + response.getBody());
        if(response.getStatusCode().is5xxServerError()) {
            throw new RuntimeException("API ERRO: " + response.getBody());
        }
    }
}
