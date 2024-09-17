package ask.urfu.misc.patterns.fantasygame.util;

import java.util.List;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class LazyRandom implements RandomGenerator {

  private final Supplier<RandomGenerator> instanceSource;
  private RandomGenerator generator;

  public LazyRandom(Supplier<RandomGenerator> instanceSource) {
    this.instanceSource = instanceSource;
  }

  @Override
  public boolean isDeprecated() {
    return false;
  }

  public boolean chance(int percent) {
    return nextInt(100) < percent;
  }

  public <X extends Enum<X>> X nextEnum(Supplier<X[]> valueSource) {
    X[] vals = valueSource.get();
    return vals[nextInt(vals.length)];
  }

  public <X> X nextOf(X[] values) {
    return values.length > 0
        ? values[nextInt(values.length)]
        : null;
  }

  public <X> X nextOf(List<X> options) {
    int size = options.size();
    return size > 0
        ? options.get(nextInt(size))
        : null;
  }

  //region Delegation

  @Override
  public DoubleStream doubles() {
    return generator().doubles();
  }

  @Override
  public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
    return generator().doubles(randomNumberOrigin, randomNumberBound);
  }

  @Override
  public DoubleStream doubles(long streamSize) {
    return generator().doubles(streamSize);
  }

  @Override
  public DoubleStream doubles(long streamSize, double randomNumberOrigin,
      double randomNumberBound) {
    return generator().doubles(streamSize, randomNumberOrigin, randomNumberBound);
  }

  @Override
  public IntStream ints() {
    return generator().ints();
  }

  @Override
  public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
    return generator().ints(randomNumberOrigin, randomNumberBound);
  }

  @Override
  public IntStream ints(long streamSize) {
    return generator().ints(streamSize);
  }

  @Override
  public IntStream ints(long streamSize, int randomNumberOrigin,
      int randomNumberBound) {
    return generator().ints(streamSize, randomNumberOrigin, randomNumberBound);
  }

  @Override
  public LongStream longs() {
    return generator().longs();
  }

  @Override
  public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
    return generator().longs(randomNumberOrigin, randomNumberBound);
  }

  @Override
  public LongStream longs(long streamSize) {
    return generator().longs(streamSize);
  }

  @Override
  public LongStream longs(long streamSize, long randomNumberOrigin,
      long randomNumberBound) {
    return generator().longs(streamSize, randomNumberOrigin, randomNumberBound);
  }

  @Override
  public boolean nextBoolean() {
    return generator().nextBoolean();
  }

  @Override
  public void nextBytes(byte[] bytes) {
    generator().nextBytes(bytes);
  }

  @Override
  public float nextFloat() {
    return generator().nextFloat();
  }

  @Override
  public float nextFloat(float bound) {
    return generator().nextFloat(bound);
  }

  @Override
  public float nextFloat(float origin, float bound) {
    return generator().nextFloat(origin, bound);
  }

  @Override
  public double nextDouble() {
    return generator().nextDouble();
  }

  @Override
  public double nextDouble(double bound) {
    return generator().nextDouble(bound);
  }

  @Override
  public double nextDouble(double origin, double bound) {
    return generator().nextDouble(origin, bound);
  }

  @Override
  public int nextInt() {
    return generator().nextInt();
  }

  @Override
  public int nextInt(int bound) {
    return generator().nextInt(bound);
  }

  @Override
  public int nextInt(int origin, int bound) {
    return generator().nextInt(origin, bound);
  }

  @Override
  public long nextLong() {
    return generator().nextLong();
  }

  @Override
  public long nextLong(long bound) {
    return generator().nextLong(bound);
  }

  @Override
  public long nextLong(long origin, long bound) {
    return generator().nextLong(origin, bound);
  }

  @Override
  public double nextGaussian() {
    return generator().nextGaussian();
  }

  @Override
  public double nextGaussian(double mean, double stddev) {
    return generator().nextGaussian(mean, stddev);
  }

  @Override
  public double nextExponential() {
    return generator().nextExponential();
  }

  //endregion

  private RandomGenerator generator() {
    if (generator == null) {
      generator = instanceSource.get();
    }
    return generator;
  }

}
