def solve_n_queens(n):
    def backtrack(row):
        if row == n:
            result.append(["".join(r) for r in board])
            return
        for col in range(n):
            if col in cols or (row + col) in diag1 or (row - col) in diag2:
                continue
            board[row][col] = 'Q'
            cols.add(col)
            diag1.add(row + col)
            diag2.add(row - col)

            backtrack(row + 1)

            board[row][col] = '.'
            cols.remove(col)
            diag1.remove(row + col)
            diag2.remove(row - col)
    result = []
    board = [["."] * n for _ in range(n)]
    cols, diag1, diag2 = set(), set(), set()
    backtrack(0)
    return result
# Driver code
if __name__ == "__main__":
    n = int(input("Enter the value of N for N-Queens: "))
    solutions = solve_n_queens(n)
    print(f"\nTotal solutions: {len(solutions)}")
    for idx, sol in enumerate(solutions, 1):
        print(f"\nSolution {idx}:")
        for row in sol:
            print(row)