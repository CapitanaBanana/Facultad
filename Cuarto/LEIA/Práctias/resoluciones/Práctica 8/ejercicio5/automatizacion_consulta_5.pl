:- initialization(main).

main :-
    consult('vacunas_base.pl'),
    
    writeln("¿Puede Claudia vacunarse con Spikevax Bivariante?"),
    (puede_vacunarse(claudia, spikevax_bivariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Claudia vacunarse con Spikevax Monovariante?"),
    (puede_vacunarse(claudia, spikevax_monovariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Claudia vacunarse con Comirnaty?"),
    (puede_vacunarse(claudia, comirnaty) -> writeln(true) ; writeln(false)),
    writeln("--------------------"),
    % --------------- %
    writeln("¿Puede Hernan vacunarse con Spikevax Bivariante?"),
    (puede_vacunarse(hernan, spikevax_bivariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Hernan vacunarse con Spikevax Monovariante?"),
    (puede_vacunarse(hernan, spikevax_monovariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Hernan vacunarse con Comirnaty?"),
    (puede_vacunarse(hernan, comirnaty) -> writeln(true) ; writeln(false)),
     writeln("--------------------"),
    % --------------- %
    writeln("¿Puede Clara vacunarse con Spikevax Bivariante?"),
    (puede_vacunarse(clara, spikevax_bivariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Clara vacunarse con Spikevax Monovariante?"),
    (puede_vacunarse(clara, spikevax_monovariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Clara vacunarse con Comirnaty?"),
    (puede_vacunarse(clara, comirnaty) -> writeln(true) ; writeln(false)),
     writeln("--------------------"),
    % --------------- %

    writeln("¿Puede Joaquín vacunarse con Spikevax Bivariante?"),
    (puede_vacunarse(joaquin, spikevax_bivariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Joaquín vacunarse con Spikevax Monovariante?"),
    (puede_vacunarse(joaquin, spikevax_monovariante) -> writeln(true) ; writeln(false)),

    writeln("¿Puede Joaquín vacunarse con Comirnaty?"),
    (puede_vacunarse(joaquin, comirnaty) -> writeln(true) ; writeln(false)),

    % --------------- %

    halt.