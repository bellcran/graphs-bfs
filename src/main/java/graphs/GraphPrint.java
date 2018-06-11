package graphs;

import java.util.ArrayList;
import java.util.List;

public class GraphPrint {

  final String name;
  final List<GraphPrint> children;
  boolean isTail;

  public GraphPrint(String name, List<GraphPrint> children) {
    this.name = name;
    this.children = children;
    this.isTail = true;
  }

  public GraphPrint(String name, boolean isTail) {
    this.name = name;
    this.children = new ArrayList<>();
    ;
    this.isTail = isTail;
  }

  public void addChild(GraphPrint child) {
    this.children.add(child);
  }

  public void print() {
    print("", isTail);
  }

  private void print(String prefix, boolean isTail) {
    System.out.println(prefix + (isTail ? "└── " : "├── ") + name);
    for (int i = 0; i < children.size() - 1; i++) {
      children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
    }
    if (children.size() > 0) {
      children.get(children.size() - 1).print(prefix + (isTail ? "    " : "│   "), true);
    }
  }

  /*
   * public static void main(String[] args) {
   * 
   * GraphPrint bb = new GraphPrint("b", false);
   * 
   * List<GraphPrint> ccChild = new ArrayList<>(); ccChild.add(new
   * GraphPrint("c1", false)); ccChild.add(new GraphPrint("c2", false));
   * GraphPrint cc = new GraphPrint("c", ccChild);
   * 
   * GraphPrint dd = new GraphPrint("d", false);
   * 
   * 
   * List<GraphPrint> children = new ArrayList<>();
   * 
   * children.add(bb); children.add(cc); children.add(dd);
   * 
   * GraphPrint tn = new GraphPrint("a", children); tn.print(); }
   */
}