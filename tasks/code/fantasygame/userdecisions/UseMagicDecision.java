package ask.urfu.misc.patterns.fantasygame.userdecisions;

import ask.urfu.misc.patterns.fantasygame.FantasyGame;

public class UseMagicDecision extends ParametrizedDecision<UseMagicDecision> {

  public UseMagicDecision(String parameter) {
    super("use magic", parameter);
  }

  public UseMagicDecision() {
    this(null);
  }

  @Override
  public UseMagicDecision withParameter(String parameter) {
    return new UseMagicDecision(parameter);
  }

  @Override
  public void execute() {
    FantasyGame.theGame().castSpell(parameter);
  }
}
