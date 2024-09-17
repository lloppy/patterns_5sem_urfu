package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import static ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision.BEGIN_GAME;
import static ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision.CREATE_CHARACTER;

import ask.urfu.misc.patterns.fantasygame.userdecisions.ChangeNameDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import ask.urfu.misc.patterns.fantasygame.userinterface.ParametrizedInterfaceItem;
import java.util.ArrayList;
import java.util.List;

public class InitialMenu extends MenuBase {

  public InitialMenu() {
    super("Prepare to adventure", keyMap());
  }

  private static List<InterfaceItem> keyMap() {
    List<InterfaceItem> map = new ArrayList<>();
    map.add(
        new ParametrizedInterfaceItem("N <name>", "N\\s*(?<p>.+)\\s*", new ChangeNameDecision()));
    map.add(new InterfaceItem("C", CREATE_CHARACTER));
    map.add(new InterfaceItem("S", BEGIN_GAME));
    return map;
  }

}
