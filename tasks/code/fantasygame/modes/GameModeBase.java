package ask.urfu.misc.patterns.fantasygame.modes;

import ask.urfu.misc.patterns.fantasygame.GameMode;
import ask.urfu.misc.patterns.library.userinterface.UserInterface;

public abstract class GameModeBase implements GameMode {

  protected final UserInterface userInterface;

  protected GameModeBase(UserInterface userInterface) {
    this.userInterface = userInterface;
  }

  @Override
  public UserInterface userInterface() {
    return userInterface;
  }

  @Override
  public void activate() {
    userInterface.activate();
  }

  @Override
  public void deactivate() {
    userInterface.deactivate();
  }

}
