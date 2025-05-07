import java.util.*;

public class DFSBFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes:");
        int n = sc.nextInt();
        System.out.println("Enter the number of edges:");
        int m = sc.nextInt();
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        System.out.println("Enter the edges:");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];
        System.out.println("Enter the starting node:");
        int l = sc.nextInt();
        System.out.println("DFS Traversal starting from node " + l +":");
//        dfstraversal.dfsUsingStack(adjList, l, visited);
//        bfstraversal.bfsUsingQueue(adjList, l, visited);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Perform DFS");
            System.out.println("2. Perform BFS");
            System.out.println("3. Exit");
            int choice = sc.nextInt();

            // Resetting the visited array for each traversal
            Arrays.fill(visited, false);

            switch (choice) {
                case 1:
                    System.out.println("DFS Traversal starting from node " + l + ":");
                    dfstraversal.dfsUsingStack(adjList, l, visited);
                    System.out.println();
                    break;

                case 2:
                    System.out.println("BFS Traversal starting from node " + l + ":");
                    bfstraversal.bfsUsingQueue(adjList, l, visited);
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Exiting the program... :)");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

class dfstraversal {
    public static void dfsUsingStack(List<List<Integer>> adjList, int startNode, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        visited[startNode] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            System.out.print(node + " ");
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
    }
}

class bfstraversal {
    public static void bfsUsingQueue(List<List<Integer>> adjList, int startNode, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;
        while (!queue.isEmpty()) {
            int node = queue.remove();
            System.out.print(node + " ");
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}