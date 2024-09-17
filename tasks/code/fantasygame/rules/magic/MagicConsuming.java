package ask.urfu.misc.patterns.fantasygame.rules.magic;

public interface MagicConsuming {

  int power();

  SpellEffectAccumulator effect(SpellEffectAccumulator effect);

}
