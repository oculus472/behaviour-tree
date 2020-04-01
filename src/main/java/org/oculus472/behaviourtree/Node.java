package org.oculus472.behaviourtree;

public abstract class Node<BlackboardType> {
  public static enum State {
    FAILED,
    RUNNING,
    SUCCESS,
  }

  public static enum Type {
    SEQUENCE_COMPOSITE,
    SELECTOR_COMPOSITE,
    CONDITIONAL_LEAF,
    ACTION_LEAF,
  }

  public abstract State tick(BlackboardType blackboard);
}