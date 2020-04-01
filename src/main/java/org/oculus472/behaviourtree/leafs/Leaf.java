package org.oculus472.behaviourtree.leafs;

import java.util.function.Function;
import org.oculus472.behaviourtree.Node;

public abstract class Leaf<BlackboardType, TaskReturnType> extends Node<BlackboardType> {
  protected Function<BlackboardType, TaskReturnType> task;

  public <T extends Leaf<BlackboardType, TaskReturnType>> T registerTask(
      Function<BlackboardType, TaskReturnType> task) {
    this.task = task;

    return (T) this;
  }

  public abstract State tick(BlackboardType blackboard);
}
