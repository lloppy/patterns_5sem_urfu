package ask.urfu.misc.patterns.fantasygame.userdecisions;

import ask.urfu.misc.patterns.fantasygame.modes.CreateCharacterMode;
import ask.urfu.misc.patterns.fantasygame.modes.GameModes;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.Collaborator;

public class SelectClassDecision extends ParametrizedDecision<SelectClassDecision> {

  private Collaborator collaborator;

  public SelectClassDecision(String parameter) {
    super(parameter, parameter);
  }

  public void setCollaborator(Collaborator collaborator) {
    this.collaborator = collaborator;
  }

  @Override
  public SelectClassDecision withParameter(String parameter) {
    return new SelectClassDecision(parameter);
  }

  @Override
  public void execute() {
    ((CreateCharacterMode) GameModes.CREATE_CHARACTER.get())
        .context()
        .chooseClass(parameter);
    if (collaborator != null) {
      collaborator.signalUpdate();
    }
  }

}
