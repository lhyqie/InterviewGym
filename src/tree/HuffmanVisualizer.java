package tree;

import java.awt.Dimension;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;

import tree.Huffman.HuffmanNode;
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

public class HuffmanVisualizer {
	public static void visualize(HuffmanNode root){
		Graph<HuffmanNode, String> g = new DirectedSparseMultigraph<HuffmanNode, String>();
		IdentityHashMap<HuffmanNode, Integer> node2id = new IdentityHashMap<HuffmanNode, Integer>();
		AtomicInteger id = new AtomicInteger(0);
	
		Queue<HuffmanNode> Q = new LinkedList<HuffmanNode>();
		Q.offer(root);
		while(!Q.isEmpty()){
			HuffmanNode t = Q.poll();
			node2id.put(t, id.get());
			id.incrementAndGet();
			if(t.left != null) Q.offer(t.left);
			if(t.right != null) Q.offer(t.right);
		}
		
		id = new AtomicInteger(0);
		Q.clear();
		Q.offer(root);
		while(!Q.isEmpty()){
			HuffmanNode t = Q.poll();
			if(t.left != null){
				Q.offer(t.left);
				int parentId = node2id.get(t);
				int childId = node2id.get(t.left);
				g.addEdge("edge:"+parentId+"->"+childId, t, t.left, EdgeType.DIRECTED);
			}
			if(t.right != null) {
				Q.offer(t.right);
				int parentId = node2id.get(t);
				int childId = node2id.get(t.right);
				g.addEdge("edge:"+parentId+"->"+childId, t, t.right, EdgeType.DIRECTED);
			}
		}
		//System.out.println("The graph g = " + g.toString());
		Layout<HuffmanNode, String> layout = new CircleLayout<HuffmanNode, String>(g);
		layout.setSize(new Dimension(500,500)); // sets the initial size of the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		//BasicVisualizationServer<Integer,String> vv = new BasicVisualizationServer<Integer,String>(layout);
		VisualizationViewer<HuffmanNode,String> vv = new VisualizationViewer<HuffmanNode,String>(layout);
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
