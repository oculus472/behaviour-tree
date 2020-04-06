package org.oculus472.behaviourtree.composites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.oculus472.behaviourtree.BehaviourTreeBuilder;
import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.Node.State;
import org.oculus472.behaviourtree.TestBlackboard;
import org.oculus472.behaviourtree.leafs.ActionLeaf;

class SelectorCompositeTest {
  @Test
  void testReturnsFailedStateIfAllChildrenReturnFailedState() {
    Node<TestBlackboard> tree =
        new BehaviourTreeBuilder<TestBlackboard>()
            .selector()
                .action(bb -> State.FAILED)
                .action(bb -> State.FAILED)
                .action(bb -> State.FAILED)
            .finish()
            .build();

    assertEquals(State.FAILED, tree.tick(TestBlackboard.getBlackboard()));
  }

  @Test
  void testReturnsSuccessStateIfChildReturnsSuccessState() {
    Node<TestBlackboard> tree =
        new BehaviourTreeBuilder<TestBlackboard>()
            .selector()
                .action(bb -> State.FAILED)
                .action(bb -> State.SUCCESS)
                .action(bb -> State.FAILED)
            .finish()
            .build();

    assertEquals(State.SUCCESS, tree.tick(TestBlackboard.getBlackboard()));
  }

  @Test
  void testReturnsRunningStateIfChildReturnsRunningState() {
    Node<TestBlackboard> tree =
        new BehaviourTreeBuilder<TestBlackboard>()
            .selector()
                .action(bb -> State.FAILED)
                .action(bb -> State.RUNNING)
                .action(bb -> State.FAILED)
            .finish()
            .build();

    assertEquals(State.RUNNING, tree.tick(TestBlackboard.getBlackboard()));
  }

  @ParameterizedTest(name = "{index} => state = {0}")
  @EnumSource(
      value = State.class,
      names = {"SUCCESS", "RUNNING"})
  void testReturnsWhenChildReturnsState(State state) {
    ActionLeaf<TestBlackboard> actionLeafMock = mock(ActionLeaf.class);
    Node<TestBlackboard> node =
        new BehaviourTreeBuilder<TestBlackboard>()
          .selector()
            .action(bb -> State.FAILED)
            .action(bb -> state)
            .action(actionLeafMock)
          .finish()
          .build();
    TestBlackboard blackboard = TestBlackboard.getBlackboard();

    node.tick(blackboard);
    verify(actionLeafMock, times(0)).tick(blackboard);
  }
}
