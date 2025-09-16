#include <iostream>
using namespace std;
int main() {
  int number;
  cin>>number;
  if(number%2==0 && number!=2){
    cout<<"yes"<<endl;
  }else{
    cout<<"no"<<endl;
  }
  return 0;
}