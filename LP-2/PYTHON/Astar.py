import heapq

def heuristic(a, b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])

def in_bounds(grid, x, y):
    return 0 <= x < len(grid) and 0 <= y < len(grid[0])

def reconstruct_path(came_from, end):
    path = []
    while end in came_from:
        path.append(end)
        end = came_from[end]
    return path[::-1]

def a_star(grid, start, end):
    open_set = []
    heapq.heappush(open_set, (0 + heuristic(start, end), 0, start))
    came_from = {}
    g_score = {start: 0}
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    while open_set:
        _, cost, current = heapq.heappop(open_set)
        if current == end:
            return [start] + reconstruct_path(came_from, current)

        for dx, dy in directions:
            nx, ny = current[0] + dx, current[1] + dy
            neighbor = (nx, ny)
            if in_bounds(grid, nx, ny) and grid[nx][ny] == 0:
                tentative_g = g_score[current] + 1
                if tentative_g < g_score.get(neighbor, float('inf')):
                    came_from[neighbor] = current
                    g_score[neighbor] = tentative_g
                    f_score = tentative_g + heuristic(neighbor, end)
                    heapq.heappush(open_set, (f_score, tentative_g, neighbor))
    return []

def main():
    rows, cols = map(int, input("Enter rows and columns: ").split())
    print("Enter grid (0=walkable, 1=blocked):")
    grid = [list(map(int, input().split())) for _ in range(rows)]
    sx, sy = map(int, input("Enter start (row col): ").split())
    ex, ey = map(int, input("Enter end (row col): ").split())

    if any([not in_bounds(grid, sx, sy), not in_bounds(grid, ex, ey),
            grid[sx][sy] == 1, grid[ex][ey] == 1]):
        print("Invalid start or end!")
        return

    path = a_star(grid, (sx, sy), (ex, ey))
    print("Path found:" if path else "No path found.")
    for p in path: print(p, end=' ')

if __name__ == "__main__":
    main()
