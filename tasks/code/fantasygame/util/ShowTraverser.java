package ask.urfu.misc.patterns.fantasygame.util;

import ask.urfu.misc.patterns.fantasygame.DungeonMap;
import ask.urfu.misc.patterns.fantasygame.FantasyGame;
import ask.urfu.misc.patterns.fantasygame.models.DungeonLocation;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.userinterface.ports.PlayerPort;
import ask.urfu.misc.patterns.fantasygame.userinterface.ports.Port;
import ask.urfu.misc.patterns.fantasygame.userinterface.ports.ShowPort;

public class ShowTraverser implements GameTraverser {

  ShowPort gamePort;
  ShowPort playerPort;
  ShowPort mapPort;


  @Override
  public void traverse(FantasyGame game) {
    gamePort = new Port();
  }

  @Override
  public void traverse(Adventurer adventurer) {
    PlayerPort port = new PlayerPort();
    port.initialize(adventurer);
    playerPort = port;
  }

  @Override
  public void traverse(Item item) {
    // unused for now
  }

  @Override
  public void traverse(DungeonMap map) {
    // unused for now
  }

  @Override
  public void traverse(DungeonLocation location) {
    // unused for now
  }

  @Override
  public void traverse(Monster monster) {
    // unused for now
  }

  public ShowPort result() {
    gamePort.addSubport(0, 0, playerPort);
    gamePort.addSubport(1, 0, playerPort);
    gamePort.addSubport(2, 0, playerPort);
    gamePort.addSubport(1, 1, playerPort);
    gamePort.addSubport(2, 1, playerPort);
    return gamePort;
  }

}
