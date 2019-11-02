import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

import java.util.List;

public class LanguageDetectingService {

    private static final String API_KEY = "e06fe0354e04e13db2869c61a412622c";


    public void process(final List<String> textToDetect) {
        DetectLanguage.apiKey = API_KEY;
        textToDetect.forEach(item -> {
            try {
                printFormattedResult(item, DetectLanguage.detect(item));
            } catch (APIError apiError) {
                apiError.printStackTrace();
            }
        });
    }

    private void printFormattedResult(final String textToDetect, final List<Result> results) {
        results.forEach(result -> System.out.println("Given text:\n " + textToDetect + "\nis a " + result.confidence + " percent " + result.language + " language."));
    }


}
