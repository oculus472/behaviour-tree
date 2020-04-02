package org.oculus472.behaviourtree.composites;

public class SelectorComposite<T> extends Composite<T> {

  protected boolean shouldReturnState(State state) {
    return state == State.RUNNING || state == State.SUCCESS;
  }

  protected State getDefaultState() {
    return State.FAILED;
  }
}
