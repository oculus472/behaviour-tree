package org.oculus472.behaviourtree.leafs;

public class ConditionLeaf<BlackboardType> extends Leaf<BlackboardType, Boolean> {

  public State tick(BlackboardType blackboard) {
    return task.apply(blackboard) ? State.SUCCESS : State.FAILED;
  }
}
