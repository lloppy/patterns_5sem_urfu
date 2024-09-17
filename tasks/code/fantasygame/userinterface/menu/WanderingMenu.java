package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import static ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision.ENTER_FIGHT;
import static ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision.FORWARD;
import static ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision.LEFT;
import static ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision.RETREAT;
import static ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision.RIGHT;

import ask.urfu.misc.patterns.fantasygame.userdecisions.UseMagicDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import ask.urfu.misc.patterns.fantasygame.userinterface.ParametrizedInterfaceItem;
import java.util.ArrayList;
import java.util.List;

public class WanderingMenu extends MenuBase {

  public WanderingMenu() {
    super("Walk the dungeon", keyMap());
  }

  private static List<InterfaceItem> keyMap() {
    List<InterfaceItem> map = new ArrayList<>();
    map.add(new InterfaceItem("W", FORWARD));
    map.add(new InterfaceItem("A", LEFT));
    map.add(new InterfaceItem("S", RETREAT));
    map.add(new InterfaceItem("D", RIGHT));
    map.add(new InterfaceItem("F", ENTER_FIGHT));
    map.add(
        new ParametrizedInterfaceItem("M <spell>", "M\\s*(?<p>.+)\\s*", new UseMagicDecision()));
    return map;
  }

}
