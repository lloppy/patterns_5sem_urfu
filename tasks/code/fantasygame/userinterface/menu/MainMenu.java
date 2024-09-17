package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends MenuBase {

  public MainMenu() {
    super("Game options", keyMap());
  }

  private static List<InterfaceItem> keyMap() {
    List<InterfaceItem> map = new ArrayList<>();
    map.add(new InterfaceItem("SAVE", UserDecision.SAVE_GAME));
    map.add(new InterfaceItem("LOAD", UserDecision.LOAD_GAME));
    map.add(new InterfaceItem("QUIT", UserDecision.END_GAME));
    return map;
  }

}
