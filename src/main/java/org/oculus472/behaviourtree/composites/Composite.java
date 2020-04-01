package org.oculus472.behaviourtree.composites;

import java.util.ArrayList;
import java.util.ListIterator;
import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.ParentNode;

public abstract class Composite<BlackboardType> extends ParentNode<BlackboardType> {
  private ArrayList<Node<BlackboardType>> children = new ArrayList<Node<BlackboardType>>();

  public State tick(BlackboardType blackboard) {
    ListIterator<Node<BlackboardType>> iterator = children.listIterator();

    do {
      State state = iterator.next().tick(blackboard);

      if (shouldReturnState(state)) {
        return state;
      }
    } while (iterator.hasNext());

    return getDefaultState();
  }

  public boolean registerChild(Node<BlackboardType> child) {
    return this.children.add(child);
  }

  protected abstract boolean shouldReturnState(State state);

  protected abstract State getDefaultState();
}
