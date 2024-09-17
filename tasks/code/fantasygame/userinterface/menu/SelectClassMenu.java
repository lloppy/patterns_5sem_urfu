package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.Collaborator;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter;
import ask.urfu.misc.patterns.fantasygame.userdecisions.SelectClassDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import java.util.List;

public class SelectClassMenu extends MenuBase implements Collaborator {

  private CreateCharacter manager;

  public SelectClassMenu() {
    super(
        "select character class",
        List.of(
            new InterfaceItem("W", new SelectClassDecision("warrior")),
            new InterfaceItem("M", new SelectClassDecision("mage")),
            new InterfaceItem("R", new SelectClassDecision("rogue"))
        ));
    items.forEach(i ->
        ((SelectClassDecision) i.decision()).setCollaborator(this)
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
