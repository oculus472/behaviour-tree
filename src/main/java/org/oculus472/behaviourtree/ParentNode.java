package org.oculus472.behaviourtree;

public abstract class ParentNode<T> extends Node<T> {
  public abstract boolean registerChild(Node<T> child);
}
