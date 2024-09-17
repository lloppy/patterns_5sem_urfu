package ask.urfu.misc.patterns.fantasygame.userdecisions;

import ask.urfu.misc.patterns.fantasygame.modes.CreateCharacterMode;
import ask.urfu.misc.patterns.fantasygame.modes.GameModes;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.Collaborator;

public class SelectItemDecision extends ParametrizedDecision<SelectItemDecision> {

  private Collaborator collaborator;

  public SelectItemDecision(String parameter) {
    super(parameter, parameter);
  }

  public void setCollaborator(Collaborator collaborator) {
    this.collaborator = collaborator;
  }

  @Override
  public SelectItemDecision withParameter(String parameter) {
    return new SelectItemDecision(parameter);
  }

  @Override
  public void execute() {
    ((CreateCharacterMode) GameModes.CREATE_CHARACTER.get())
        .context()
        .chooseItem(parameter);
    if (collaborator != null) {
      collaborator.signalUpdate();
    }
  }

}
