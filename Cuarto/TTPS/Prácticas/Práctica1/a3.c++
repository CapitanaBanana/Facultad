#include<bits/stdc++.h>
using namespace std;
int main(){
  int cant;
  int opinion;
  int res = 0;
  cin >> cant;
  for(int i=0;i<cant;i++){
    int agreement = 0;
    for (int i = 0; i < 3; i++)
    {
      cin>>opinion;
      if(opinion==1){
        agreement++;
      }
    }
    if (agreement>1){
      res++;
    }
  }
  cout<<res<<'\n';
}
