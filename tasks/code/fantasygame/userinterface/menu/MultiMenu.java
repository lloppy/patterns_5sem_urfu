package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.library.game.UserDecision;
import ask.urfu.misc.patterns.library.userinterface.ComplexUserInterface;
import ask.urfu.misc.patterns.library.userinterface.UserInterface;
import ask.urfu.misc.patterns.library.userinterface.UserInterfaceImplementation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MultiMenu extends ComplexUserInterface {

  public MultiMenu(
      UserInterfaceImplementation implementation,
      UserInterface... components) {
    super(implementation, Arrays.asList(components));
  }

  @Override
  public void draw() {
    components.forEach(UserInterface::draw);
  }

  @Override
  public void activate() {
    super.activate();
    components.forEach(UserInterface::activate);
  }

  @Override
  public void deactivate() {
    super.deactivate();
    components.forEach(UserInterface::deactivate);
  }

  @Override
  protected UserDecision compose(List<UserDecision> decisions) {
    if (decisions.isEmpty()) {
      return null;
    } else if (decisions.size() == 1) {
      return decisions.get(0);
    } else {
      return null;
    }
  }

  @Override
  public UserDecision awaitNextDecision() {
    Optional<UserDecision> nextDecision;
    do {
      nextDecision = getNextDecision();
    } while (nextDecision.isEmpty());
    return nextDecision.get();
  }

}
