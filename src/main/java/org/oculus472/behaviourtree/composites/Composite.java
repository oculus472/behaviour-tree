package org.oculus472.behaviourtree.composites;

import java.util.ArrayList;
import java.util.ListIterator;
import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.ParentNode;

public abstract class Composite<T> extends ParentNode<T> {
  private final ArrayList<Node<T>> children = new ArrayList<>();
  private int currentChildIndex = 0;

  @Override
  public State tick(T blackboard) {
    for (; currentChildIndex < children.size(); currentChildIndex += 1) {
      State state = children.get(currentChildIndex).tick(blackboard);

      if (shouldReturnState(state)) {
        // So we don't repeat the current node.
        currentChildIndex++;

        return state;
      }
    }

    resetIteration();

    return getDefaultState();
  }

  public <RT extends ParentNode<T>> RT registerChild(Node<T> child) {
    this.children.add(child);

    return (RT) this;
  }

  private void resetIteration() {
    currentChildIndex = -1;
  }

  protected abstract boolean shouldReturnState(State state);

  protected abstract State getDefaultState();
}
