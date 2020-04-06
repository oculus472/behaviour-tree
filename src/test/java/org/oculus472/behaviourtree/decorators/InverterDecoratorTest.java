package org.oculus472.behaviourtree.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.oculus472.behaviourtree.BehaviourTreeBuilder;
import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.Node.State;
import org.oculus472.behaviourtree.TestBlackboard;

class InverterDecoratorTest {

  @Test
  void testReturnsFailedStateIfChildReturnsSuccess() {
    Node<TestBlackboard> node = new BehaviourTreeBuilder<TestBlackboard>()
        .invert()
          .action(bb -> State.SUCCESS)
        .finish()
        .build();

    assertEquals(State.FAILED, node.tick(TestBlackboard.getBlackboard()));
  }

  @Test
  void testReturnsSuccessStateIfChildReturnsFailed() {
    Node<TestBlackboard> node = new BehaviourTreeBuilder<TestBlackboard>()
        .invert()
          .action(bb -> State.FAILED)
        .finish()
        .build();

    assertEquals(State.SUCCESS, node.tick(TestBlackboard.getBlackboard()));
  }

  @Test
  void testReturnsRunningStateIfChildReturnsRunning() {
    Node<TestBlackboard> node = new BehaviourTreeBuilder<TestBlackboard>()
        .invert()
          .action(bb -> State.RUNNING)
        .finish()
        .build();

    assertEquals(State.RUNNING, node.tick(TestBlackboard.getBlackboard()));
  }
}