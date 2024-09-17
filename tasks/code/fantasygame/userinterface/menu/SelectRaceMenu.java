package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.Collaborator;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter;
import ask.urfu.misc.patterns.fantasygame.userdecisions.SelectRaceDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import java.util.List;

public class SelectRaceMenu extends MenuBase implements Collaborator {

  private CreateCharacter manager;

  public SelectRaceMenu() {
    super(
        "select race",
        List.of(
            new InterfaceItem("H", new SelectRaceDecision("human")),
            new InterfaceItem("E", new SelectRaceDecision("elf")),
            new InterfaceItem("D", new SelectRaceDecision("dwarf"))
        ));
    items.forEach(i ->
        ((SelectRaceDecision) i.decision()).setCollaborator(this)
    );
  }

  @Override
  public CreateCharacter manager() {
    return manager;
  }

  @Override
  public void setManager(CreateCharacter manager) {
    this.manager = manager;
  }

  @Override
  public void cancelAction(String reason) {
    System.out.println("Action impossible: " + reason);
  }

}
