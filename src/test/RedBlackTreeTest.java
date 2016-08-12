import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.log;

public class RedBlackTreeTest {

  private RedBlackTree<Integer> tree;
  private int[] numbers = {1, 2, 3, 4, 5, 6, 7};

  @Before
  public void setup() {
    tree = new RedBlackTree<Integer>(numbers[0]);
    for (int i = 1;i<numbers.length;i++) {
      tree = tree.insert(numbers[i]);
    }
  }

  @Test
  public void testContains() {
    Assert.assertTrue(tree.contains(numbers[1]));
  }

  @Test
  public void testLookUpNode() throws Exception {
    Assert.assertEquals((long)tree.lookUpNode(numbers[1]).getValue(),numbers[1]);
  }

  @Test
  public void testMin() throws Exception {
    Assert.assertEquals(tree.min().getValue(), Collections.min(Arrays.asList(ArrayUtils.toObject(numbers))));
  }

  @Test
  public void testMax() throws Exception {
    Assert.assertEquals(tree.max().getValue(), Collections.max(Arrays.asList(ArrayUtils.toObject(numbers))));
  }

  @Test
  public void testInsert() throws Exception {
    tree = tree.insert(24);
    Assert.assertTrue(tree.contains(24));
  }

  @Test
  public void testDelete() throws Exception {
    tree = tree.insert(-30);
    tree = tree.delete(-30);
    Assert.assertFalse(tree.contains(-30));
    tree = tree.delete(4);
    Assert.assertFalse(tree.contains(4));
  }

  @Test
  public void simpleTestGetHeigth() throws Exception {
    //create a new empty tree with one element
    RedBlackTree<Integer> test = new RedBlackTree<>(3);
    //the heigth of the tree should be 1
    Assert.assertEquals(1, test.getHeight());
  }

  @Test
  public void testInsertBalancing() throws Exception {
    int height = tree.getHeight();
    Assert.assertTrue(height <= (2 * log(numbers.length + 1)));
  }
}