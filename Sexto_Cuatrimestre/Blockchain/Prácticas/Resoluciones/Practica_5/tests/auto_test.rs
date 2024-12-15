use practica_5::concesionario_auto::{Auto, Colores, Concesionario_auto};
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_concesionario_new() {
        let concesionario = Concesionario_auto::new(
            String::from("AutoMax"),
            String::from("Calle 123"),
            10,
        );
        assert_eq!(concesionario.nombre, "AutoMax");
        assert_eq!(concesionario.direccion, "Calle 123");
        assert_eq!(concesionario.autos.capacity(), 10);
        assert!(concesionario.autos.is_empty());
    }

    #[test]
    fn test_agregar_auto() {
        let mut concesionario = Concesionario_auto::new(
            String::from("AutoMax"),
            String::from("Calle 123"),
            2,
        );
        let auto = Auto::new(
            String::from("Toyota"),
            String::from("Corolla"),
            2022,
            Colores::Rojo,
            20000.0,
        );

        assert!(concesionario.agregar_auto(auto));
        assert_eq!(concesionario.autos.len(), 1);
    }

    #[test]
    fn test_agregar_auto_excede_capacidad() {
        let mut concesionario = Concesionario_auto::new(
            String::from("AutoMax"),
            String::from("Calle 123"),
            1,
        );
        let auto1 = Auto::new(
            String::from("Toyota"),
            String::from("Corolla"),
            2022,
            Colores::Rojo,
            20000.0,
        );
        let auto2 = Auto::new(
            String::from("Honda"),
            String::from("Civic"),
            2021,
            Colores::Azul,
            18000.0,
        );

        assert!(concesionario.agregar_auto(auto1));
        assert!(!concesionario.agregar_auto(auto2)); // Devuelve false porque no hay más lugar
    }

    #[test]
    fn test_eliminar_auto() {
        let mut concesionario = Concesionario_auto::new(
            String::from("AutoMax"),
            String::from("Calle 123"),
            5,
        );
        let auto = Auto::new(
            String::from("Toyota"),
            String::from("Corolla"),
            2022,
            Colores::Rojo,
            20000.0,
        );
        
        concesionario.agregar_auto(auto.clone());

        assert!(concesionario.eliminar_auto(&auto));
        assert!(concesionario.autos.is_empty());
    }

    #[test]
    fn test_eliminar_auto_inexistente() {
        let mut concesionario = Concesionario_auto::new(
            String::from("AutoMax"),
            String::from("Calle 123"),
            5,
        );
        let auto = Auto::new(
            String::from("Toyota"),
            String::from("Corolla"),
            2022,
            Colores::Rojo,
            20000.0,
        );

        assert!(!concesionario.eliminar_auto(&auto)); // Devuelve false porque no existe el auto
    }

    #[test]
    fn test_buscar_auto() {
        let mut concesionario = Concesionario_auto::new(
            String::from("AutoMax"),
            String::from("Calle 123"),
            5,
        );
        let auto = Auto::new(
            String::from("Toyota"),
            String::from("Corolla"),
            2022,
            Colores::Rojo,
            20000.0,
        );
        concesionario.agregar_auto(auto.clone());

        assert_eq!(concesionario.buscar_auto(&auto), Some(&auto));
    }

    #[test]
    fn test_buscar_auto_inexistente() {
        let concesionario = Concesionario_auto::new(
            String::from("AutoMax"),
            String::from("Calle 123"),
            5,
        );
        let auto = Auto::new(
            String::from("Toyota"),
            String::from("Corolla"),
            2022,
            Colores::Rojo,
            20000.0,
        );

        assert_eq!(concesionario.buscar_auto(&auto), None);
    }

    #[test]
    fn test_auto_new() {
        let auto = Auto::new(
            String::from("Toyota"),
            String::from("Corolla"),
            2022,
            Colores::Rojo,
            20000.0,
        );

        assert_eq!(auto.marca, "Toyota");
        assert_eq!(auto.modelo, "Corolla");
        assert_eq!(auto.anio, 2022);
        assert_eq!(auto.color, Colores::Rojo);
        assert_eq!(auto.precio_bruto, 20000.0);
    }
     #[test]
    fn test_calcular_precio() {
        let auto_nuevo = Auto::new(
            String::from("BMW"),
            String::from("Corolla"),
            2023,
            Colores::Rojo,
            20000.0,
        );

        let auto_viejo = Auto::new(
            String::from("Honda"),
            String::from("Civic"),
            1999,
            Colores::Negro,
            20000.0,
        );

        assert_eq!(auto_nuevo.calcular_precio(), 28000.0);

        assert_eq!(auto_viejo.calcular_precio(), 17000.0);
    }

  
}