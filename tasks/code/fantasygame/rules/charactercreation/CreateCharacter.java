package ask.urfu.misc.patterns.fantasygame.rules.charactercreation;

import ask.urfu.misc.patterns.fantasygame.models.PlayerCharacterClass;
import ask.urfu.misc.patterns.fantasygame.models.gear.GearType;
import ask.urfu.misc.patterns.fantasygame.models.gear.Item;
import ask.urfu.misc.patterns.fantasygame.models.perks.Perk;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CreateCharacter {

  private final Collaborator raceSource;
  private final Collaborator classSource;
  private final Collaborator itemsSource;
  private Context context;


  public CreateCharacter(
      Collaborator raceSource,
      Collaborator classSource,
      Collaborator itemsSource) {
    this.raceSource = raceSource;
    this.classSource = classSource;
    this.itemsSource = itemsSource;
  }


  private static void ignoreError(String error) {
    // do nothing when error occurs, use this method when not interested in error message
  }


  public Context context() {
    if (context == null) {
      context = new Context();
    }
    return context;
  }

  public void wasUpdated(Collaborator collaborator) {
    if (collaborator == raceSource || collaborator == classSource) {
      cancelIfRulesNotRespected(
          this::checkRaceAndClass, collaborator
      );
    }
    if (collaborator == classSource || collaborator == itemsSource) {
      cancelIfRulesNotRespected(
          this::checkClassAndItem, collaborator
      );
    }
    if (collaborator == classSource) {
      setClassItem();
      if (!checkItemsToDiffer(CreateCharacter::ignoreError)) {
        context.selectableItem = null;
      }
    }
    if (collaborator == itemsSource) {
      cancelIfRulesNotRespected(
          this::checkItemsToDiffer, collaborator
      );
    }
  }

  //region Rules

  private boolean checkRaceAndClass(Consumer<String> errorTarget) {
    var race = context().race;
    var characterClass = context().characterClass;

    if (race == Race.ELF && characterClass == PlayerCharacterClass.ROGUE) {
      errorTarget.accept("elves cannot be rogues");
      return false;
    }
    if (race == Race.DWARF && characterClass == PlayerCharacterClass.MAGE) {
      errorTarget.accept("dwarves cannot be mages");
      return false;
    }
    return true;
  }

  private boolean checkClassAndItem(Consumer<String> errorTarget) {
    var characterClass = context().characterClass;
    var item = context().selectableItem;

    if (characterClass == null || item == null) {
      return true;
    }
    if (characterClass == PlayerCharacterClass.MAGE && (item.getType() == GearType.SHIELD)) {
      errorTarget.accept("mages cannot use shields");
      return false;
    }
    if (characterClass == PlayerCharacterClass.MAGE && (item.getType() == GearType.BOW)) {
      errorTarget.accept("mages cannot use bows");
      return false;
    }
    if (characterClass == PlayerCharacterClass.ROGUE && (item.getType() == GearType.HELMET)) {
      errorTarget.accept("rogues cannot use helmets");
      return false;
    }
    return true;
  }

  private boolean checkItemsToDiffer(Consumer<String> errorTarget) {
    if (context.classItem == context.selectableItem) {
      errorTarget.accept("items must be different");
      return false;
    }
    return true;
  }

  private void setClassItem() {
    if (context.characterClass != null) {
      context.classItem = switch (context.characterClass) {
        case WARRIOR -> Item.basic(GearType.SWORD);
        case MAGE -> Item.basic(GearType.STAFF);
        case ROGUE -> Item.basic(GearType.BOW);
      };
    }
  }

  //endregion

  private void cancelIfRulesNotRespected(
      Predicate<Consumer<String>> rules, Collaborator collaborator
  ) {
    StringBuilder error = new StringBuilder();
    var rulesRespected = rules.test(error::append);
    if (!rulesRespected) {
      context.restoreState();
      collaborator.cancelAction(error.toString());
    }
  }


  public static class Context {

    private Race race;
    private PlayerCharacterClass characterClass;
    private Perk perk;
    private Item classItem;
    private Item selectableItem;
    private Context oldState;


    private static void copy(Context from, Context to) {
      to.race = from.race;
      to.characterClass = from.characterClass;
      to.perk = from.perk;
      to.classItem = from.classItem;
      to.selectableItem = from.selectableItem;
      to.oldState = from.oldState;
    }

    private static String noneOr(Object o) {
      return o == null ? "none" : o.toString();
    }

    private static String noneOr(Supplier<Object> source) {
      Object o = null;
      try {
        o = source.get();
      } catch (Exception e) {
        // ignore errors
      }
      return noneOr(o);
    }


    public void chooseClass(String parameter) {
      storeState();
      characterClass = PlayerCharacterClass.valueOf(parameter.toUpperCase());
    }

    public void chooseRace(String parameter) {
      storeState();
      race = Race.valueOf(parameter.toUpperCase());
    }

    public void chooseItem(String parameter) {
      storeState();
      this.selectableItem = Item.basic(GearType.valueOf(parameter));
    }

    @Override
    public String toString() {
      return "Character" +
          ":\n race: " + noneOr(race) +
          ",\n class: " + noneOr(characterClass) +
          ",\n perk: " + noneOr(perk) +
          ",\n item1: " + noneOr(() -> classItem.getType()) +
          ",\n item2: " + noneOr(() -> selectableItem.getType()) +
          "\n";
    }

    private void storeState() {
      if (oldState == null) {
        oldState = new Context();
      }
      copy(this, oldState);
    }

    private void restoreState() {
      if (oldState != null) {
        copy(oldState, this);
        oldState = null;
      }
    }

    public Race getRace() {
      return race;
    }

    public PlayerCharacterClass getCharacterClass() {
      return characterClass;
    }

    public Perk getPerk() {
      return perk;
    }

    public Item getClassItem() {
      return classItem;
    }

    public Item getSelectableItem() {
      return selectableItem;
    }
  }

}
