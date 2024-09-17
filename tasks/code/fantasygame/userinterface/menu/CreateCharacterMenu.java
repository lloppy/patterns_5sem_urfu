package ask.urfu.misc.patterns.fantasygame.userinterface.menu;

import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.CreateCharacter;
import ask.urfu.misc.patterns.fantasygame.userdecisions.UserDecision;
import ask.urfu.misc.patterns.fantasygame.userinterface.InterfaceItem;
import ask.urfu.misc.patterns.fantasygame.userinterface.KeyboardInterface;
import ask.urfu.misc.patterns.library.userinterface.UserInterface;
import java.util.List;

public class CreateCharacterMenu extends MultiMenu {

  private final CreateCharacter manager;
  SelectClassMenu selectClass;
  SelectRaceMenu selectRace;
  SelectItemsMenu selectItems;


  public CreateCharacterMenu() {
    super(new KeyboardInterface("create character"), menuItems());
    selectClass = (SelectClassMenu) components.get(0);
    selectRace = (SelectRaceMenu) components.get(1);
    selectItems = (SelectItemsMenu) components.get(2);
    this.manager = new CreateCharacter(selectRace, selectClass, selectItems);
    selectClass.setManager(this.manager);
    selectRace.setManager(this.manager);
    selectItems.setManager(this.manager);
  }

  private static UserInterface[] menuItems() {
    return new UserInterface[]{
        new SelectClassMenu(),
        new SelectRaceMenu(),
        new SelectItemsMenu(),
        new MenuBase("Done", List.of(new InterfaceItem("C", UserDecision.END_CREATE_CHARACTER)))
    };
  }

  public CreateCharacter getManager() {
    return manager;
  }

}
