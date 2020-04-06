package org.oculus472.behaviourtree.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.oculus472.behaviourtree.BehaviourTreeBuilder;
import org.oculus472.behaviourtree.Node;
import org.oculus472.behaviourtree.Node.State;
import org.oculus472.behaviourtree.TestBlackboard;

class RepeatUntilCountDecoratorTest {

  @ParameterizedTest
  @EnumSource(State.class)
  void testReturnsRunningUntilTickedCountAmountOfTimes(State state) {
    TestBlackboard blackboard = TestBlackboard.getBlackboard();
    Node<TestBlackboard> node = new BehaviourTreeBuilder<TestBlackboard>()
        .repeatUntilCount(4)
          .action(bb -> state)
        .finish()
        .build();

    assertEquals(State.RUNNING, node.tick(blackboard));
    assertEquals(State.RUNNING, node.tick(blackboard));
    assertEquals(State.RUNNING, node.tick(blackboard));
    assertEquals(state.RUNNING, node.tick(blackboard));
    assertEquals(state.SUCCESS, node.tick(blackboard));
  }
}