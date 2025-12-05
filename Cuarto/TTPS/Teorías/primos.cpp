struct Primos {
    typedef long long ll;
    vector<int> listaPrimos; // lista de numeros primos
    vector<int> minPrimo; // menor primo que divide a i
    // Saber el menor primo que divide a un numero nos permite
    // factorizar mas rapido
    vector<bool> esPrimo; // True si es primo
    Primos(int n) {criba(n);}
    void criba(int n) {
        minPrimo.resize(n+1);
        esPrimo.resize(n+1,true); // En principio son todos primos
        esPrimo[0] = esPrimo[1] = false; // El 1 no es primo
        for(int i = 2; i <= n; i++) {
            if(!esPrimo[i]) continue;
            listaPrimos.push_back(i);
            for(ll j = 1LL*i*i; j <= n; j += i) {
                if(esPrimo[j]) {
                    esPrimo[j] = false;
                    minPrimo[j] = i; 
                    // i es el menor primo que divide a j
                }
            }
        }
    }
    bool testPrimalidad(ll x) {
        if(x < esPrimo.size()) return esPrimo[x];
        ll maxPrimo = listaPrimos.back();
        // Asegurarse que esten hallados todos los primos
        // menores o igual a sqrt(x) en la criba
        for(int p : listaPrimos) {
            if(x%p == 0) return false;
        }
        return true;
    }
};