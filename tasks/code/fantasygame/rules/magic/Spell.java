package ask.urfu.misc.patterns.fantasygame.rules.magic;

import java.util.function.UnaryOperator;

/**
 * Every spell consists of:
 * <ul>
 *   <li>time regulation (optional, defaults to instant);</li>
 *   <li>target regulation (optional, defaults to random visible target);</li>
 *   <li>action regulation.</li>
 * </ul>
 * The following rules apply:
 * <ul>
 *   <li>action regulation 'kill' can only have 'instant' time regulation;</li>
 *   <li>other action regulations cannot have 'instant' time regulation;</li>
 * </ul>
 */
public class Spell implements MagicConsuming {

  private final TimeRegulation timeRegulation;

  private final TargetRegulation targetRegulation;

  private final ActionRegulation actionRegulation;

  public Spell(
      TimeRegulation timeRegulation,
      TargetRegulation targetRegulation,
      ActionRegulation actionRegulation) {
    if (timeRegulation != TimeRegulation.INSTANT && actionRegulation == ActionRegulation.KILL) {
      throw new IllegalArgumentException("wrong spell");
    }
    if (timeRegulation == TimeRegulation.INSTANT && actionRegulation != ActionRegulation.KILL) {
      throw new IllegalArgumentException("wrong spell");
    }
    this.timeRegulation = timeRegulation;
    this.targetRegulation = targetRegulation;
    this.actionRegulation = actionRegulation;
  }

  @Override
  public int power() {
    return timeRegulation.power() + targetRegulation.power() + actionRegulation.power();
  }

  @Override
  public SpellEffectAccumulator effect(SpellEffectAccumulator effect) {
    return ((UnaryOperator<SpellEffectAccumulator>) actionRegulation::effect)
        .andThen(targetRegulation::effect)
        .andThen(timeRegulation::effect)
        .apply(effect);
  }

}
