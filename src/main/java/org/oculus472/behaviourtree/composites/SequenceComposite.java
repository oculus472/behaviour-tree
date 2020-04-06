package org.oculus472.behaviourtree.composites;

public final class SequenceComposite<T> extends Composite<T> {

  @Override
  protected boolean shouldReturnState(State state) {
    return state == State.FAILED || state == State.RUNNING;
  }

  @Override
  protected State getDefaultState() {
    return State.SUCCESS;
  }
}
