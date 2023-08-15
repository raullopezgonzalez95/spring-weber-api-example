import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor
public class Webex {
    @NonNull
    private final RestTemplate restTemplate;

    public void messageToWebex(String tittle, String message) {
        String url = "https://webexapis.com/v1/messages";
        String roomId = "04a432f0-3adc-11ee-9331-57a0f2d29a84";
        String token = "MWFmNTQ4NDMtZWI4Ny00OGRkLWE3OGUtMWI3NGZlNjAzYzcxM2U4MmQ5N2QtZTM0_PF84_b87ebf58-8b12-418f-bed3-bd7584e303eb";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", null);
        body.add("roomId", roomId);
        body.add("markdown", "### " + tittle + " \n ``` json \n" + message);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        try {
            restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    String.class
            );
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
