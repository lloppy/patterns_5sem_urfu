package ask.urfu.misc.patterns.fantasygame;

import ask.urfu.misc.patterns.library.userinterface.UserInterface;

public interface GameMode {

  default String name() {
    return this.getClass().getSimpleName();
  }

  UserInterface userInterface();

  void turn();

  void activate();

  void deactivate();

}
