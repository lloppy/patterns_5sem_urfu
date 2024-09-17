package ask.urfu.misc.patterns.fantasygame.rules.combat;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.models.character.Monster;
import ask.urfu.misc.patterns.fantasygame.rules.combat.Combat.Model;
import ask.urfu.misc.patterns.library.character.GameCharacter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Combat<M extends Model> {

  private boolean over = false;

  public boolean isOver() {
    return over;
  }

  public Context<M> newContext(Adventurer player, Monster... enemies) {
    return new Context<>(
        toModel(player),
        Arrays.stream(enemies).map(this::toModel).collect(Collectors.toList())
    );
  }

  public final boolean processTurn(Context<M> context) {
    List<M> actionSequence = determineSequence(context);
    actionSequence.forEach(actor -> attack(actor, context));
    context.removeDeadEnemies();
    over = context.battleEnded();
    return over;
  }


  protected abstract void attack(M actor, Context<M> context);

  protected abstract M toModel(GameCharacter character);

  protected abstract List<M> determineSequence(Context<M> context);

  protected abstract M chooseTarget(M actor, Context<M> context);

  public abstract static class Model extends FantasyCharacterModel {

    protected Model(int level, int strength, int agility, int magic) {
      super(level, strength, agility, magic);
    }

    protected abstract boolean isDead();

  }

  public static class Context<M extends Model> {

    M player;

    List<M> enemies;

    public Context(M player, List<M> enemies) {
      this.player = player;
      this.enemies = enemies;
    }

    private void removeDeadEnemies() {
      List<M> dead = enemies.stream().filter(Model::isDead).toList();
      enemies.removeAll(dead);
    }

    public boolean battleEnded() {
      return player.isDead() || enemies.isEmpty();
    }
  }

}
