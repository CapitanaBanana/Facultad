% Base de conocimiento
hombre(juan).
hombre(pedro).
hombre(luis).
hombre(ricardo).

mujer(ana).
mujer(juana).
mujer(maria).
mujer(laura).

% Relaciones directas
padre(juan, pedro).
padre(juan, juana).
padre(luis, juan).

madre(ana, pedro).
madre(ana, juana).
madre(maria, ana).
madre(laura, juan).

% Progenitor
progenitor(X, Y) :- padre(X, Y).
progenitor(X, Y) :- madre(X, Y).

% Antepasado
antepasado(X, Y) :- progenitor(X, Y).
antepasado(X, Y) :- progenitor(X, Z), antepasado(Z, Y).

%Hermano/Hermana
hermano(X, Y) :-
    hombre(X),
    progenitor(Z, X),
    progenitor(Z, Y),
    X \= Y.

hermana(X, Y) :-
    mujer(X),
    progenitor(Z, X),
    progenitor(Z, Y),
    X \= Y.

%Abuelo/Abuela
abuelo(X, Y) :-
    hombre(X),
    progenitor(X, Z),
    progenitor(Z, Y).

abuela(X, Y) :-
    mujer(X),
    progenitor(X, Z),
    progenitor(Z, Y).

%Tio/Tia
tio(X, Y) :-
    hombre(X),
    progenitor(Z, Y),
    hermano(X, Z).

tia(X, Y) :-
    mujer(X),
    progenitor(Z, Y),
    hermana(X, Z).
