:- initialization(main).

main :-
    consult('ejercicio_4.pl'),
    
    writeln("¿Es Sebastian bueno?"),
    (es_buena(sebastian) -> writeln(true) ; writeln(false)),
    
    writeln("¿Es Sebastian malo?"),
    (es_mala(sebastian) -> writeln(true) ; writeln(false)),
    
    halt.