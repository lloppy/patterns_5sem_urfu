package ask.urfu.misc.patterns.fantasygame.userdecisions;

public abstract class ParametrizedDecision<T extends ParametrizedDecision<T>>
    implements AdventurerDecision {

  protected final String parameter;
  private final String description;

  protected ParametrizedDecision(String description, String parameter) {
    this.description = description;
    this.parameter = parameter;
  }

  public String description() {
    return description;
  }

  public abstract T withParameter(String parameter);

}
