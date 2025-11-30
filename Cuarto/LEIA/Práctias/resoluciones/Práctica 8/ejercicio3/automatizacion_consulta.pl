:- initialization(main).

main :-
    consult('ejercicio_3.pl'),

    writeln("¿Es Luis abuelo de Pedro?"),
    (abuelo(luis, pedro) -> writeln(true) ; writeln(false)),

    writeln("¿Es Luis abuelo de Juana?"),
    (abuelo(luis, juana) -> writeln(true) ; writeln(false)),

    writeln("¿Es Maria abuela de Juana?"),
    (abuela(maria, juana) -> writeln(true) ; writeln(false)),
    writeln("--------------------"),

    % ---------------------------
    writeln("¿Son Pedro y Juana hermanos?"),
    (hermano(pedro, juana) -> writeln(true) ; writeln(false)),

    writeln("¿Es Juana hermana de Pedro?"),
    (hermana(juana, pedro) -> writeln(true) ; writeln(false)),
    writeln("--------------------"),

    % ---------------------------
    writeln("¿Es Luis tío de Juana?"),
    (tio(luis, juana) -> writeln(true) ; writeln(false)),

    writeln("¿Es Sofia tía de Pedro?"),
    (tia(sofia, pedro) -> writeln(true) ; writeln(false)),
    writeln("--------------------"),

    % ---------------------------
    writeln("¿Es Laura antepasado de Pedro?"),
    (antepasado(laura, pedro) -> writeln(true) ; writeln(false)),

    writeln("¿Es Maria antepasado de Juana?"),
    (antepasado(maria, juana) -> writeln(true) ; writeln(false)),
    writeln("--------------------"),

    % ---------------------------
    writeln("¿Es Juan progenitor de Pedro?"),
    (progenitor(juan, pedro) -> writeln(true) ; writeln(false)),

    writeln("¿Es Ana progenitor de Juana?"),
    (progenitor(ana, juana) -> writeln(true) ; writeln(false)),

    halt.