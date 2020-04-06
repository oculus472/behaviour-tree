package org.oculus472.behaviourtree.leafs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.oculus472.behaviourtree.Node.State;
import org.oculus472.behaviourtree.TestBlackboard;

class ConditionLeafTest {

  @Test
  void testReturnsSuccessStateIfTaskReturnedTrue() {
    ConditionLeaf<TestBlackboard> node = new ConditionLeaf<TestBlackboard>().registerTask(bb -> true);

    assertEquals(State.SUCCESS, node.tick(TestBlackboard.getBlackboard()));
  }

  @Test
  void testReturnsFailedStateIfTaskReturnedFalse() {
    ConditionLeaf<TestBlackboard> node = new ConditionLeaf<TestBlackboard>().registerTask(bb -> false);

    assertEquals(State.FAILED, node.tick(TestBlackboard.getBlackboard()));
  }
}
