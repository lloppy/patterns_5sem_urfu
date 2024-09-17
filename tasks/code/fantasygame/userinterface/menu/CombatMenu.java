package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import java.util.ArrayList;
import java.util.List;

public class CombatMenu extends MenuBase {

  public CombatMenu() {
    super("Combat", keymap());
  }

  private static List<InterfaceItem> keymap() {
    List<InterfaceItem> map = new ArrayList<>();
    map.add(new InterfaceItem("R", UserDecision.RETREAT));
    map.add(new InterfaceItem("F", UserDecision.ENTER_FIGHT));
    return map;
  }

}
