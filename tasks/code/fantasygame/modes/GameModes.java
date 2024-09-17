package ask.urfu.misc.patterns.fantasygame.modes;

import ask.urfu.misc.patterns.fantasygame.GameMode;

// Паттерн Singleton
public enum GameModes {

  INITIAL(new InitialGameMode()),
  CREATE_CHARACTER(new CreateCharacterMode()),
  WANDERING(new WanderingGameMode()),
  COMBAT(new CombatMode());

  private final GameMode mode;

  GameModes(GameMode mode) {
    this.mode = mode;
  }

  public GameMode get() {
    return mode;
  }

}
