package org.oculus472.behaviourtree.composites;

public class SelectorComposite<BlackboardType> extends Composite<BlackboardType> {

  protected boolean shouldReturnState(State state) {
    return state == State.RUNNING || state == State.SUCCESS;
  }

  protected State getDefaultState() {
    return State.FAILED;
  }
}
