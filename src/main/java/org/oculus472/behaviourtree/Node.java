package org.oculus472.behaviourtree;

public abstract class Node<T> {
  public enum State {
    FAILED,
    RUNNING,
    SUCCESS,
  }

  public abstract State tick(T blackboard);
}