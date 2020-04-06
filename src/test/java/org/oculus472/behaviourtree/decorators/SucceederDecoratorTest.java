package org.oculus472.behaviourtree.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.oculus472.behaviourtree.BehaviourTreeBuilder;
import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.Node.State;
import org.oculus472.behaviourtree.TestBlackboard;

class SucceederDecoratorTest {

  @ParameterizedTest
  @EnumSource(State.class)
  void testAlwaysReturnsSuccessState(State state) {
    Node<TestBlackboard> tree = new BehaviourTreeBuilder<TestBlackboard>()
        .succeed()
            .action(bb -> state)
        .finish()
        .build();

    assertEquals(State.SUCCESS, tree.tick(TestBlackboard.getBlackboard()));
  }

  @Test
  void testAlwaysReturnsSuccessStateComplexTree() {
    Node<TestBlackboard> tree = new BehaviourTreeBuilder<TestBlackboard>()
        .succeed()
            .sequence()
                .action(bb -> State.RUNNING)
                .action(bb -> State.FAILED)
            .finish()
            .selector()
                .condition(bb -> false)
                .action(bb -> State.FAILED)
            .finish()
        .finish()
        .build();

    assertEquals(State.SUCCESS, tree.tick(TestBlackboard.getBlackboard()));
  }
}
