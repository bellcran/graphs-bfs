# graphs-bfs

### setting

  1) gradle eclipse
  2) eclipse -> File -> import -> General -> Existing Project into workspace

### sample data

1) 0 -> 1 -> 2 -> 3 +--> 4 -> 7
                    |    ↓    ↑
                    +--> 5 -> 6
                 
2) 4, 5 노드를 터미널노드로 셋팅
    ....
    g.setTerminalYn(new int[] {4, 5});
    ....
