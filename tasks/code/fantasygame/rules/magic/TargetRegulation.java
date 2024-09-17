package ask.urfu.misc.patterns.fantasygame.rules.magic;

import ask.urfu.misc.patterns.fantasygame.FantasyGame;

/**
 * Target regulation consists of:
 * <ul>
 *   <li>quantifier regulation (optional, defaults to random visible target);</li>
 *   <li>monster name (optional, defaults to none).</li>
 * </ul>
 */
class TargetRegulation implements MagicConsuming {

  QuantifierRegulation quantifierRegulation;
  String monsterName;

  public TargetRegulation(QuantifierRegulation quantifierRegulation, String monsterName) {
    this.quantifierRegulation = quantifierRegulation;
    this.monsterName = monsterName;
  }

  @Override
  public int power() {
    int monsterNameModifier = monsterName == null ? 2 : 0;
    return quantifierRegulation.power() + monsterNameModifier;
  }

  @Override
  public SpellEffectAccumulator effect(SpellEffectAccumulator effect) {
    SpellEffectAccumulator spellEffectAccumulator = effect.onMonsterType(monsterName);
    if (this.quantifierRegulation == QuantifierRegulation.ALL_VISIBLE
        || this.quantifierRegulation == QuantifierRegulation.RANDOM_VISIBLE
    ) {
      spellEffectAccumulator.locations(FantasyGame.theGame().map().currentLocation());
    }
    if (this.quantifierRegulation == QuantifierRegulation.RANDOM_VISIBLE) {
      spellEffectAccumulator.randomMonster();
    }
    return spellEffectAccumulator;
  }

}
