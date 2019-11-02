import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectionInfo {
    @JsonProperty("language")
    private String language;

    @JsonProperty("isReliable")
    private String isReliable;

    @JsonProperty("confidence")
    private String confidence;
}
