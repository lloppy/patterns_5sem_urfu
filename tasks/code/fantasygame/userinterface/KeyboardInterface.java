package ask.urfu.misc.patterns.fantasygame.userinterface;

import ask.urfu.misc.patterns.library.game.UserDecision;
import ask.urfu.misc.patterns.library.userinterface.UserInterfaceImplementation;
import java.util.List;

public class KeyboardInterface implements UserInterfaceImplementation {

  private static final ScannerWatch WATCH = new ScannerWatch();
  private final String title;
  private final List<InterfaceItem> keyMap;
  private UserDecision decision;
  private boolean active = false;

  public KeyboardInterface(
      String title,
      List<InterfaceItem> keyMap
  ) {
    this.title = title;
    this.keyMap = keyMap;
    this.decision = null;
    WATCH.getEvents().subscribe(this::whenNewLineAvailable);
  }

  public KeyboardInterface(String title) {
    this(title, List.of());
  }

  @Override
  public void show() {
    System.out.println(this);
  }

  @Override
  public boolean ready() {
    return decision != null;
  }

  @Override
  public UserDecision getDecision() {
    UserDecision fetchedDecision = this.decision;
    if (fetchedDecision != null) {
      this.decision = null;
    }
    return fetchedDecision;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder()
        .append(title)
        .append(":\n-------------\n");
    keyMap.forEach(item ->
        builder.append(
            String.format("[%s]: %s%n", item.value(), item.decision().description())
        ));
    return builder.toString();
  }

  @Override
  public void activate() {
    this.active = true;
  }

  @Override
  public void deactivate() {
    this.active = false;
  }

  private void whenNewLineAvailable(String line) {
    if (active) {
      String input = line.trim().toUpperCase();
      this.decision = keyMap.stream()
          .filter(x -> x.accept(input))
          .findFirst()
          .map(x -> x.decision(input))
          .orElse(null);
    }
  }

}
