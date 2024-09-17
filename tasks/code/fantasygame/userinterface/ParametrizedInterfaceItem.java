package ask.urfu.misc.patterns.fantasygame.userinterface;

import ask.urfu.misc.patterns.fantasygame.userdecisions.AdventurerDecision;
import ask.urfu.misc.patterns.fantasygame.userdecisions.ParametrizedDecision;
import java.util.regex.Matcher;

public class ParametrizedInterfaceItem extends InterfaceItem {

  /**
   * @param value   what to show in user interface
   * @param pattern how to parse user input MUST contain exactly one a capturing group
   */
  public ParametrizedInterfaceItem(String value, String pattern, ParametrizedDecision<?> decision) {
    super(value, pattern, decision);
  }

  @Override
  public AdventurerDecision decision(String userInput) {
    Matcher matcher = pattern.matcher(userInput);
    String parameter = matcher.matches() ? matcher.group(1) : null;
    return ((ParametrizedDecision<?>) decision).withParameter(parameter);
  }

}
