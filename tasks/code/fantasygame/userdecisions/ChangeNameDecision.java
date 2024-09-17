package ask.urfu.misc.patterns.fantasygame.userdecisions;

import ask.urfu.misc.patterns.fantasygame.FantasyGame;

public class ChangeNameDecision extends ParametrizedDecision<ChangeNameDecision> {

  public ChangeNameDecision(String parameter) {
    super("choose hero name", parameter);
  }

  public ChangeNameDecision() {
    this(null);
  }

  @Override
  public void execute() {
    FantasyGame.theGame().changePlayerName(this.parameter);
  }

  @Override
  public ChangeNameDecision withParameter(String parameter) {
    return new ChangeNameDecision(parameter);
  }

}
