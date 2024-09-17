package ask.urfu.misc.patterns.fantasygame.modes;

import ask.urfu.misc.patterns.fantasygame.FantasyGame;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.rules.combat.Combat;
import ask.urfu.misc.patterns.fantasygame.rules.combat.Combat.Context;
import ask.urfu.misc.patterns.fantasygame.rules.combat.SimpleCombat;
import ask.urfu.misc.patterns.fantasygame.service.CombatEventsLogger;
import ask.urfu.misc.patterns.fantasygame.userinterface.KeyboardInterface;
import ask.urfu.misc.patterns.fantasygame.userinterface.menu.CombatMenu;
import ask.urfu.misc.patterns.fantasygame.userinterface.menu.MainMenu;
import ask.urfu.misc.patterns.fantasygame.userinterface.menu.MultiMenu;
import ask.urfu.misc.patterns.fantasygame.util.LazyRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Random;

public class CombatMode extends GameModeBase {

  Combat combat;
  Context context;
  LazyRandom random;
  private boolean newCombat = true;
  private boolean combatEnded = false;

  public CombatMode() {
    super(new MultiMenu(
        new KeyboardInterface("Menu"),
        new MainMenu(),
        new CombatMenu()
    ));
    // TODO: 12.08.2024 use main random
    this.random = new LazyRandom(() ->
        new Random(LocalDateTime.now().get(ChronoField.MILLI_OF_DAY))
    );
  }

  @Override
  public void turn() {
    if (combatEnded) {
      endCombat();
    } else {
      if (newCombat) {
        initializeCombat();
      }
      nextCombatRound();
    }

    userInterface.draw();
    try {
      userInterface.awaitAndExecuteNextDecision();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void nextCombatRound() {
    combat.processTurn(context);
    combatEnded = combat.isOver();
  }

  private void initializeCombat() {
    combat = new SimpleCombat(random);
    context = combat.newContext(
        FantasyGame.theGame().player(),
        FantasyGame.theGame()
            .map()
            .currentLocation()
            .enemies()
            .map(Monster.class::cast)
            .toArray(Monster[]::new)
    );
    ((SimpleCombat) combat).addReactor(new CombatEventsLogger());
  }

  private void endCombat() {
    newCombat = true;
    combatEnded = false;
    combat = null;
    context = null;
    FantasyGame.theGame().resumeWandering();
  }

}
