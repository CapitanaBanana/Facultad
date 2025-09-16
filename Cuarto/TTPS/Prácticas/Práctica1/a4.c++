#include<bits/stdc++.h>
using namespace std;
int main(){
  int cant;
  int x=0;
  string op;
  cin >> cant;
  for(int i=0;i<cant;i++){
    cin >> op;
    if (op.find("--") != string::npos){
      x--;
    }
    else{
      x++;
    }
}

 cout<<x<<'\n';}
