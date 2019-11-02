import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DirectoryFilesReader {
    private static final String TXT_EXTENSION = "txt";

    public List<String> readAllFilesFromDirectory(final String dirPath) throws IOException {
        return Files.list(Paths.get(dirPath))
                .filter(path -> path
                        .getFileName()
                        .toString()
                        .endsWith(TXT_EXTENSION))
                .map(this::readFileContentFromPath)
                .collect(Collectors.toList());
    }

    private String readFileContentFromPath(final Path path) {
        try {
            return Files.readString(path);
        } catch (final IOException exp) {
            throw new CannotReadFileContentException("Failed to read file content", exp);
        }
    }
}
