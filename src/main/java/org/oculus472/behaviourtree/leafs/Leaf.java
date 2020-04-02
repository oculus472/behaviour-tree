package org.oculus472.behaviourtree.leafs;

import java.util.function.Function;
import org.oculus472.behaviourtree.Node;

public abstract class Leaf<T, R> extends Node<T> {
  protected Function<T, R> task;

  public <RT extends Leaf<T, R>> RT registerTask(
      Function<T, R> task) {
    this.task = task;

    return (RT) this;
  }

  public abstract State tick(T blackboard);
}
