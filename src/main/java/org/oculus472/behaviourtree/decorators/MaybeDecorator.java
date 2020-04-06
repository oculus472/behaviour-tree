package org.oculus472.behaviourtree.decorators;

import java.util.Random;

public class MaybeDecorator<T> extends Decorator<T> {

  private final Random randomGenerator = new Random();

  @Override
  public State tick(T blackboard) {
    // We always return success because we want the boolean to decide if the action
    // should be performed, not the result of the child.tick call.
    if (randomGenerator.nextBoolean()) {
      child.tick(blackboard);

      return State.SUCCESS;
    }

    return State.SUCCESS;
  }
}
