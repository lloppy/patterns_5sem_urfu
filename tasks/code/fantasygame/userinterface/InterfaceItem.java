package ask.urfu.misc.patterns.fantasygame.userinterface;

import ask.urfu.misc.patterns.fantasygame.userdecisions.AdventurerDecision;
import java.util.regex.Pattern;

public class InterfaceItem {

  protected final Pattern pattern;
  protected final AdventurerDecision decision;
  private final String value;


  public InterfaceItem(String value, String pattern, AdventurerDecision decision) {
    this.value = value;
    this.pattern = Pattern.compile(pattern);
    this.decision = decision;
  }

  public InterfaceItem(String value, AdventurerDecision decision) {
    this.value = value;
    this.pattern = Pattern.compile(value);
    this.decision = decision;
  }


  public boolean accept(String userInput) {
    return pattern.matcher(userInput).matches();
  }

  public String value() {
    return value;
  }

  public AdventurerDecision decision(String userInput) {
    // ignore user input in simple case
    return decision;
  }

  public AdventurerDecision decision() {
    return decision;
  }

}
