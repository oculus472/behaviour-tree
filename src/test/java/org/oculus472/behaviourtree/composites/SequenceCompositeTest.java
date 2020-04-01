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

class SequenceCompositeTest {

  @Test
  void testReturnsFailedStateIfChildReturnsFailedState() {
    Node<TestBehaviourTree> tree =
        new BehaviourTreeBuilder<TestBehaviourTree>()
            .sequence()
                .action(bb -> State.SUCCESS)
                .action(bb -> State.FAILED)
                .action(bb -> State.RUNNING)
            .finish()
            .build();

    assertEquals(State.FAILED, tree.tick(TestBehaviourTree.getTree()));
  }

  @Test
  void testReturnsRunningStateIfChildReturnsRunningState() {
    Node<TestBehaviourTree> tree =
        new BehaviourTreeBuilder<TestBehaviourTree>()
            .sequence()
                .action(bb -> State.SUCCESS)
                .action(bb -> State.RUNNING)
                .action(bb -> State.SUCCESS)
            .finish()
            .build();

    assertEquals(State.RUNNING, tree.tick(TestBehaviourTree.getTree()));
  }

  @Test
  void testReturnsSuccessStateIfAllChildrenReturnSuccessState() {
    Node<TestBehaviourTree> tree =
        new BehaviourTreeBuilder<TestBehaviourTree>()
            .sequence()
                .action(bb -> State.SUCCESS)
                .action(bb -> State.SUCCESS)
                .action(bb -> State.SUCCESS)
            .finish()
            .build();

    assertEquals(State.SUCCESS, tree.tick(TestBehaviourTree.getTree()));
  }

  @ParameterizedTest(name = "{index} => state = {0}")
  @EnumSource(
      value = State.class,
      names = {"FAILED", "RUNNING"})
  void testReturnsWhenChildReturnsState(State state) {
    ActionLeaf<TestBehaviourTree> actionLeafMock = mock(ActionLeaf.class);
    SequenceComposite<TestBehaviourTree> node = new SequenceComposite<TestBehaviourTree>();

    node.registerChild(new ActionLeaf<TestBehaviourTree>().registerTask(bb -> State.SUCCESS));
    node.registerChild(new ActionLeaf<TestBehaviourTree>().registerTask(bb -> state));
    node.registerChild(actionLeafMock.registerTask(bb -> State.SUCCESS));
    TestBehaviourTree tree = TestBehaviourTree.getTree();

    node.tick(tree);
    verify(actionLeafMock, times(0)).tick(tree);
  }
}
