package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.fantasygame.userdecisions.ParametrizedDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import ask.urfu.misc.patterns.fantasygame.userinterface.KeyboardInterface;
import ask.urfu.misc.patterns.library.game.UserDecision;
import ask.urfu.misc.patterns.library.userinterface.UserMenu;
import java.util.List;
import java.util.function.Predicate;

public class MenuBase extends UserMenu {

  protected final List<InterfaceItem> items;

  public MenuBase(String name, List<InterfaceItem> items) {
    super(new KeyboardInterface(name, items), validationPredicate(items));
    this.items = items;
  }

  private static Predicate<UserDecision> validationPredicate(List<InterfaceItem> items) {
    return d -> items.stream()
        .map(InterfaceItem::decision)
        .anyMatch(itemDecision -> {
          if (itemDecision instanceof ParametrizedDecision<?> parametrized) {
            return d.getClass().equals(parametrized.getClass());
          } else {
            return d.equals(itemDecision);
          }
        });
  }

}
