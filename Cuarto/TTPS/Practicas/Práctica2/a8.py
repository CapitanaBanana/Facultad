import sys
input = sys.stdin.readline
n, w = map(int, input().split())
p = list(map(int, input().split()))
v = list(map(int, input().split()))
""" dp = [[0 for _ in range(w+1)] for _ in range(n+1)]

for i in range(1, n+1):
    for j in range(1, w+1):
        if j < p[i-1]:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-p[i-1]]+v[i-1])
print(dp[n][w])
 """
dp = [0] * (w + 1)
for i in range(n):
    peso = p[i]
    valor = v[i]
    for j in range(w, peso - 1, -1):
        nuevo_valor = dp[j - peso] + valor
        if nuevo_valor > dp[j]:
            dp[j] = nuevo_valor

print(dp[w])
