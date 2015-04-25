package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class StronglyConnectedComponent {
	
	public static void main(String[] args) {
		ArrayList <String> edges = new ArrayList<String>(Arrays.asList("a->b","b->c","b->e","b->f","e->a","e->f","c->d",
														"c->g","f->g","d->c","d->h","g->f","g->h","h->h"));
		MyDGraph G = new MyDGraph(edges);
		G.explain();
		//G.visualize();
		
		get_component(G);
	}
	
	public static void get_component(MyDGraph G){
		G.DFS();
		
		int[] f = G.getFinishedTime();
		int []nodeIndices = new int[f.length];
		for (int i = 0; i < f.length; i++) {
			nodeIndices[i] = i;
		}
		
		//sort node indices in the descending order of their finish time
		System.out.println(Arrays.toString(f));
		System.out.println(Arrays.toString(nodeIndices));
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f.length - i - 1; j++) {
				if(f[j] < f[j + 1]){
					int t = f[j]; f[j] = f[j+1]; f[j+1] = t;
					int t2 = nodeIndices[j]; nodeIndices[j] = nodeIndices[j+1]; nodeIndices[j+1] = t2;
				}
			}
		}
		System.out.println();
		System.out.println(Arrays.toString(f));
		System.out.println(Arrays.toString(nodeIndices));
		System.out.println();
		System.out.println("Below : each line is a strongly connected component");
		MyGraph GT = G.getTranpose();
		for (int nodeIndex : nodeIndices) {
			GT.DFS_at(nodeIndex, new AtomicInteger());
			System.out.println();
		}
	}
}
