def call(String step_name, int ci_node_total, String label = null, Closure cl) {
  def nodes = [:]

  for(int i = 0; i < ci_node_total; i++) {
    def index = i;
    nodes["${step_name}_ci_node_${i}"] = {
      node(label) {
        withEnv(["CI_NODE_INDEX=$index", "CI_NODE_TOTAL=$ci_node_total"]) {
          cl()
        }
      }
    }
  }

  return nodes;
}
