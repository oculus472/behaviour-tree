package org.oculus472.behaviourtree.decorators;

public class SucceederDecorator<BlackboardType> extends Decorator<BlackboardType> {

  @Override
  public State tick(BlackboardType blackboard) {
    child.tick(blackboard);

    return State.SUCCESS;
  }
}
