package org.oculus472.behaviourtree.composites;

public class SequenceComposite<T> extends Composite<T> {

  protected boolean shouldReturnState(State state) {
    return state == State.FAILED || state == State.RUNNING;
  }

  protected State getDefaultState() {
    return State.SUCCESS;
  }
}
