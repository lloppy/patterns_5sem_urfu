package ask.urfu.misc.patterns.fantasygame.models.gear;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.library.gear.GearEffect;

// Паттерн Singleton
public enum GearType {

  SWORD("sword", model -> ((FantasyCharacterModel) model).increaseStrength(2)),

  BOW("bow", model -> ((FantasyCharacterModel) model).increaseStrength(1)),

  STAFF("staff", model -> ((FantasyCharacterModel) model).increaseMagic(2)),

  BOOTS("boots", model -> ((FantasyCharacterModel) model).increaseProtection(1)),

  HELMET("helmet", model -> ((FantasyCharacterModel) model).increaseProtection(1)),

  SHIELD("shield", model -> ((FantasyCharacterModel) model).increaseProtection(2));

  private final String name;
  private final GearEffect effect;

  GearType(String name, GearEffect effect) {
    this.name = name;
    this.effect = effect;
  }

  public String getName() {
    return name;
  }

  public GearEffect getEffect() {
    return effect;
  }
}
