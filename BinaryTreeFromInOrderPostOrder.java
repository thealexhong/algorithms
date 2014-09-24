import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

import java.lang.Integer;
import java.lang.NumberFormatException;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}

public class BinaryTreeFromInOrderPostOrder {

  public static TreeNode buildTree(int[] inorder, int[] postorder)
  {
    int inStart = 0;
    int inEnd = inorder.length - 1;
    int postStart = 0;
    int postEnd = postorder.length - 1;
    return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
  }
  
  public static TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                                   int[] postorder, int postStart, int postEnd)
  {
    if (inStart > inEnd || postStart > postEnd)
      return null;
    int rootValue = postorder[postEnd];
    TreeNode root = new TreeNode(rootValue);
    
    int k = 0;
    for (int i = 0; i < inorder.length; ++i)
    {
      if(inorder[i] == rootValue)
      {
        k = i;
        break;
      }
    }
    root.left = buildTree(inorder, inStart, k - 1,
                          postorder, postStart, postStart + k - (inStart + 1));
    root.right = buildTree(inorder, k + 1, inEnd,
                           postorder, postStart + k - inStart, postEnd - 1);
    
    return root;
  }
  
  public static ArrayList<Integer> breadthTree(TreeNode root)
  {
    if (root == null)
      return null;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    ArrayList<Integer> breadth = new ArrayList<Integer>();
    queue.clear();
    queue.add(root);
    while(!queue.isEmpty())
    {
      TreeNode node = queue.remove();
      breadth.add(node.val);
      if(node.left != null) queue.add(node.left);
      if(node.right != null) queue.add(node.right);
    }
    return breadth;
  }
  
  public static void main (String[] args) throws IOException {
      
    Scanner sc = null;
    PrintWriter pw = null;
    //BufferedReader br = null;
      
    try
    {
      sc = new Scanner(new BufferedReader(new FileReader("DATA1.txt")));
      //br = new BufferedReader(new FileReader("DATA.txt"));
      pw = new PrintWriter(new FileWriter("OUT1.txt"));
          
      while (sc.hasNext())
      {
        String[] inorderStr = sc.next().split(",");
        String[] postorderStr = sc.next().split(",");
        int[] inorder = new int[inorderStr.length];
        int[] postorder = new int[postorderStr.length];
        for (int i = 0; i < inorder.length; i++)
        {
          try
          {
            inorder[i] = Integer.parseInt(inorderStr[i]);
            postorder[i] = Integer.parseInt(postorderStr[i]);
          }
          catch (NumberFormatException e)
          {}
        }
        TreeNode tn = buildTree(inorder, postorder);
        ArrayList<Integer> breadth = breadthTree(tn);
        if (breadth != null) {
          for (int i = 0; i < breadth.size(); i++)
          {
            if (i == breadth.size() - 1)
              pw.println (breadth.get(i));
            else
              pw.print(breadth.get(i) + ",");
          }
        }
      }
    }
    finally
    {
      if (sc != null)
        sc.close();
      if (pw != null)
        pw.close();
      }
  }
}