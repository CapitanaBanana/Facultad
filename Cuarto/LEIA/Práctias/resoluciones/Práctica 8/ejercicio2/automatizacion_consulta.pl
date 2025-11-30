:- initialization(main).

main :-
    consult('ejercicio2.pl'),

    writeln("===== Consulta 1: Personas disponibles para un rol en una localidad ====="),
    writeln("Personas para rol 'programador' en 'la_plata':"),
    (   setof(P, personas_para_rol_en_localidad(P, programador, la_plata), Personas)
    ->  forall(member(P, Personas), writeln(P))
    ;   writeln('No hay personas')
    ),
    writeln("----------------------------------------"),

    writeln("===== Consulta 2: Personas disponibles para un rol ====="),
    writeln("Personas para rol 'diseniador':"),
    (   setof(P, disponible_para_rol(P, diseniador), Personas)
    ->  forall(member(P, Personas), writeln(P))
    ;   writeln('No hay personas')
    ),
    writeln("----------------------------------------"),

    writeln("===== Consulta 3: Roles que puede ocupar una persona ====="),
    writeln("Roles que puede ocupar juan:"),
    (   setof(R, puede_ocupar(juan, R), RolesUnicos)
    ->  forall(member(R, RolesUnicos), writeln(R))
    ;   writeln('No tiene roles')
    ),
    writeln("----------------------------------------"),

    writeln("===== Consulta 4: ¿Es apta una persona para un rol? ====="),
    writeln("¿Es Ana apta para 'tester'?"),
    (   puede_ocupar(ana, tester)
    ->  writeln(true)
    ;   writeln(false)
    ),
    writeln("----------------------------------------"),

    halt.