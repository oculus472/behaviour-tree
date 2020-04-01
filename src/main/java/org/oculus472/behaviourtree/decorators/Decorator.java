package org.oculus472.behaviourtree.decorators;

import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.ParentNode;

public abstract class Decorator<BlackboardType> extends ParentNode<BlackboardType> {
  protected Node<BlackboardType> child;

  public boolean registerChild(Node<BlackboardType> child) {
    if (this.child != null) {
      return false;
    }

    this.child = child;

    return true;
  }
}
