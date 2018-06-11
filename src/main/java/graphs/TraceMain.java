/**
 * 유향, 무향그래프 너비우선탐색 테스트
 */
package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TraceMain {

  public static void main(String args[]) {
    // 링크 정보를 생성한다.
    List<Edge> edges = new ArrayList<Edge>();

    edges.add(new Edge("Edge_0", 0, 1));
    edges.add(new Edge("Edge_1", 1, 2));
    edges.add(new Edge("Edge_2", 2, 3));
    edges.add(new Edge("Edge_3", 3, 4));
    edges.add(new Edge("Edge_4", 3, 5));
    edges.add(new Edge("Edge_5", 4, 5));
    edges.add(new Edge("Edge_6", 5, 6));
    edges.add(new Edge("Edge_7", 4, 7));
    edges.add(new Edge("Edge_8", 6, 7));

    /*
     * edges.add(new Edge("Edge_0", 0, 1, 85)); edges.add(new Edge("Edge_1", 0, 2,
     * 217)); edges.add(new Edge("Edge_2", 0, 4, 173)); edges.add(new Edge("Edge_3",
     * 2, 6, 186)); edges.add(new Edge("Edge_4", 2, 7, 103)); edges.add(new
     * Edge("Edge_5", 3, 7, 183)); edges.add(new Edge("Edge_6", 5, 8, 250));
     * edges.add(new Edge("Edge_7", 8, 9, 84)); edges.add(new Edge("Edge_8", 7, 9,
     * 167)); edges.add(new Edge("Edge_9", 4, 9, 502)); edges.add(new
     * Edge("Edge_10",9, 10, 40)); edges.add(new Edge("Edge_11",1, 10, 600));
     */

    // 링크정보를 가지고 탐색을 적용하기 위한 그래프를 생성한다.
    TraceGraph g = new TraceGraph(edges);

    // 터미널노드를 셋팅한다.
    // g.setTerminalYn(new Vertex[] {g.getVertex(4), g.getVertex(5)});

    // System.out.println("==> 유향그래프 원본 출력");
    // System.out.println(g);

    // 유향그래프에 탐색로직(BFS)을 적용한다.
    BFS bfs = new BFS(g);
    // 시작노드(2)부터 말단노드까지 탐색(tracking)한다.
    bfs.execute(2);
    // bfs.execute(3);

    // 탐색이 끝난 유향그래프를 얻는다.
    // TraceGraph bfsGraph = bfs.getGraph();

    // 이진트리형태로 출력한다.
    g.print();
    
    // 탐색이 끝난 경로 (tracking 경로) 출력 (링크정보)
    System.out.println("==> 탐색이 끝난 경로 출력 (링크정보)");
    List<Edge> path = bfs.getPath();

    for (Edge edge : path) {
      System.out.println(edge);
    }
    System.out.println();


    // 기타 함수 테스트

    // 유향그래프 탐색 결과 출력 (탐색노드, 거리, 선행노드)
    // System.out.println("==> 유향그래프 탐색 결과 출력 (탐색노드, 거리, 선행노드)");
    // System.out.println(bfs);
    // 탐색이 끝난 유향그래프 (tracking 경로) 출력
    // System.out.println("==> 탐색이 끝난 유향그래프 출력");
    // System.out.println(bfsGraph);
    // 거리값을 출력한다.
    // System.out.println("==> 시작노드에서 각 노드까지의 거리값 출력");
    // for (Object val : bfs.getDistances()) System.out.println(val);

    /*
     * int V = 7; g = new TraceGraph(V); // 노드 인덱스는 0부터 시작
     * 
     * // 노드에 유향그래프 속성을 부여한다. g.addDirectedEdge(0, 1); g.addDirectedEdge(0, 2);
     * g.addDirectedEdge(2, 3); g.addDirectedEdge(2, 4); g.addDirectedEdge(2, 5);
     * g.addDirectedEdge(4, 6);
     * 
     * g.setTerminalYn(new int[] {4}); // terminalYn 을 true 로 셋팅하거나 유향그래프에서 인접노드가 없을
     * 경우 말단노드
     * 
     * System.out.print("==> 노드 2의 인접노드: "); System.out.println(g.getAdj(2)); //
     * 노드2와 연결된 인접노드 목록을 출력한다. System.out.println("");
     * 
     * System.out.println("==> 모든 노드 출력"); // 모든 노드 출력 Iterator itrV =
     * g.iterateVertices(); while (itrV.hasNext()) { int v = (int)itrV.next();
     * System.out.printf("** Node : %d\n",v);
     * 
     * if (!g.getTerminalYn(v)) { Iterator itrE = g.iterateEdges(v);
     * System.out.print("Adjacency Node : "); while (itrE.hasNext()) {
     * System.out.print(itrE.next()+" "); } System.out.println("\n");
     * 
     * } else { System.out.println("Terminal Node\n"); }
     * 
     * }
     * 
     * V = 4; g = new Graph(V);
     * 
     * g.addDirectedEdge(0, 1); g.addDirectedEdge(0, 2); g.addDirectedEdge(1, 2);
     * g.addDirectedEdge(2, 0); g.addDirectedEdge(2, 3); g.addDirectedEdge(3, 3);
     * 
     * // 유향그래프를 만든다. V = 8; g = new TraceGraph(V);
     * 
     * 
     * g.addDirectedEdge(0, 1); g.addDirectedEdge(1, 2); g.addDirectedEdge(2, 3);
     * g.addDirectedEdge(3, 4); g.addDirectedEdge(3, 5); g.addDirectedEdge(4, 5);
     * g.addDirectedEdge(5, 6); g.addDirectedEdge(4, 7); g.addDirectedEdge(6, 7);
     * 
     * // 터미널노드를 셋팅한다. g.setTerminalYn(new int[] {4, 5});
     * 
     * System.out.println("==> 유향그래프 원본 출력"); System.out.println(g);
     * 
     * // 유향그래프를 시작노드(2)부터 말단노드까지 탐색(tracking)한다. BFS bfs = new BFS(g);
     * bfs.execute(2); TraceGraph bfsGraph = bfs.getTrace();
     * 
     * // 유향그래프 탐색 결과 출력 (탐색노드, 거리, 선행노드)
     * System.out.println("==> 유향그래프 탐색 결과 출력 (탐색노드, 거리, 선행노드)");
     * System.out.println(bfs); // 탐색이 끝난 유향그래프 (tracking 경로) 출력
     * System.out.println("==> 탐색이 끝난 유향그래프 출력"); System.out.println(bfsGraph);
     * 
     * // 거리값을 출력한다. System.out.println("==> 시작노드에서 각 노드까지의 거리값 출력"); for (Object
     * val : bfs.getDistances()) System.out.println(val);
     */

  }

}
