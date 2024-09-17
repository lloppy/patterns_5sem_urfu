package ask.urfu.misc.patterns.fantasygame.models.gear;

import ask.urfu.misc.patterns.library.gear.Gear;
import ask.urfu.misc.patterns.library.gear.GearEffect;

public class Item implements Gear {

  private final String name;
  private final GearType type;
  private final GearEffect additionalEffect;


  private Item(String name, GearType type, GearEffect additionalEffect) {
    this.name = name;
    this.type = type;
    this.additionalEffect = additionalEffect;
  }

  // 😒 паблик - множество экземпляров
  public static Item basic(GearType type) {
    return new Item(
        "simple " + type.getName(),
        type,
        null
    );
  }

  // 😒 паблик - множество экземпляров
  public static Item special(String name, GearType type, GearEffect additionalEffect) {
    return new Item(name, type, additionalEffect);
  }


  @Override
  public String name() {
    return name;
  }

  @Override
  public GearEffect effect() {
    return GearEffects.compose(type.getEffect(), additionalEffect);
  }

  @Override
  public Gear replicate() {
    return null;
  }

  public GearType getType() {
    return type;
  }

}
