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
import org.oculus472.behaviourtree.TestBehaviourTree;
import org.oculus472.behaviourtree.leafs.ActionLeaf;

class SelectorCompositeTest {
  @Test
  void testReturnsFailedStateIfAllChildrenReturnFailedState() {
    Node<TestBehaviourTree> tree =
        new BehaviourTreeBuilder<TestBehaviourTree>()
            .selector()
                .action(bb -> State.FAILED)
                .action(bb -> State.FAILED)
                .action(bb -> State.FAILED)
            .finish()
            .build();

    assertEquals(State.FAILED, tree.tick(TestBehaviourTree.getTree()));
  }

  @Test
  void testReturnsSuccessStateIfChildReturnsSuccessState() {
    Node<TestBehaviourTree> tree =
        new BehaviourTreeBuilder<TestBehaviourTree>()
            .selector()
                .action(bb -> State.FAILED)
                .action(bb -> State.SUCCESS)
                .action(bb -> State.FAILED)
            .finish()
            .build();

    assertEquals(State.SUCCESS, tree.tick(TestBehaviourTree.getTree()));
  }

  @Test
  void testReturnsRunningStateIfChildReturnsRunningState() {
    Node<TestBehaviourTree> tree =
        new BehaviourTreeBuilder<TestBehaviourTree>()
            .selector()
                .action(bb -> State.FAILED)
                .action(bb -> State.RUNNING)
                .action(bb -> State.FAILED)
            .finish()
            .build();

    assertEquals(State.RUNNING, tree.tick(TestBehaviourTree.getTree()));
  }

  @ParameterizedTest(name = "{index} => state = {0}")
  @EnumSource(
      value = State.class,
      names = {"SUCCESS", "RUNNING"})
  void testReturnsWhenChildReturnsState(State state) {
    ActionLeaf<TestBehaviourTree> actionLeafMock = mock(ActionLeaf.class);
    SelectorComposite<TestBehaviourTree> node = new SelectorComposite<TestBehaviourTree>();
    node.registerChild(new ActionLeaf<TestBehaviourTree>().registerTask(bb -> State.FAILED));
    node.registerChild(new ActionLeaf<TestBehaviourTree>().registerTask(bb -> state));
    node.registerChild(actionLeafMock.registerTask(bb -> State.FAILED));
    TestBehaviourTree tree = TestBehaviourTree.getTree();

    node.tick(tree);
    verify(actionLeafMock, times(0)).tick(tree);
  }
}
