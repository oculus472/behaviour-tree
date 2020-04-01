package org.oculus472.behaviourtree.leafs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.oculus472.behaviourtree.Node.State;
import org.oculus472.behaviourtree.TestBehaviourTree;

class ConditionLeafTest {

  @Test
  void testReturnsSuccessStateIfTaskReturnedTrue() {
    ConditionLeaf<TestBehaviourTree> node = new ConditionLeaf<TestBehaviourTree>().registerTask(bb -> true);

    assertEquals(State.SUCCESS, node.tick(TestBehaviourTree.getTree()));
  }

  @Test
  void testReturnsFailedStateIfTaskReturnedFalse() {
    ConditionLeaf<TestBehaviourTree> node = new ConditionLeaf<TestBehaviourTree>().registerTask(bb -> false);

    assertEquals(State.FAILED, node.tick(TestBehaviourTree.getTree()));
  }
}
