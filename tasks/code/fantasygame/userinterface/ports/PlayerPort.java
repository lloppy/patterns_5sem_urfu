package ask.urfu.misc.patterns.fantasygame.userinterface.ports;

import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;

public class PlayerPort extends SimplePort {

  public void initialize(Adventurer player) {
    this.write(player.getName());
    this.write(player.playerClass().name());
    this.write(player.playerClass().description());
  }

}
