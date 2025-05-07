from collections import deque

def dfs_using_stack(adj_list, start_node, visited):
    stack = [start_node]
    visited[start_node] = True

    while stack:
        node = stack.pop()
        print(node, end=' ')
        for neighbor in adj_list[node]:
            if not visited[neighbor]:
                visited[neighbor] = True
                stack.append(neighbor)

def bfs_using_queue(adj_list, start_node, visited):
    queue = deque([start_node])
    visited[start_node] = True

    while queue:
        node = queue.popleft()
        print(node, end=' ')
        for neighbor in adj_list[node]:
            if not visited[neighbor]:
                visited[neighbor] = True
                queue.append(neighbor)

def main():
    n = int(input("Enter the number of nodes: "))
    m = int(input("Enter the number of edges: "))

    adj_list = [[] for _ in range(n + 1)]

    print("Enter the edges:")
    for _ in range(m):
        u, v = map(int, input().split())
        adj_list[u].append(v)
        adj_list[v].append(u)

    start_node = int(input("Enter the starting node: "))

    while True:
        print("\nChoose an option:")
        print("1. Perform DFS")
        print("2. Perform BFS")
        print("3. Exit")
        choice = input("Enter your choice: ")

        visited = [False] * (n + 1)

        if choice == '1':
            print(f"DFS Traversal starting from node {start_node}:")
            dfs_using_stack(adj_list, start_node, visited)
            print()
        elif choice == '2':
            print(f"BFS Traversal starting from node {start_node}:")
            bfs_using_queue(adj_list, start_node, visited)
            print()
        elif choice == '3':
            print("Exiting the program... :)")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()