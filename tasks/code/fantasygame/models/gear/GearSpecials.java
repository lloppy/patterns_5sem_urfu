package ask.urfu.misc.patterns.fantasygame.models.gear;

import ask.urfu.misc.patterns.library.gear.GearEffect;

// Паттерн Singleton
public enum GearSpecials {

  SHARP("sharp", GearEffects.sharp()),

  VORPAL("vorpal", GearEffects.vorpal()),

  MAGICAL("magical", GearEffects.magical()),

  POWERFUL("powerful", GearEffects.powerful()),

  STURDY("sturdy", GearEffects.sturdy());

  private final String description;
  private final GearEffect additionalEffect;

  GearSpecials(String description, GearEffect additionalEffect) {
    this.description = description;
    this.additionalEffect = additionalEffect;
  }

  public String getDescription() {
    return description;
  }

  public GearEffect getAdditionalEffect() {
    return additionalEffect;
  }
}
