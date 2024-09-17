package ask.urfu.misc.patterns.fantasygame.modes;

import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter.Context;
import ask.urfu.misc.patterns.fantasygame.service.CreateCharacterService;
import ask.urfu.misc.patterns.fantasygame.userinterface.menu.CreateCharacterMenu;

public class CreateCharacterMode extends GameModeBase {

  CreateCharacter createCharacter;

  CreateCharacterService service;

  public CreateCharacterMode() {
    super(new CreateCharacterMenu());
    createCharacter = ((CreateCharacterMenu) userInterface()).getManager();
    service = new CreateCharacterService();
  }

  @Override
  public void turn() {
    System.out.println(context());
    userInterface().draw();
    try {
      userInterface().awaitAndExecuteNextDecision();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public Context context() {
    return createCharacter.context();
  }

  public Adventurer doCreate() {
    return service.create(context());
  }

}
