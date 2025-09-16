#include<bits/stdc++.h>
using namespace std;
int main(){
  int cant;
  int num;
  bool even = true;
  bool changed = false;
  cin >> cant;
  cin >> num;
  even = num % 2 == 0;

   
  for(int i=1;i<cant;i++){
    cin >> num;
    if (num%2==0){
      if(!even and !changed){
        even = true;
        changed = true;
      }
      else if(changed){
        
      }
    }
    else{
      if(even and !changed){
        even = false;
        changed = true;
      }
    }
    
}

 cout<<x<<'\n';}
