use practica_5::fecha::Fecha;
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_new_fecha() {
        let fecha = Fecha::new(15, 8, 2023);
        assert_eq!(fecha.dia, 15);
        assert_eq!(fecha.mes, 08);
        assert_eq!(fecha.anio, 2023);
    }

    #[test]
    fn test_print() {
        let fecha = Fecha::new(1, 1, 2023);
        assert_eq!(fecha.print(), "01/01/2023");
    }

    #[test]
    fn test_es_bisiesto() {
        assert!(Fecha::new(01, 01, 2020).es_bisiesto());
        assert!(!Fecha::new(01, 01, 2021).es_bisiesto());
        assert!(Fecha::new(01, 01, 2000).es_bisiesto());
        assert!(!Fecha::new(01, 01, 1900).es_bisiesto());
    }

    #[test]
    fn test_es_fecha_valida() {
        assert!(Fecha::new(15, 8, 2023).es_fecha_valida());
        assert!(!Fecha::new(31, 02, 2023).es_fecha_valida());
        assert!(Fecha::new(29, 02, 2020).es_fecha_valida()); // Año bisiesto
        assert!(!Fecha::new(29, 02, 2023).es_fecha_valida()); // Año no bisiesto
        assert!(!Fecha::new(0, 01, 2023).es_fecha_valida()); // Día inválido
        assert!(!Fecha::new(15, 13, 2023).es_fecha_valida()); // Mes inválido
    }

    #[test]
    fn test_sumar_dias() {
        let mut fecha = Fecha::new(28, 2, 2020); // Año bisiesto
        fecha.sumar_dias(1);
        assert_eq!(fecha.dia, 29);
        assert_eq!(fecha.mes, 02);
        assert_eq!(fecha.anio, 2020);

        fecha.sumar_dias(1);
        assert_eq!(fecha.dia, 01);
        assert_eq!(fecha.mes, 03);
        assert_eq!(fecha.anio, 2020);

        let mut fecha2 = Fecha::new(31, 12, 2021);
        fecha2.sumar_dias(1);
        assert_eq!(fecha2.dia, 01);
        assert_eq!(fecha2.mes, 01);
        assert_eq!(fecha2.anio, 2022);
    }

    #[test]
    fn test_restar_dias() {
        let mut fecha = Fecha::new(1, 3, 2020); // Año bisiesto
        fecha.restar_dias(1);
        assert_eq!(fecha.dia, 29);
        assert_eq!(fecha.mes, 02);
        assert_eq!(fecha.anio, 2020);

        fecha.restar_dias(29);
        assert_eq!(fecha.dia, 31);
        assert_eq!(fecha.mes, 01);
        assert_eq!(fecha.anio, 2020);

        let mut fecha2 = Fecha::new(1, 1, 2022);
        fecha2.restar_dias(1);
        assert_eq!(fecha2.dia, 31);
        assert_eq!(fecha2.mes, 12);
        assert_eq!(fecha2.anio, 2021);
    }

    #[test]
    fn test_es_mayor() {
        let fecha1 = Fecha::new(15, 08, 2023);
        let fecha2 = Fecha::new(14, 08, 2023);
        let fecha3 = Fecha::new(15, 09, 2023);
        let fecha4 = Fecha::new(15, 08, 2024);

        assert!(fecha1.es_mayor(&fecha2));
        assert!(!fecha1.es_mayor(&fecha3));
        assert!(!fecha1.es_mayor(&fecha4));
        assert!(fecha4.es_mayor(&fecha1));
        assert!(fecha3.es_mayor(&fecha2));
    }

    #[test]
    fn test_dias_en_mes() {
        let fecha = Fecha::new(01, 01, 2023);
        assert_eq!(fecha.dias_en_mes(01, 2023), 31);
        assert_eq!(fecha.dias_en_mes(02, 2023), 28);
        assert_eq!(fecha.dias_en_mes(02, 2020), 29); // Año bisiesto
        assert_eq!(fecha.dias_en_mes(04, 2023), 30);
        assert_eq!(fecha.dias_en_mes(12, 2023), 31);
    }
}
