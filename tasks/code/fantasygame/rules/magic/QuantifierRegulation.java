package ask.urfu.misc.patterns.fantasygame.rules.magic;

// Паттерн Singleton
enum QuantifierRegulation implements MagicConsuming {

  ALL("alikus", 3),

  ALL_VISIBLE("visicus", 2),

  RANDOM_VISIBLE("ranikus", 1);

  private final int power;
  private final String word;

  QuantifierRegulation(String word, int power) {
    this.word = word;
    this.power = power;
  }

  @Override
  public int power() {
    return power;
  }

  @Override
  public SpellEffectAccumulator effect(SpellEffectAccumulator effect) {
    // do nothing
    return effect;
  }

  public String word() {
    return word;
  }

}
