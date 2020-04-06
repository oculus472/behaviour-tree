package org.oculus472.behaviourtree.decorators;

/**
 * Ticks the decorated {@link org.oculus472.behaviourtree.Node} the specified amount of times. Any
 * subsequent ticks will return a SUCCESS state without ticking the decorated node.
 *
 * @param <T> Behaviour tree blackboard type.
 */
public final class RepeatUntilCountDecorator<T> extends Decorator<T> {

  private int repeatCount = -1;

  /**
   * @param repeatCount The amount of times to tick the decorated {@link org.oculus472.behaviourtree.Node}.
   */
  public RepeatUntilCountDecorator(int repeatCount) {
    this.repeatCount = repeatCount;
  }

  @Override
  public State tick(T blackboard) {
    if (repeatCount == 0) {
      return State.SUCCESS;
    }

    repeatCount -= 1;

    super.tick(blackboard);

    return State.RUNNING;

  }
}
