package ask.urfu.misc.patterns.fantasygame.userdecisions;

import ask.urfu.misc.patterns.fantasygame.Direction;
import ask.urfu.misc.patterns.fantasygame.FantasyGame;

// Паттерн Singleton
public enum UserDecision implements AdventurerDecision {

  //region Initial screen

  BEGIN_GAME("start adventuring", () -> FantasyGame.theGame().startAdventure()),
  CREATE_CHARACTER("create character", () -> FantasyGame.theGame().createCharacter()),
  END_CREATE_CHARACTER("character created", () -> FantasyGame.theGame().endCreateCharacter()),

  //endregion

  //region Main menu

  SAVE_GAME("save game", () -> FantasyGame.theGame().save()),

  LOAD_GAME("load game", () -> FantasyGame.theGame().load(0)), // todo saveId

  END_GAME("end game", () -> FantasyGame.theGame().endGame()),

  //endregion

  //region Wandering

  FORWARD("go forward", () -> FantasyGame.theGame().move(Direction.AHEAD)),
  LEFT("go left", () -> FantasyGame.theGame().move(Direction.LEFT)),
  RIGHT("go right", () -> FantasyGame.theGame().move(Direction.RIGHT)),

  RETREAT("retreat to previous location", () -> FantasyGame.theGame().move(Direction.BACK)),

  //endregion

  //region Combat

  ENTER_FIGHT("fight monsters", () -> FantasyGame.theGame().enterCombat()),
  CONTINUE_FIGHT("continue fight", () -> {
  })

  //endregion
  ;

  private final String description;

  private final Runnable action;

  UserDecision(String description, Runnable action) {
    this.description = description;
    this.action = action;
  }

  public String description() {
    return description;
  }

  @Override
  public void execute() {
    action.run();
  }

}
