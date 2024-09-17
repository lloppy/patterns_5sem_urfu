package ask.urfu.misc.patterns.fantasygame.modes;

import ask.urfu.misc.patterns.fantasygame.userinterface.menu.InitialMenu;

class InitialGameMode extends GameModeBase {

  InitialGameMode() {
    super(new InitialMenu());
  }

  @Override
  public void turn() {
    userInterface.draw();
    try {
      userInterface.awaitAndExecuteNextDecision();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

}
