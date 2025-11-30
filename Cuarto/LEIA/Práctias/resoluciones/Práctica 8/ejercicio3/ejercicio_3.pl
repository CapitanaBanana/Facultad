% Hechos
hombre(juan).
hombre(pedro).
hombre(luis).
hombre(ricardo).

mujer(ana).
mujer(juana).
mujer(maria).
mujer(laura).
mujer(sofia).

% Relaciones directas
padre(juan, pedro).
padre(juan, juana).
padre(luis, juan).

madre(ana, pedro).
madre(ana, juana).
madre(maria, ana).
madre(laura, juan).
madre(maria, sofia).
% REGLAS

% Progenitor
% X es progenitor de Y si X es padre de Y
progenitor(X, Y) :- padre(X, Y).
% X es progenitor de Y si X es madre de Y
progenitor(X, Y) :- madre(X, Y).

% Antepasado
% X es antepasado de Y si X es progenitor de Y
antepasado(X, Y) :- progenitor(X, Y).
% X es antepasado de Y si X es progenitor de Z y Z es antepasado de Y
antepasado(X, Y) :- progenitor(X, Z), antepasado(Z, Y).

% Hermano/Hermana
% X es hermano de Y si
% X es hombre
% X e Y tienen el mismo progenitor
% X no es la misma persona que Y
hermano(X, Y) :-
    hombre(X),
    progenitor(Z, X),
    progenitor(Z, Y),
    X \= Y.

% X es hermana de Y si 
% X es mujer
% X e Y tienen el mismo progenitor
% X no es la misma persona que Y
hermana(X, Y) :-
    mujer(X),
    progenitor(Z, X),
    progenitor(Z, Y),
    X \= Y.

%Abuelo/Abuela
% X es abuelo de Y si
% X es hombre
% X es progenitor de Z
% Z es progenitor de Y
abuelo(X, Y) :-
    hombre(X),
    progenitor(X, Z),
    progenitor(Z, Y).

% X es abuela de Y si
% X es mujer
% X es progenitor de Z
% Z es progenitor de Y
abuela(X, Y) :-
    mujer(X),
    progenitor(X, Z),
    progenitor(Z, Y).

%Tio/Tia
% X es tio de Y si
% X es hombre
% Z es progenitor de Y
% X es hermano de Z
tio(X, Y) :-
    hombre(X),
    progenitor(Z, Y),
    hermano(X, Z).

% X es tia de Y si
% X es mujer
% Z es progenitor de Y
% X es hermano de Z
tia(X, Y) :-
    mujer(X),
    progenitor(Z, Y),
    hermana(X, Z).
