import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
public class RestLanguageDetecionService {

    private final RestTemplate restTemplate;
    private static final String API_KEY = "e06fe0354e04e13db2869c61a412622c";
    private final static String URI = "https://ws.detectlanguage.com";

    public RestLanguageDetecionService() {
        this.restTemplate = new RestTemplateBuilder().rootUri(URI)
                .build();
    }

    public void processAll(final List<String> textToDetect) {
        textToDetect.forEach(this::processText);
    }

    private void processText(final String textToDetect) {
        final HttpEntity<String> request = createHttpEntity();
        try {
            final ResponseEntity<LanguageData> response = restTemplate.postForEntity("/0.2/detect?q=" + textToDetect, request, LanguageData.class);
            printFormattedResult(response);
        } catch (final RestClientException exp) {
            log.error("Failed to successfully detect language");
        }
    }

    private HttpEntity<String> createHttpEntity() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(API_KEY);
        httpHeaders.add(HttpHeaders.ACCEPT, APPLICATION_JSON.toString());
        return new HttpEntity<>(httpHeaders);
    }

    private void printFormattedResult(ResponseEntity<LanguageData> response) {
        Objects.requireNonNull(response.getBody())
                .getDetections()
                .getDetections()
                .forEach(detection -> System.out.println("Language is " + detection.getLanguage() + " and is " + detection.getIsReliable()));
    }
}

