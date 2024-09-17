package ask.urfu.misc.patterns.fantasygame.models.character;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.library.character.CharacterModel;
import ask.urfu.misc.patterns.library.character.NonPlayerCharacter;
import ask.urfu.misc.patterns.library.gear.Gear;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Peaceful implements NonPlayerCharacter {

  private final String name;

  private final String description;

  private final List<String> questText;

  private Peaceful(String name, String description, List<String> questText) {
    this.name = name;
    this.description = description;
    this.questText = questText;
  }

  public static Description having() {
    return new Description();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public CharacterModel getModel() {
    return FantasyCharacterModel.peaceful();
  }

  @Override
  public Stream<Gear> gear() {
    return Stream.empty();
  }

  public static class Description {

    private String name;

    private String appearance;

    private List<String> questText;

    public Description name(String name) {
      this.name = name;
      return this;
    }

    public Description appearance(String text) {
      this.appearance = text;
      return this;
    }

    public Description questText(String... phrases) {
      this.questText = Arrays.asList(phrases);
      return this;
    }

    // 😒 паблик - множество экземпляров
    public Peaceful done() {
      return new Peaceful(name, appearance, questText);
    }

  }
}
