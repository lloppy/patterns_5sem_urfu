package ask.urfu.misc.patterns.fantasygame.rules.magic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * For parsing rules see {@link Spell}
 */
public class SpellParser {

  // test
  public static void main(String[] args) {
    SpellParser parser = new SpellParser();
    // no time (kill all goblins instantly)
    parser.parseSpell("alikus goblin kirdiki");
    // no target (blind random visible goblin for 3 turns)
    parser.parseSpell("urnum goblin bliki");
    // no monster name (weaken random visible monster forever)
    parser.parseSpell("verum weki");
    // no time, no target, but monster name (kill random visible goblin instantly)
    parser.parseSpell("goblin kirdiki");
    // no time, no target, no monster name (kill random visible monster instantly)
    parser.parseSpell("kirdiki");
    // error
    parser.parseSpell("abracadabra");
  }

  public Spell parseSpell(String line) {
    try {
      var context = new ParserContext(line);

      // time regulation -- optional (defaults to instant)
      TimeRegulation timeRegulation = parseTimeRegulation(context);

      // target regulation (optional, defaults to random visible target)
      QuantifierRegulation quantifierRegulation = getQuantifierRegulation(context);
      // backtracking parse
      String monsterName;
      if (parseActionRegulation(context, true) == null) {
        monsterName = context.currentToken;
        context.tokenConsumed();
      } else {
        monsterName = null;
      }
      TargetRegulation targetRegulation = new TargetRegulation(quantifierRegulation, monsterName);

      // action Regulation
      ActionRegulation actionRegulation = parseActionRegulation(context, false);

      return new Spell(timeRegulation, targetRegulation, actionRegulation);
    } catch (Exception e) {
      throw new IllegalArgumentException("Wrong spell", e);
    }
  }

  private QuantifierRegulation getQuantifierRegulation(ParserContext context) {
    QuantifierRegulation quantifierRegulation = recognizeToken(
        context.currentToken,
        QuantifierRegulation::values,
        QuantifierRegulation::word);
    if (quantifierRegulation == null) {
      quantifierRegulation = QuantifierRegulation.RANDOM_VISIBLE;
    } else {
      context.tokenConsumed();
    }
    return quantifierRegulation;
  }

  private TimeRegulation parseTimeRegulation(ParserContext context) {
    TimeRegulation timeRegulation = recognizeToken(
        context.currentToken,
        TimeRegulation::values,
        TimeRegulation::word);
    if (timeRegulation == null) {
      timeRegulation = TimeRegulation.INSTANT;
    } else {
      context.tokenConsumed();
    }
    return timeRegulation;
  }

  private ActionRegulation parseActionRegulation(ParserContext context, boolean preview) {
    ActionRegulation actionRegulation = recognizeToken(
        context.currentToken,
        ActionRegulation::values,
        ActionRegulation::word);
    if (actionRegulation == null) {
      if (!preview) {
        throw new IllegalArgumentException("wrong spell (no action regulation found)");
      }
    } else {
      if (!preview) {
        context.tokenConsumed();
      }
    }
    return actionRegulation;
  }

  <T extends Enum<T>> T recognizeToken(
      String token,
      Supplier<T[]> valueSource,
      Function<T, String> keyWord
  ) {
    T[] enumValues = valueSource.get();
    return Arrays.stream(enumValues)
        .filter(x -> token.equalsIgnoreCase(keyWord.apply(x)))
        .findFirst()
        .orElse(null);
  }

  private static class ParserContext {

    private final Deque<String> tokens;
    private String currentToken;

    private ParserContext(String text) {
      List<String> list = Arrays.stream(text.split(" "))
          .filter(s -> !s.isBlank())
          .map(String::trim)
          .toList();
      tokens = new LinkedList<>(list);
      if (tokens.isEmpty()) {
        throw new IllegalArgumentException("Empty input");
      } else {
        currentToken = tokens.removeFirst();
      }
    }

    private void tokenConsumed() {
      if (tokens.isEmpty()) {
        currentToken = null;
      } else {
        currentToken = tokens.removeFirst();
      }
    }
  }

}
