package ask.urfu.misc.patterns.fantasygame.rules.magic;

// Паттерн Singleton
public enum TimeRegulation implements MagicConsuming {

  INSTANT("antum", 1),

  THREE_TURNS("urnum", 1),

  FOREVER("verum", 2);

  private final String word;
  private final int power;

  TimeRegulation(String word, int power) {
    this.word = word;
    this.power = power;
  }

  @Override
  public int power() {
    return power;
  }

  @Override
  public SpellEffectAccumulator effect(SpellEffectAccumulator effect) {
    return switch (this) {
      case INSTANT -> effect.instant();
      case FOREVER -> effect.lastsForever();
      case THREE_TURNS -> effect.lasts(3);
    };
  }

  public String word() {
    return word;
  }

}
