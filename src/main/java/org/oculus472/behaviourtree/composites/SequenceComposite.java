package org.oculus472.behaviourtree.composites;

public class SequenceComposite<BlackboardType> extends Composite<BlackboardType> {

  protected boolean shouldReturnState(State state) {
    return state == State.FAILED || state == State.RUNNING;
  }

  protected State getDefaultState() {
    return State.SUCCESS;
  }
}
