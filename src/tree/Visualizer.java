package tree;

import java.awt.Dimension;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;

import tree.MyBTree.Node;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


public class Visualizer {
	static class Vertex{
		private int id;
		private String label;
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
	
	public static void visualize(MyBTree tree){
		if(tree.root == null) return;
		
		Graph<Vertex, String> g = new DirectedSparseMultigraph<Vertex, String>();
		IdentityHashMap<Node, Integer> node2id = new IdentityHashMap<Node, Integer>();
		AtomicInteger id = new AtomicInteger(0);
	
		Queue<Node> Q = new LinkedList<Node>();
		Q.offer(tree.root);
		while(!Q.isEmpty()){
			Node t = Q.poll();
			node2id.put(t, id.get());
			id.incrementAndGet();
			System.out.println(id + " : " + t.e);
			if(t.left != null) Q.offer(t.left);
			if(t.right != null) Q.offer(t.right);
		}
		
		id = new AtomicInteger(0);
		Q.clear();
		Q.offer(tree.root);
		while(!Q.isEmpty()){
			Node t = Q.poll();
			if(t.left != null){
				Q.offer(t.left);
				int parentId = node2id.get(t);
				int childId = node2id.get(t.left);
				g.addEdge("edge:"+parentId+"->"+childId, new Vertex(parentId, ""+t.e), new Vertex(childId, ""+t.left.e), EdgeType.DIRECTED);
			}
			if(t.right != null) {
				Q.offer(t.right);
				int parentId = node2id.get(t);
				int childId = node2id.get(t.right);
				g.addEdge("edge:"+parentId+"->"+childId, new Vertex(parentId, ""+t.e), new Vertex(childId, ""+t.right.e), EdgeType.DIRECTED);
			}
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
		
		JFrame frame = new JFrame("Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);	
	}
	
}
