package ask.urfu.misc.patterns.fantasygame.rules.magic;

// Паттерн Singleton
enum ActionRegulation implements MagicConsuming {

  BLIND("bliki", 1),

  WEAKEN("weki", 1),

  KILL("kirdiki", 3);

  private final int power;
  private final String word;

  ActionRegulation(String word, int power) {
    this.power = power;
    this.word = word;
  }

  @Override
  public int power() {
    return power;
  }

  @Override
  public SpellEffectAccumulator effect(SpellEffectAccumulator effect) {
    return switch (this) {
      case KILL -> effect.type(KillSpellEffect::new);
      // TODO: 06.09.2024 add other cases
      default -> throw new UnsupportedOperationException("Not implemented");
    };
  }

  public String word() {
    return word;
  }
}
