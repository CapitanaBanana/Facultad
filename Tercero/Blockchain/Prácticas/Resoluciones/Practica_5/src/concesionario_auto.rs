use std::ptr::null;

#[derive( PartialEq, Clone, Debug)] 
pub struct Concesionario_auto{
    pub nombre: String,
    pub direccion: String,
    pub autos: Vec<Auto>
}

#[derive( PartialEq, Clone, Debug)] 
pub struct Auto{
    pub marca: String,
    pub modelo: String,
    pub anio: u16,
    pub color: Colores,
    pub precio_bruto: f32
}

#[derive( PartialEq, Clone, Debug)] 
pub enum Colores{
    Rojo,
    Azul,
    Verde,
    Amarillo,
    Negro,
    Blanco}

impl Concesionario_auto{
  pub fn new( nombre: String, direccion:String, max_autos:u16) -> Concesionario_auto{
        Concesionario_auto{
          nombre,
          direccion,
          autos: Vec::with_capacity(max_autos as usize),
        }
  }
  pub fn agregar_auto(&mut self,auto:Auto)-> bool{
     if self.autos.len() < self.autos.capacity() {
            self.autos.push(auto);
            true
        } else {
            false
        }
  }
  pub fn eliminar_auto(&mut self, auto:&Auto)-> bool{
     if let Some(posicion) = self.autos.iter().position(|x| x == auto) {
            self.autos.remove(posicion);
            true
        } else {
            false
        }
  }
    pub fn buscar_auto(&self, auto: &Auto) -> Option<&Auto> {
    self.autos.iter().find(|&x| x == auto)
  }
  }

  impl Auto {
    pub fn new(marca:String, modelo:String, anio:u16, color: Colores, precio_bruto:f32)->Auto{
      Auto{
        marca,
        modelo,
        anio,
        color,
        precio_bruto
      }
    }
   pub fn calcular_precio(&self) -> f32 {
    let mut precio = self.precio_bruto;

    if self.anio < 2000 {
        precio = precio - self.precio_bruto * 0.05; 
    }
    if self.marca == "BMW" {
        precio = precio + self.precio_bruto * 0.15; 
       
    }

    match self.color {
        Colores::Rojo | Colores::Amarillo | Colores::Azul => {
            precio = precio + self.precio_bruto * 0.25; 
        }
        _ => {
            precio = precio - self.precio_bruto * 0.1; 
        }
    }
    precio
}
  }
