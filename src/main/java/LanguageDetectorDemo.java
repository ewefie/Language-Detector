import java.io.IOException;
import java.util.List;

public class LanguageDetectorDemo {
    public static void main(String[] args) throws IOException {
        final DirectoryFilesReader reader = new DirectoryFilesReader();
        final List<String> filesContent = reader.readAllFilesFromDirectory(args[0]);

        LanguageDetectingService service = new LanguageDetectingService();
        service.process(filesContent);

        RestLanguageDetecionService restService = new RestLanguageDetecionService();
        restService.processAll(filesContent);
    }
}
