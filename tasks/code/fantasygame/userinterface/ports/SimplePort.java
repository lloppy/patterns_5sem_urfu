package ask.urfu.misc.patterns.fantasygame.userinterface.ports;

import java.util.ArrayList;
import java.util.List;

public class SimplePort implements ShowPort {

  List<String> contents = new ArrayList<>();

  @Override
  public PortContents contents() {
    int maxLineLength = contents.stream().map(String::length).reduce(0, Math::max);
    int linesTotal = contents.size();
    if (linesTotal == 0) {
      return PortContents.clean(1, 1);
    }
    int height = linesTotal + 4;
    int width = maxLineLength + 4;
    PortContents result = PortContents.clean(width, height);
    result.drawBorder();
    // render contents
    result.write(2, 2, contents);
    return result;
  }

  @Override
  public void write(String newLine) {
    contents.add(newLine);
  }

  @Override
  public void addSubport(int x, int y, ShowPort playerPort) {
    throw new IllegalStateException("Simple port can't have subports.");
  }

}
