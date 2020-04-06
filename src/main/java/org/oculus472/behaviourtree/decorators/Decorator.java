package org.oculus472.behaviourtree.decorators;

import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.ParentNode;

public abstract class Decorator<T> extends ParentNode<T> {

  protected Node<T> child;

  @Override
  public <RT extends ParentNode<T>> RT registerChild(Node<T> child) {
    if (this.child != null) {
      return (RT) this;
    }

    this.child = child;

    return (RT) this;
  }

  @Override
  public State tick(T blackboard) {
    return child.tick(blackboard);
  }
}
