#include<bits/stdc++.h>
using namespace std;
int main(){
  long n;
  long m;
  long a;
  cin >> n>>m>>a;
  double res = (n * m) / (a * a);
  cout << ceil(res);
}

