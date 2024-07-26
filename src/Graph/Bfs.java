package Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Bfs {
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine().trim());
//		while (T-- > 0) {
//			String[] s = br.readLine().trim().split(" ");
//			int V = Integer.parseInt(s[0]);
//			int E = Integer.parseInt(s[1]);
//			ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//			for (int i = 0; i < V; i++)
//				adj.add(i, new ArrayList<Integer>());
//			for (int i = 0; i < E; i++) {
//				String[] S = br.readLine().trim().split(" ");
//				int u = Integer.parseInt(S[0]);
//				int v = Integer.parseInt(S[1]);
//				adj.get(u).add(v);
//				// adj.get(v).add(u);
//			}
////            Solution obj = new Solution();
//			ArrayList<Integer> ans = bfsOfGraph(V, adj);
//			for (int i = 0; i < ans.size(); i++)
//				System.out.print(ans.get(i) + " ");
//			System.out.println();
	}
//	}

	public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> ans = new ArrayList<>();
		q.add(0);
		boolean[] vis = new boolean[V];

		Arrays.fill(vis, false);
		vis[0] = true;
		while (q.size() > 0) {
			int rem = q.remove();
			ans.add(rem);
			List<Integer> nbs = adj.get(rem);
			for (int ele : nbs) {
				if (vis[ele] == false) {
					q.add(ele);
					vis[ele] = true;
				}
			}
		}
		return ans;

	}
}
