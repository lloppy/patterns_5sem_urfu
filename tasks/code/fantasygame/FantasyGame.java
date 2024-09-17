package ask.urfu.misc.patterns.fantasygame;

import ask.urfu.misc.patterns.fantasygame.models.FantasyCharacterModel;
import ask.urfu.misc.patterns.fantasygame.models.character.Adventurer;
import ask.urfu.misc.patterns.fantasygame.modes.CreateCharacterMode;
import ask.urfu.misc.patterns.fantasygame.modes.GameModes;
import ask.urfu.misc.patterns.fantasygame.modes.WanderingGameMode;
import ask.urfu.misc.patterns.fantasygame.service.SavedGames;
import ask.urfu.misc.patterns.fantasygame.userinterface.ports.ShowPort;
import ask.urfu.misc.patterns.fantasygame.util.GameTraverser;
import ask.urfu.misc.patterns.fantasygame.util.ShowTraverser;
import ask.urfu.misc.patterns.fantasygame.util.Traversable;
import ask.urfu.misc.patterns.library.game.Game;
import ask.urfu.misc.patterns.library.gear.Gear;
import java.util.List;

public class FantasyGame implements Game, Traversable {

  private static final FantasyGame GAME = new FantasyGame();
  private Adventurer player;
  private DungeonMap map;
  private GameMode mode;
  private boolean gameOver = false;


  public static FantasyGame theGame() {
    return GAME;
  }


  public static void main(String[] args) {
    FantasyGame.theGame().initialize();
    FantasyGame.theGame().runGame();
  }


  @Override
  public Adventurer player() {
    return player;
  }

  @Override
  public DungeonMap map() {
    return map;
  }

  @Override
  public void initialize() {
    GameConfig gameConfig = new GameConfig()
        .enemyFiles("files")
        .randomMonsters(1)
        .randomMonsters(2)
        .done();
    map = gameConfig.randomMap();
    player = gameConfig.randomAdventurer();
    changeMode(GameModes.INITIAL.get());
  }

  @Override
  public int save() {
    return SavedGames.service().saveGame();
  }

  @Override
  public void load(int saveId) {
    SavedGames.service().loadGame(saveId);
  }

  @Override
  public void gameTurn() {
    try {
      mode.turn();
    } catch (Exception e) {
      System.out.printf("Error: %s%n", e.getMessage());
    }
  }

  public void showGamePorts() {
    ShowTraverser traverser = new ShowTraverser();
    this.accept(traverser);
    ShowPort result = traverser.result();
    ShowPort.print(result);
  }

  @Override
  public void accept(GameTraverser traverser) {
    traverser.traverse(this);
    traverser.traverse(player());
    traverser.traverse(map());
  }

  public void runGame() {
    while (!gameOver) {
      gameTurn();
    }
  }

  public void changePlayerName(String newName) {
    player().changeName(newName);
  }

  public void createCharacter() {
    changeMode(GameModes.CREATE_CHARACTER.get());
  }

  public void endCreateCharacter() {
    if (mode instanceof CreateCharacterMode createCharacterMode) {
      player = createCharacterMode.doCreate();
      changeMode(GameModes.INITIAL.get());
    }
  }

  public void startAdventure() {
    changeMode(GameModes.WANDERING.get());
  }

  public void move(Direction direction) {
    map().move(direction);
  }

  public void enterCombat() {
    changeMode(GameModes.COMBAT.get());
  }

  public void resumeWandering() {
    changeMode(GameModes.WANDERING.get());
  }

  public void endGame() {
    gameOver = true;
  }

  private void changeMode(GameMode newMode) {
    if (mode != null) {
      mode.deactivate();
    }
    mode = newMode;
    mode.activate();
  }

  public void castSpell(String spell) {
    if (mode instanceof WanderingGameMode wandering) {
      wandering.castSpell(spell);
    }
  }

  public GameSave createSave() {
    GameSave save = new GameSave();
    save.update(this);
    return save;
  }

  public void loadSave(GameSave gameSave) {
    // TODO: 04.09.2024 implement
  }

  public static class GameSave {

    private AdventurerSave player;
    private MapSave map;
    private String mode;


    public void update(FantasyGame game) {
      this.player = new AdventurerSave();
      this.player.update(game.player);
      this.map = new MapSave();
      this.map.update(game.map);
      this.mode = game.mode.name();
    }

  }

  private static class AdventurerSave {

    String name;
    FantasyCharacterModel model;
    List<String> items;

    void update(Adventurer adventurer) {
      name = adventurer.getName();
      model = new FantasyCharacterModel(adventurer.getModel());
      items = adventurer.gear().map(Gear::name).toList();
    }
  }

  private static class MapSave {

    public void update(DungeonMap map) {
      // TODO: 04.09.2024 implement
    }
  }

}
