package org.oculus472.behaviourtree.composites;

public final class SelectorComposite<T> extends Composite<T> {

  @Override
  protected boolean shouldReturnState(State state) {
    return state == State.RUNNING || state == State.SUCCESS;
  }

  @Override
  protected State getDefaultState() {
    return State.FAILED;
  }
}
