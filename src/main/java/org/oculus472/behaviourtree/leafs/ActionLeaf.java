package org.oculus472.behaviourtree.leafs;

import org.oculus472.behaviourtree.Node.State;

public class ActionLeaf<BlackboardType> extends Leaf<BlackboardType, State> {

  public State tick(BlackboardType blackboard) {
    return task.apply(blackboard);
  }
}
