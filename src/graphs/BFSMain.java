/**
 * 유향, 무향그래프 너비우선탐색 테스트
 */
package graphs;

import java.util.Iterator;

public class BFSMain {

  public static void main(String args[])
  {
    // 0 ~ 6 까지 인덱스 번호가 부여된 노드를 만든다.
    int V = 7;
    Graph g = new Graph(V); // 노드 인덱스는 0부터 시작
    
    // 노드에 유향그래프 속성을 부여한다. 
    g.addDirectedEdge(0, 1);
    g.addDirectedEdge(0, 2);
    g.addDirectedEdge(2, 3);
    g.addDirectedEdge(2, 4);
    g.addDirectedEdge(2, 5);
    g.addDirectedEdge(4, 6);
    
    g.setTerminalYn(new int[] {4}); // terminalYn 을 true 로 셋팅하거나 
                                    // 유향그래프에서 인접노드가 없을 경우 말단노드

    System.out.print("==> 노드 2의 인접노드: ");
    System.out.println(g.getAdj(2)); // 노드2와 연결된 인접노드 목록을 출력한다.
    System.out.println("");
    
    System.out.println("==> 모든 노드 출력");
    // 모든 노드 출력
    Iterator itrV = g.iterateVertices();
    while (itrV.hasNext()) {
      int v = (int)itrV.next();
      System.out.printf("** Node : %d\n",v);
      
      if (!g.getTerminalYn(v)) {
        Iterator itrE = g.iterateEdges(v);
        System.out.print("Adjacency Node : ");
        while (itrE.hasNext()) {
          System.out.print(itrE.next()+" ");
        }
        
        System.out.println("\n");

      } else {
        System.out.println("Terminal Node\n");
      }

    }
    
    /*
    V = 4;
    g = new Graph(V);
    
    g.addDirectedEdge(0, 1);
    g.addDirectedEdge(0, 2);
    g.addDirectedEdge(1, 2);
    g.addDirectedEdge(2, 0);
    g.addDirectedEdge(2, 3);
    g.addDirectedEdge(3, 3);
    */
    
    // 유향그래프를 만든다.
    V = 8;
    g = new Graph(V);
    g.addDirectedEdge(0, 1);
    g.addDirectedEdge(1, 2);
    g.addDirectedEdge(2, 3);
    g.addDirectedEdge(3, 4);
    g.addDirectedEdge(3, 5);
    g.addDirectedEdge(4, 5);
    g.addDirectedEdge(5, 6);
    g.addDirectedEdge(4, 7);
    g.addDirectedEdge(6, 7);
    
    // 터미널노드를 셋팅한다.
    g.setTerminalYn(new int[] {4, 5});
    
    System.out.println("==> 유향그래프 원본 출력");
    System.out.println(g);

    // 유향그래프를 시작노드(2)부터 말단노드까지 탐색(tracking)한다.
    BFS bfs = new BFS(g);
    Graph bfsGraph = bfs.bfs(2);
    
    // 탐색하기 전 유향그래프 출력 (원본)
    System.out.println("==> 유향그래프 탐색 결과 출력 (탐색노드, 거리, 선행노드)");
    System.out.println(bfs);
    // 탐색이 끝난 유향그래프 (tracking 경로) 출력
    System.out.println("==> 탐색이 끝난 유향그래프 출력");
    System.out.println(bfsGraph);
    
    // 거리값을 출력한다.
    System.out.println("==> 시작노드에서 각 노드까지의 거리값 출력");
    for (Object val : bfs.getDistances()) System.out.println(val);
    
  }

}
