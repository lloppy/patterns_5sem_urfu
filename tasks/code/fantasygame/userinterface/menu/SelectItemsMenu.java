package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.Collaborator;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter;
import ask.urfu.misc.patterns.fantasygame.userdecisions.SelectItemDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import java.util.List;

public class SelectItemsMenu extends MenuBase implements Collaborator {

  private CreateCharacter manager;

  public SelectItemsMenu() {
    super(
        "select items",
        List.of(
            new InterfaceItem("SWORD", new SelectItemDecision("SWORD")),
            new InterfaceItem("BOW", new SelectItemDecision("BOW")),
            new InterfaceItem("STAFF", new SelectItemDecision("STAFF")),
            new InterfaceItem("SHIELD", new SelectItemDecision("SHIELD")),
            new InterfaceItem("BOOTS", new SelectItemDecision("BOOTS")),
            new InterfaceItem("NOTHING", new SelectItemDecision(null))
        ));
    items.forEach(i ->
        ((SelectItemDecision) i.decision()).setCollaborator(this)
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
