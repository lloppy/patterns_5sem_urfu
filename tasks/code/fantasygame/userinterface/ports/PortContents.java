package ask.urfu.misc.patterns.fantasygame.userinterface.ports;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class PortContents {

  public static final char VERTICAL_BORDER = '|';
  public static final char HORIZONTAL_BORDER = 0x2014; //longdash
  public static final char BLANK = ' ';

  private final char[][] contents;

  public PortContents(char[][] contents) {
    this.contents = contents;
  }

  public static PortContents clean(int width, int height) {
    PortContents result = new PortContents(new char[height][width]);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        result.write(x, y, BLANK);
      }
    }
    return result;
  }

  public void drawBorder() {
    int height = height();
    int width = width();
    for (int y = 0; y < height; y++) {
      write(0, y, VERTICAL_BORDER);
      write(width - 1, y, VERTICAL_BORDER);
    }
    for (int x = 1; x < width - 1; x++) {
      write(x, 0, HORIZONTAL_BORDER);
      write(x, height - 1, HORIZONTAL_BORDER);
    }
  }

  public int height() {
    return contents.length;
  }

  public int width() {
    return contents[0].length;
  }

  public char get(int x, int y) {
    return contents[y][x];
  }

  public void write(int x, int y, char symbol) {
    contents[y][x] = symbol;
  }

  public void write(int x, int y, String line) {
    int length = line.length();
    for (int i = 0; i < length; i++) {
      contents[y][x + i] = line.charAt(i);
    }
  }

  public void write(int x, int y, Collection<String> lines) {
    for (String line : lines) {
      write(x, y, line);
      y++;
    }
  }

  public void pasteWithOffset(PortContents source, int xOffset, int yOffset) {
    int width = source.width();
    int height = source.height();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        write(x + xOffset, y + yOffset, source.get(x, y));
      }
    }
  }

  public Stream<String> lines() {
    return Arrays.stream(contents)
        .map(String::valueOf);
  }

}
