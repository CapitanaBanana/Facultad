% Base de conocimiento
% Skills de las personas
skill(juan, gestion_recursos_humanos).
skill(juan, buena_comunicacion).
skill(juan, empatia_clientes).
skill(juan, uml).
skill(juan, arquitecturas).
skill(juan, java).
skill(juan, javascript).

%  Roles que les gusta trabajar 
prefiere(juan, lider).
prefiere(juan, analista).
prefiere(juan, disenador).
prefiere(juan, programador).

% Localidades donde puede trabajar 
puede_ir(juan, capital).
puede_ir(juan, cordoba).
puede_ir(juan, mendoza).
puede_ir(juan, la_plata).


% Relación entre roles y skills necesarias
requiere(lider, gestion_recursos_humanos).
requiere(lider, buena_comunicacion).

requiere(analista, uml).
requiere(analista, arquitecturas).

requiere(disenador, empatia_clientes).
requiere(disenador, buena_comunicacion).

requiere(programador, java).
requiere(programador, javascript).
requiere(programador, python).
requiere(programador, php).
requiere(programador, cpp).


% Reglas


% :) Una persona es apta para un rol si
% Esta Rol requiere una habilidad que la persona posea
es_apta(Persona, Rol) :-
    requiere(Rol, SkillReq),
    skill(Persona, SkillReq).

% :) :) Una persona puede ocupar un rol
% tiene preferencia por un rol y es apta para el mismo
puede_ocupar(Persona, Rol) :-
    prefiere(Persona, Rol),
    es_apta(Persona, Rol).

% :) Una persona puede ocupar un rol en una localidad si 
% puede ocupar ese rol y puede ir a esa localidad
personas_para_rol_en_localidad(Persona, Rol, Localidad) :-
    puede_ocupar(Persona, Rol),
    puede_ir(Persona, Localidad).


% :) Una persona esta disponible para un rol si
% Ese Rol requiere una habilidad que la persona tenga
disponible_para_rol(Persona, Rol) :-
    requiere(Rol, SkillReq),
    skill(Persona, SkillReq).




