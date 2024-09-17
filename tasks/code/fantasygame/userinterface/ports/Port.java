package ask.urfu.misc.patterns.fantasygame.userinterface.ports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Port implements ShowPort {

  PortsGrid portsGrid = new PortsGrid();
  List<String> titleContents = new ArrayList<>();


  @Override
  public PortContents contents() {
    Map<ShowPort, PortContents> portContentsMap = new HashMap<>();
    PortContents result = prepareEmptyResult(portContentsMap);
    result.drawBorder();
    renderGrid(portContentsMap, result);
    return result;
  }

  @Override
  public void write(String contents) {
    titleContents.add(contents);
  }

  @Override
  public void addSubport(int x, int y, ShowPort subport) {
    portsGrid.addPort(x, y, subport);
  }


  private PortContents prepareEmptyResult(Map<ShowPort, PortContents> portContentsMap) {
    int gridWidth = portsGrid.width();
    int gridHeight = portsGrid.height();
    // count result dimensions
    int maxContentsWidth = 0;
    int maxContentsHeight = 0;
    for (int y = 0; y < gridHeight; y++) {
      int lineContentsWidth = 0;
      int lineContentsHeight = 0;
      // one grid line
      for (int x = 0; x < gridWidth; x++) {
        ShowPort port = portsGrid.get(x, y);
        if (port != null) {
          PortContents contents = port.contents();
          portContentsMap.put(port, contents);
          lineContentsWidth += contents.width();
          lineContentsHeight = Math.max(lineContentsHeight, contents.height());
        }
        maxContentsWidth = Math.max(maxContentsWidth, lineContentsWidth);
      }
      maxContentsHeight += lineContentsHeight;
    }
    // prepare contents with counted size
    int contentsHeight = maxContentsHeight + 4;
    int contentsWidth = maxContentsWidth + 4;
    return PortContents.clean(contentsWidth, contentsHeight);
  }

  private void renderGrid(Map<ShowPort, PortContents> portContentsMap, PortContents result) {
    int gridWidth = portsGrid.width();
    int gridHeight = portsGrid.height();

    int xOffset = 2;
    int yOffset = 2;
    for (int y = 0; y < gridHeight; y++) {
      int maxHeight = 0;
      //one grid line
      for (int x = 0; x < gridWidth; x++) {
        ShowPort port = portsGrid.get(x, y);
        if (port != null) {
          PortContents portContents = portContentsMap.get(port);
          int portWidth = portContents.width();
          int portHeight = portContents.height();
          maxHeight = Math.max(maxHeight, portHeight);
          result.pasteWithOffset(portContents, xOffset, yOffset);
          xOffset += portWidth;
        }
      }
      xOffset = 2;
      yOffset += maxHeight;
    }
  }


}
