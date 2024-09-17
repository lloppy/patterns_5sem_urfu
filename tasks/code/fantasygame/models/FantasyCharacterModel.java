package ask.urfu.misc.patterns.fantasygame.models;

import ask.urfu.misc.patterns.library.character.CharacterModel;

public class FantasyCharacterModel implements CharacterModel {

  private int level;
  private int strength;
  private int agility;
  private int magic;


  public FantasyCharacterModel() {
  }

  public FantasyCharacterModel(int level, int strength, int agility, int magic) {
    this.level = level;
    this.strength = strength;
    this.agility = agility;
    this.magic = magic;
  }

  public FantasyCharacterModel(FantasyCharacterModel that) {
    this(that.getLevel(), that.getStrength(), that.getAgility(), that.getMagic());
  }


  public static FantasyCharacterModel initialWarrior() {
    return new FantasyCharacterModel(1, 3, 2, 0);
  }

  public static FantasyCharacterModel initialRogue() {
    return new FantasyCharacterModel(1, 1, 3, 1);
  }

  public static FantasyCharacterModel initialMage() {
    return new FantasyCharacterModel(1, 1, 1, 3);
  }

  public static FantasyCharacterModel peaceful() {
    return new FantasyCharacterModel(0, 0, 0, 0);
  }


  public int getLevel() {
    return level;
  }

  public int getStrength() {
    return strength;
  }

  public int getAgility() {
    return agility;
  }

  public int getMagic() {
    return magic;
  }


  public FantasyCharacterModel increaseStrength(int amount) {
    return new FantasyCharacterModel(
        this.getLevel(), this.getStrength() + amount, this.getAgility(), this.getMagic()
    );
  }

  public FantasyCharacterModel increaseMagic(int amount) {
    return new FantasyCharacterModel(
        this.getLevel(), this.getStrength(), this.getAgility(), this.getMagic() + amount
    );
  }

  public FantasyCharacterModel increaseProtection(int amount) {
    return new FantasyCharacterModel(
        this.getLevel(), this.getStrength() + amount, this.getAgility() + amount, this.getMagic()
    );
  }


}
