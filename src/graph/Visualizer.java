package graph;

import java.awt.Dimension;
import java.util.HashSet;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Visualizer {
	
	static class Vertex{
		int id;
		String label;
		
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((label == null) ? 0 : label.hashCode());
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
			return id == ((Vertex)obj).id;
		}

		public Vertex(int id, String label){
			this.id = id;
			this.label = label;
		}
		
		public String toString(){
			return label;
		}
	}
	
	public static void visualize(MyGraph G, int mode){
		Graph<Vertex, String> g = null; 
		if(mode == MyGraph.DIRECTED){
			g = new DirectedSparseMultigraph<Vertex, String>();
			// build graph by adding edges
			g.getEdges(EdgeType.DIRECTED);
			for (int i = 0; i < G.n; i++) {			
				for (int j : G.adj[i]) {
					if(G.nodeLabels == null){
						g.addEdge("edge:"+i+"->"+j, new Vertex(i, ""+i), new Vertex(j, ""+j), EdgeType.DIRECTED);
					}else{
						g.addEdge("edge:"+i+"->"+j, new Vertex(i, G.nodeLabels[i]), new Vertex(j, G.nodeLabels[j]), EdgeType.DIRECTED);
					}
				}
			}
		}else if(mode == MyGraph.UNDIRECTED){
			HashSet<String> added_edges = new HashSet<String>(); // to avoid parallel edges in undirected graph
			g = new UndirectedSparseMultigraph<Vertex, String>();
			g.getEdges(EdgeType.DIRECTED);
			for (int i = 0; i < G.n; i++) {			
				for (int j : G.adj[i]) {
					int small = i;
					int big = j;
					if(small > big){ int t = big; big = small; small = t;}
					if(!added_edges.contains(""+small+"->"+big)){
						added_edges.add(""+small+"->"+big);
						if(G.nodeLabels == null){
							g.addEdge("edge:"+i+"->"+j, new Vertex(i, ""+i), new Vertex(j, ""+j), EdgeType.UNDIRECTED);
						}else{
							g.addEdge("edge:"+i+"->"+j, new Vertex(i, G.nodeLabels[i]), new Vertex(j, G.nodeLabels[j]), EdgeType.UNDIRECTED);
						}
					}
				}
			}
		}else{
			throw new IllegalArgumentException("graph mode illegal : it should be MyGraph.DIRECTED or MyGraph.unDIRECTED");
		}
		
		
		//System.out.println("The graph g = " + g.toString());
		Layout<Vertex, String> layout = new CircleLayout<Vertex, String>(g);
		layout.setSize(new Dimension(500,500)); // sets the initial size of the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		//BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
		VisualizationViewer<Vertex,String> vv = new VisualizationViewer<Vertex,String>(layout);
		vv.setPreferredSize(new Dimension(500,500)); //Sets the viewing area size
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		//gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		gm.setMode(ModalGraphMouse.Mode.PICKING);
		vv.setGraphMouse(gm);
		
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
