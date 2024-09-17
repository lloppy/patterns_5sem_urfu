package ask.urfu.misc.patterns.fantasygame.rules.combat;

import static java.util.Comparator.comparing;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.rules.combat.SimpleCombat.SimpleCombatModel;
import ask.urfu.misc.patterns.fantasygame.util.LazyRandom;
import ask.urfu.misc.patterns.library.character.GameCharacter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SimpleCombat extends Combat<SimpleCombatModel> {

  private final LazyRandom random;
  private final List<CombatEventsReactor> reactors = new ArrayList<>();

  public SimpleCombat(LazyRandom random) {
    this.random = random;
  }

  @Override
  protected void attack(SimpleCombatModel actor, Context<SimpleCombatModel> context) {
    SimpleCombatModel target = chooseTarget(actor, context);
    reactors.forEach(r -> r.attackCommenced(this, actor, target));

    int agilityBonus = actor.getAgility() - target.getAgility();
    int damage = Math.max(0, actor.getStrength() + agilityBonus);
    target.health -= damage;
    reactors.forEach(r -> r.targetHit(this, actor, target, damage));

    if (target.isDead()) {
      reactors.forEach(r -> r.targetDead(this, target));
    }
  }

  @Override
  protected SimpleCombatModel toModel(GameCharacter character) {
    FantasyCharacterModel initialModel = (FantasyCharacterModel) character.getModel();
    FantasyCharacterModel effectiveModel = character.gear()
        .reduce(
            initialModel,
            (model, gear) -> (FantasyCharacterModel) gear.effect().applyTo(model),
            (x, y) -> y
        );
    return new SimpleCombatModel(
        character.getName(),
        effectiveModel.getLevel(),
        effectiveModel.getStrength(),
        effectiveModel.getAgility(),
        effectiveModel.getMagic()
    );
  }

  @Override
  protected List<SimpleCombatModel> determineSequence(Context<SimpleCombatModel> context) {
    var allCombatants = Stream.concat(
        Stream.of(context.player),
        context.enemies.stream()
    );
    return allCombatants
        .sorted(comparing(FantasyCharacterModel::getAgility))
        .toList();
  }

  @Override
  protected SimpleCombatModel chooseTarget(
      SimpleCombatModel actor,
      Context<SimpleCombatModel> context) {
    if (actor == context.player) {
      // player targets random enemy
      return random.nextOf(context.enemies);
    } else {
      // enemies always target player
      return context.player;
    }
  }

  public void addReactor(CombatEventsReactor reactor) {
    this.reactors.add(reactor);
  }

  public interface CombatEventsReactor {

    void attackCommenced(SimpleCombat source, SimpleCombatModel actor, SimpleCombatModel target);

    void targetHit(
        SimpleCombat source, SimpleCombatModel actor, SimpleCombatModel target, int damage
    );

    void targetDead(SimpleCombat source, SimpleCombatModel target);

  }

  public static class SimpleCombatModel extends Combat.Model {

    String name;
    int health;

    protected SimpleCombatModel(String name, int level, int strength, int agility, int magic) {
      super(level, strength, agility, magic);
      this.name = name;
      this.health = strength + level;
    }

    public String getName() {
      return name;
    }

    public int getHealth() {
      return health;
    }

    @Override
    protected boolean isDead() {
      return health < 1;
    }

  }

}
