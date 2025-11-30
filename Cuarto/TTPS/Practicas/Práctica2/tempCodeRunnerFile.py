import sys
n, w = map(int, input().split())
p = list(map(int, input().split()))
v = list(map(int, input().split()))
dp = [[0 for _ in range(w+1)] for _ in range(n+1)]

for i in range(1, n+1):
    for j in range(1, w+1):
        if j < p[i-1]:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-p[i-1]]+v[i-1])
print(dp[n][w])
