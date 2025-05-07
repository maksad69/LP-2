import java.util.*;

class Node implements Comparable<Node> {
    int x, y;
    int gCost;
    int hCost;
    Node parent;

    Node(int x, int y, Node parent, int gCost, int hCost) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
    }

    int fCost() {
        return gCost + hCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fCost(), other.fCost());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) return false;
        Node other = (Node) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class AStarPathfinding {
    static final int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    public static List<Node> aStar(int[][] grid, int[] start, int[] end) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        Node startNode = new Node(start[0], start[1], null, 0, heuristic(start, end));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == end[0] && current.y == end[1]) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (int[] dir : DIRECTIONS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isInBounds(grid, newX, newY) && grid[newX][newY] == 0) {
                    Node neighbor = new Node(newX, newY, current,
                            current.gCost + 1,
                            heuristic(new int[]{newX, newY}, end));

                    if (closedSet.contains(neighbor)) continue;

                    boolean betterPath = true;
                    for (Node openNode : openSet) {
                        if (openNode.equals(neighbor) && openNode.gCost <= neighbor.gCost) {
                            betterPath = false;
                            break;
                        }
                    }

                    if (betterPath) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private static int heuristic(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    private static boolean isInBounds(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    private static List<Node> reconstructPath(Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input grid size
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] grid = new int[rows][cols];

        // Input grid values
        System.out.println("Enter grid values (0 for walkable, 1 for blocked):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // Input start and end points
        System.out.print("Enter start point (row and column): ");
        int sx = sc.nextInt();
        int sy = sc.nextInt();
        System.out.print("Enter end point (row and column): ");
        int ex = sc.nextInt();
        int ey = sc.nextInt();

        // Validate start and end
        if (!isInBounds(grid, sx, sy) || !isInBounds(grid, ex, ey) ||
                grid[sx][sy] == 1 || grid[ex][ey] == 1) {
            System.out.println("Invalid start or end position!");
            return;
        }

        List<Node> path = aStar(grid, new int[]{sx, sy}, new int[]{ex, ey});

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.printf("(%d, %d) ", node.x, node.y);
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
