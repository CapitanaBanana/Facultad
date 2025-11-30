% Hechos del dominio %
es_algo(mentir, malo).
es_algo(ayudar, bueno).

hace_algo(sebastian, ayudar).
hace_algo(sebastian, mentir).

% Reglas del dominio %

% Una persona es buena si esa Persona hace algo Algo y ese Algo es algo bueno %
es_buena(Persona) :- hace_algo(Persona, Algo), es_algo(Algo, bueno).

% Una persona es mala si esa Persona hace algo y ese Algo es algo malo %
es_mala(Persona):- hace_algo(Persona, Algo), es_algo(Algo, malo).
