# graphs-bfs


** setting

  1) gradle eclipse
  2) eclipse -> File -> import -> General -> Existing Project into workspace

** sample data

  1)
  0 -> 1 -> 2 -> 3 +--> 4 -> 7
                   |    ↓    ↑
                   +--> 5 -> 6
                 
  2) 4, 5 노드를 터미널노드로 셋팅 
    ....
    g.setTerminalYn(new int[] {4, 5});
    ....

** source 

  Graph with V = 8 , E = 9
  0: [1]
  1: [2]
  2: [3]
  3: [4, 5]
  4: [5, 7]
  5: [6]
  6: [7]
  7: []

** result

  Graph with V = 8 , E = 3
  0: []
  1: []
  2: [3]
  3: [4, 5]
  4: []
  5: []
  6: []
  7: []
