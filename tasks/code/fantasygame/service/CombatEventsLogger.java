package ask.urfu.misc.patterns.fantasygame.service;

import ask.urfu.misc.patterns.fantasygame.rules.combat.SimpleCombat;
import ask.urfu.misc.patterns.fantasygame.rules.combat.SimpleCombat.CombatEventsReactor;
import ask.urfu.misc.patterns.fantasygame.rules.combat.SimpleCombat.SimpleCombatModel;

public class CombatEventsLogger implements CombatEventsReactor {

  @Override
  public void attackCommenced(
      SimpleCombat source, SimpleCombatModel actor, SimpleCombatModel target
  ) {
    System.out.println(actor.getName() + " attacks " + target.getName() + ".");
  }

  @Override
  public void targetHit(
      SimpleCombat source, SimpleCombatModel actor, SimpleCombatModel target, int damage
  ) {
    System.out.printf(
        "%s hits %s for %s health. %s has %s health left.%n",
        actor.getName(), target.getName(), damage, target.getName(), target.getHealth());
  }

  @Override
  public void targetDead(SimpleCombat source, SimpleCombatModel target) {
    System.out.println(target.getName() + " is dead.");
  }

}
