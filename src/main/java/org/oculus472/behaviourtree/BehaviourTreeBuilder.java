package org.oculus472.behaviourtree;

import java.util.Stack;
import java.util.function.Function;
import org.oculus472.behaviourtree.Node.State;
import org.oculus472.behaviourtree.composites.SelectorComposite;
import org.oculus472.behaviourtree.composites.SequenceComposite;
import org.oculus472.behaviourtree.decorators.SucceederDecorator;
import org.oculus472.behaviourtree.leafs.ActionLeaf;
import org.oculus472.behaviourtree.leafs.ConditionLeaf;

public class BehaviourTreeBuilder<BlackboardType> {
  Node<BlackboardType> currentNode;
  private Stack<ParentNode<BlackboardType>> parentNodeStack = new Stack<>();

  public BehaviourTreeBuilder<BlackboardType> action(Function<BlackboardType, State> task) {
    // TODO: ensure parent stack isn't empty.

    ActionLeaf<BlackboardType> node = new ActionLeaf<BlackboardType>().registerTask(task);

    parentNodeStack.peek().registerChild(node);

    return this;
  }

  public BehaviourTreeBuilder<BlackboardType> condition(Function<BlackboardType, Boolean> task) {
    // TODO: ensure parent stack isn't empty.

    ConditionLeaf<BlackboardType> node = new ConditionLeaf<BlackboardType>().registerTask(task);

    parentNodeStack.peek().registerChild(node);

    return this;
  }

  public BehaviourTreeBuilder<BlackboardType> sequence() {
    return registerParent(new SequenceComposite<>());
  }

  public BehaviourTreeBuilder<BlackboardType> selector() {
    return registerParent(new SelectorComposite<>());
  }

  public BehaviourTreeBuilder<BlackboardType> succeeder() {
    return registerParent(new SucceederDecorator<>());
  }

  public BehaviourTreeBuilder<BlackboardType> registerParent(ParentNode<BlackboardType> node) {
    if (!parentNodeStack.empty()) {
      parentNodeStack.peek().registerChild(node);
    }

    parentNodeStack.push(node);

    return this;
  }

  public BehaviourTreeBuilder<BlackboardType> finish() {
    currentNode = parentNodeStack.pop();

    return this;
  }

  public Node<BlackboardType> build() {
    return currentNode;
  }
}
