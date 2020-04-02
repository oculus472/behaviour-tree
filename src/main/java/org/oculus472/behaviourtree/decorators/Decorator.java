package org.oculus472.behaviourtree.decorators;

import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.ParentNode;

public abstract class Decorator<T> extends ParentNode<T> {
  protected Node<T> child;

  public boolean registerChild(Node<T> child) {
    if (this.child != null) {
      return false;
    }

    this.child = child;

    return true;
  }
}
