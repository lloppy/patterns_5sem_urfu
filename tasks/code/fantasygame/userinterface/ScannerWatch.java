package ask.urfu.misc.patterns.fantasygame.userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerWatch {

  private final Scanner scanner;
  private final Events events = new Events();
  private boolean done;

  public ScannerWatch() {
    done = false;
    scanner = new Scanner(System.in);

    Thread thread = new Thread(this::loop, "ScannerWatch");
    thread.setDaemon(true);
    thread.start();
  }

  public Events getEvents() {
    return events;
  }

  private void loop() {
    while (!done) {
      try {
        String line = scanner.nextLine();
        events.signalNewLine(line);
      } catch (Exception e) {
        done = true;
      }
    }
  }


  public interface Watcher {

    void newLineAvailable(String line);

  }

  public static class Events {

    private final List<Watcher> watchers = new ArrayList<>();

    public void subscribe(Watcher watcher) {
      watchers.add(watcher);
    }

    private void signalNewLine(String line) {
      watchers.forEach(watcher ->
          watcher.newLineAvailable(line)
      );
    }

  }

}
