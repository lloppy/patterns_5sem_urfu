package ask.urfu.misc.patterns.fantasygame.userinterface.ports;

public interface ShowPort {

  static void print(ShowPort port) {
    port.contents()
        .lines()
        .forEach(System.out::println);
  }

  PortContents contents();

  void write(String contents);

  void addSubport(int x, int y, ShowPort playerPort);

}
