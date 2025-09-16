#include <bits/stdc++.h>
using namespace std;
int main() {
    int cant;
    int even = 0;
    int odd = 0;
    int evenPos = -1;
    int oddPos = -1;
    int num;
    cin >> cant;
    for (int i = 0; i < cant; i++) {
        cin >> num;
        if (num % 2 == 0) {
            even++;
            evenPos = i + 1;   
        } else {
            odd++;
            oddPos = i + 1;
        }
    }
    if (even == 1) {
        cout << evenPos << "\n";
    } else {
        cout << oddPos << "\n";
    }

    return 0;
}
