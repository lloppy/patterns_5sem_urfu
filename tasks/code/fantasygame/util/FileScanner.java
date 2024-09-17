package ask.urfu.misc.patterns.fantasygame.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FileScanner {

  private static final String LINE_SEPARATOR = "---";
  private static final String KEY_SEPARATOR = ":";
  private final Path baseFilePath;

  public FileScanner(Path baseFilePath) {
    this.baseFilePath = baseFilePath;
  }

  public Map<String, String> scanFile(String file) {
    Path path = baseFilePath.resolve(file);
    try (var input = Files.newInputStream(path);
        var scanner = new Scanner(input)) {
      Map<String, String> result = new LinkedHashMap<>();
      int count = 1;
      while (scanner.hasNext()) {
        String line = scanner.nextLine().trim();
        // skipping empty lines and separator lines
        if (!line.isEmpty() && !line.contains(LINE_SEPARATOR)) {
          String[] split = line.split(KEY_SEPARATOR);
          if (split.length == 1) {
            // if no separator found, what was read is a value, key is just a number
            result.put(String.valueOf(count++), split[0].trim());
          } else {
            result.put(split[0].trim().toUpperCase(), split[1].trim());
          }
        }
      }
      return result;
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public Map<String, String> scanFile(Path file) {
    return scanFile(file.toString());
  }

}
