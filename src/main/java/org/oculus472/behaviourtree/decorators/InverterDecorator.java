package org.oculus472.behaviourtree.decorators;

public final class InverterDecorator<T> extends Decorator<T> {

  @Override
  public State tick(T blackboard) {
    State state = super.tick(blackboard);

    if (state == State.RUNNING) {
      return state;
    }

    return state == State.SUCCESS ? State.FAILED : State.SUCCESS;
  }
}
