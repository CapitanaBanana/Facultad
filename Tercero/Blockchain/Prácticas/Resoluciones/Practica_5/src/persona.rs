pub struct Persona{
    nombre: String,
    edad: u8,
    dirreccion: Option<String>
}
impl Persona{
    pub fn new(nombre: String, edad: u8, dirreccion: Option<String>) -> Persona{
        Persona{
            nombre,
            edad,
            dirreccion
        }
    }
    pub fn print(&self) -> String {
    format!(
        "Nombre: {} edad: {} direccion: {}",
        self.nombre,
        self.edad,
        self.get_dirreccion()
    )
}
    pub fn get_edad(&self) -> u8{
        self.edad
    }
    pub fn get_nombre(&self) -> &String{
        &self.nombre
    }
    pub fn get_dirreccion(&self) -> String {
        match &self.dirreccion {
            Some(dirreccion) => dirreccion.clone(),
            None => "no establecida".to_string(),
        }
    }
    pub fn set_edad(&mut self, edad: u8){
        self.edad = edad;
    }
    pub fn set_nombre(&mut self, nombre: String){
        self.nombre = nombre;
    }
    pub fn set_dirreccion(&mut self, dirreccion: Option<String>){
        self.dirreccion = dirreccion;
    }
}
/* fn main() {
   let mut persona = Persona::new("Juan".to_string(), 25, Some("Calle 123".to_string()));
    println!("{}", persona.print());
    println!("Edad: {}", persona.get_edad());
    persona.set_edad(30);
    print!("Edad: {}", persona.get_edad());

    let persona_sin_dirreccion = Persona::new("Juan".to_string(), 25, None);
    println!("{}", persona_sin_dirreccion.print());
}  */