package org.oculus472.behaviourtree.composites;

import java.util.ArrayList;
import java.util.ListIterator;
import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.ParentNode;

public abstract class Composite<T> extends ParentNode<T> {

  private ArrayList<Node<T>> children = new ArrayList<Node<T>>();

  @Override
  public State tick(T blackboard) {
    ListIterator<Node<T>> iterator = children.listIterator();

    do {
      State state = iterator.next().tick(blackboard);

      if (shouldReturnState(state)) {
        return state;
      }
    } while (iterator.hasNext());

    return getDefaultState();
  }

  public <RT extends ParentNode<T>> RT registerChild(Node<T> child) {
    this.children.add(child);

    return (RT) this;
  }

  protected abstract boolean shouldReturnState(State state);

  protected abstract State getDefaultState();
}
