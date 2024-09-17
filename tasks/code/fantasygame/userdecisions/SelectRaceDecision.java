package ask.urfu.misc.patterns.fantasygame.userdecisions;

import ask.urfu.misc.patterns.fantasygame.modes.CreateCharacterMode;
import ask.urfu.misc.patterns.fantasygame.modes.GameModes;
import ask.urfu.misc.patterns.fantasygame.rules.charactercreation.Collaborator;

public class SelectRaceDecision extends ParametrizedDecision<SelectRaceDecision> {

  private Collaborator collaborator;

  public SelectRaceDecision(String parameter) {
    super(parameter, parameter);
  }

  public void setCollaborator(Collaborator collaborator) {
    this.collaborator = collaborator;
  }

  @Override
  public SelectRaceDecision withParameter(String parameter) {
    return new SelectRaceDecision(parameter);
  }

  @Override
  public void execute() {
    ((CreateCharacterMode) GameModes.CREATE_CHARACTER.get())
        .context()
        .chooseRace(parameter);
    if (collaborator != null) {
      collaborator.signalUpdate();
    }
  }

}
