use practica_5::persona::Persona;

#[test]
fn test_crear_persona() {
    let persona = Persona::new("Juan".to_string(), 25, Some("Calle 123".to_string()));
    assert_eq!(persona.get_nombre(), "Juan");
    assert_eq!(persona.get_edad(), 25);
    assert_eq!(persona.get_dirreccion(), "Calle 123");
}

#[test]
fn test_crear_persona_sin_direccion() {
    let persona = Persona::new("Maria".to_string(), 22, None);
    assert_eq!(persona.get_nombre(), "Maria");
    assert_eq!(persona.get_edad(), 22);
    assert_eq!(persona.get_dirreccion(), "no establecida");
}

#[test]
fn test_actualizar_edad() {
    let mut persona = Persona::new("Juan".to_string(), 25, Some("Calle 123".to_string()));
    persona.set_edad(30);
    assert_eq!(persona.get_edad(), 30);
}

#[test]
fn test_actualizar_nombre() {
    let mut persona = Persona::new("Juan".to_string(), 25, Some("Calle 123".to_string()));
    persona.set_nombre("Carlos".to_string());
    assert_eq!(persona.get_nombre(), "Carlos");
}

#[test]
fn test_actualizar_direccion() {
    let mut persona = Persona::new("Juan".to_string(), 25, Some("Calle 123".to_string()));
    persona.set_dirreccion(Some("Avenida 456".to_string()));
    assert_eq!(persona.get_dirreccion(), "Avenida 456");

    persona.set_dirreccion(None);
    assert_eq!(persona.get_dirreccion(), "no establecida");
}

#[test]
fn test_print() {
    let persona = Persona::new("Juan".to_string(), 25, Some("Calle 123".to_string()));
    assert_eq!(persona.print(), ("Nombre: Juan edad: 25 direccion: Calle 123"));
}