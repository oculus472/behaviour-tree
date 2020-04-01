package org.oculus472.behaviourtree;

public abstract class ParentNode<BlackboardType> extends Node<BlackboardType> {
  public abstract boolean registerChild(Node<BlackboardType> child);
}
