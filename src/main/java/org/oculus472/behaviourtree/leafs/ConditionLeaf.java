package org.oculus472.behaviourtree.leafs;

public class ConditionLeaf<T> extends Leaf<T, Boolean> {

  public State tick(T blackboard) {
    return task.apply(blackboard) ? State.SUCCESS : State.FAILED;
  }
}
