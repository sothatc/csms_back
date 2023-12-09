package itfact.entp.enterprise.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CorRegNumberService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "";

    @Value("${apikey.clientkey}")
    private String apiKey;

    public CorRegNumberService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBusinessRegistrationInfo(String registrationNumber) {
        // API 호출을 위한 URL 조합
        String url = apiUrl + "?number=" + registrationNumber + "&apiKey=" + apiKey;
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@url = " + url);



        // API 호출 결과 반환
        return url;
    }
}
