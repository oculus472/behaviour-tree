package org.oculus472.behaviourtree.decorators;

public class SucceederDecorator<T> extends Decorator<T> {

  @Override
  public State tick(T blackboard) {
    child.tick(blackboard);

    return State.SUCCESS;
  }
}
