package ask.urfu.misc.patterns.fantasygame.modes;

import ask.urfu.misc.patterns.fantasygame.FantasyGame;
import ask.urfu.misc.patterns.fantasygame.rules.magic.Magic;
import ask.urfu.misc.patterns.fantasygame.userinterface.KeyboardInterface;
import ask.urfu.misc.patterns.fantasygame.userinterface.menu.MainMenu;
import ask.urfu.misc.patterns.fantasygame.userinterface.menu.MultiMenu;
import ask.urfu.misc.patterns.fantasygame.userinterface.menu.WanderingMenu;
import ask.urfu.misc.patterns.fantasygame.util.LazyRandom;
import ask.urfu.misc.patterns.library.game.Location;
import ask.urfu.misc.patterns.library.gear.Gear;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class WanderingGameMode extends GameModeBase {

  private final Magic magic = new Magic(
      new LazyRandom(() ->
          new Random(LocalDateTime.now().get(ChronoField.MILLI_OF_DAY))
      )
  );

  public WanderingGameMode() {
    super(new MultiMenu(
        new KeyboardInterface("Menu"),
        new WanderingMenu(),
        new MainMenu()
    ));
  }

  private static String enemiesDescription(Location location) {
    var enemies = location.enemies().toList();
    String enemyString = "";
    if (!enemies.isEmpty()) {
      int enemyCount = enemies.size();
      String enemyName = enemies.get(0).getName();
      if (enemyCount == 1) {
        enemyString = ("a " + enemyName + " ");
      } else {
        enemyString = enemyCount + " " + enemyName + "s ";
      }
    }
    return enemyString;
  }

  private static String itemsDescription(Location location) {
    List<Gear> gears = location.items().toList();
    StringBuilder itemString = new StringBuilder();
    if (!gears.isEmpty()) {
      gears.forEach(item -> {
        if (!itemString.isEmpty()) {
          itemString.append(", ");
        }
        itemString.append(item.toString());
      });
    }
    return itemString.toString();
  }

  public void describe() {
    String playerName = FantasyGame.theGame().player().getName();
    Location location = FantasyGame.theGame().map().currentLocation();
    StringBuilder description = new StringBuilder()
        .append(playerName)
        .append(" enters ")
        .append(location.name())
        .append(" and sees ");
    String enemiesDescription = enemiesDescription(location);
    String itemsDescription = itemsDescription(location);
    if (!enemiesDescription.isEmpty()) {
      description.append(enemiesDescription);
    }
    if (!itemsDescription.isEmpty()) {
      description.append(itemsDescription);
    }
    if (enemiesDescription.isEmpty() && itemsDescription.isEmpty()) {
      description.append("nothing");
    }
    System.out.println(description);
  }

  public void castSpell(String spell) {
    Optional.ofNullable(magic.getSpell(spell))
        .map(magicSpell -> magic.castSpell(
            FantasyGame.theGame().player(),
            magicSpell
        )).ifPresent(spellEffect ->
            spellEffect.apply(FantasyGame.theGame().map())
        );
  }

  @Override
  public void turn() {
    FantasyGame.theGame().showGamePorts();
    describe();
    userInterface.draw();
    try {
      userInterface.awaitAndExecuteNextDecision();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

}
