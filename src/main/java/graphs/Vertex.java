/**
 * 노드 클래스 정의
 */
package graphs;

public class Vertex {
  final private String id;
  final private int index;

  /**
   * 노드 이름과 인덱스로 노드를 생성한다.
   * @param id
   * @param name
   */
  public Vertex(String id, int index) {
      this.id = id;
      this.index = index;
  }
  
  public String getId() {
      return id;
  }

  public int getIndex() {
      return index;
  }

  @Override
  public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj)
          return true;
      if (obj == null)
          return false;
      if (getClass() != obj.getClass())
          return false;
      Vertex other = (Vertex) obj;
      if (id == null) {
          if (other.id != null)
              return false;
      } else if (!id.equals(other.id))
          return false;
      return true;
  }

  @Override
  public String toString() {
      return id + "(" + index + ")";
  }

}