package org.oculus472.behaviourtree.leafs;

import org.oculus472.behaviourtree.Node.State;

public class ActionLeaf<T> extends Leaf<T, State> {

  public State tick(T blackboard) {
    return task.apply(blackboard);
  }
}
