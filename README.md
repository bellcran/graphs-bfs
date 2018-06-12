# 너비우선탐색(BFS)

### 설치

- **prompt> gradle eclipse**
- **이클립스 실행** : 
eclipse -> File -> import -> General -> Existing Project into workspace

### 샘플데이터

```
0 -> 1 -> 2 -> 3 +--> 4 -> 7
                 |    ↓    ↑
                 +--> 5 -> 6
```

### 그래프 생성
```
List<Edge> edges = new ArrayList<Edge>();

edges.add(new Edge("Edge_0", 0, 1));
edges.add(new Edge("Edge_1", 1, 2));
edges.add(new Edge("Edge_2", 2, 3));
....
TraceGraph g = new TraceGraph(edges);
....
```
### 말단노드 설정
```
....
g.setTerminalYn(new int[] {4, 5});
....
```
### 유향그래프에 탐색로직(BFS) 적용
```
BFS bfs = new BFS(g);
bfs.execute(2); //시작노드
```

### 원본노드출력(이진트리형태)
```
g.print();
```
**[result]**
```
└── Node_0
    └── Node_1
        └── Node_2
            └── Node_3
                ├── Node_4
                │   ├── Node_5
                │   │   └── Node_6
                │   │       └── Node_7
                │   └── Node_7
                └── Node_5
                    └── Node_6
                        └── Node_7
```
### 탐색결과 출력 (이진트리형태)
```
List<Edge> path = bfs.getPath();
TraceGraph tg = new TraceGraph(path);
tg.print();
```
**[result]**
```
└── Node_2
    └── Node_3
        ├── Node_4
        └── Node_5
```
### 탐색결과 출력 (링크정보)
```
for (Edge edge : path) {
  System.out.println(edge);
}
System.out.println();
```
**[result]**
```
Node_2(2)-Node_3(3)
Node_3(3)-Node_4(4)
Node_3(3)-Node_5(5)
```


# 최단경로(Dijkstra)

### 샘플데이터

```
0 +-> 1 -> 10
  |
  +-> 2 +-> 6    8 <----- 5
  |     |        ↓ 
  |     +-> 7 -> 9 -> 10
  |         ↑    ↑
  |   3 ----+    |
  |              |
  +-> 4 ---------+
```
### 그래프 생성

```
List<Edge> edges = new ArrayList<Edge>();

edges.add(new Edge("Edge_0", 0, 1, 85));
edges.add(new Edge("Edge_1", 0, 2, 217));
edges.add(new Edge("Edge_2", 0, 4, 173));
edges.add(new Edge("Edge_3", 2, 6, 186));
edges.add(new Edge("Edge_4", 2, 7, 103));
edges.add(new Edge("Edge_5", 3, 7, 183));
edges.add(new Edge("Edge_6", 5, 8, 250)); 
edges.add(new Edge("Edge_7", 8, 9, 84));
edges.add(new Edge("Edge_8", 7, 9, 167));
edges.add(new Edge("Edge_9", 4, 9, 502));
edges.add(new Edge("Edge_10",9, 10, 40));
edges.add(new Edge("Edge_11",1, 10, 600));

ShortestPathGraph graph = new ShortestPathGraph(edges);
```
### 그래프에 최단경로 로직 적용
```
Dijkstra dijkstra = new Dijkstra(graph);
dijkstra.execute(0); //시작노드
LinkedList<Vertex> path = dijkstra.getPath(10); //끝노드
```
### 원본노드출력(이진트리형태)
```
graph.print();
```
**[result]**
```
└── Node_0
    ├── Node_1
    │   └── Node_10
    ├── Node_2
    │   ├── Node_6
    │   └── Node_7
    │       └── Node_9
    │           └── Node_10
    └── Node_4
        └── Node_9
            └── Node_10
└── Node_3
    └── Node_7
        └── Node_9
            └── Node_10
└── Node_5
    └── Node_8
        └── Node_9
            └── Node_10
```

### 탐색결과출력(노드정보)
```
for (Vertex vertex : path) {
  System.out.print(vertex);
  if (vertex.getIndex() != to)System.out.print(" -> ");
}
```
**[result]**
```
Node_0(0) -> Node_2(2) -> Node_7(7) -> Node_9(9) -> Node_10(10)
```

