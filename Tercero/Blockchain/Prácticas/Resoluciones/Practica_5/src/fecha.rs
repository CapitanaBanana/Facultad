pub struct Fecha {
    pub dia: u8,
    pub mes: u8,
    pub anio: u16,
}

impl Fecha {
    pub fn new(dia: u8, mes: u8, anio: u16) -> Fecha {
        let fecha = Fecha { dia, mes, anio };
        if fecha.es_fecha_valida() {
            fecha
        } else {
            Fecha { dia: 0, mes: 0, anio: 0 }
        }
    }

    pub fn print(&self) -> String {
        format!("{:02}/{:02}/{:04}", self.dia, self.mes, self.anio)
    }

    pub fn es_bisiesto(&self) -> bool {
        self.anio % 4 == 0 && (self.anio % 100 != 0 || self.anio % 400 == 0)
    }

    pub fn es_fecha_valida(&self) -> bool {
        if self.mes < 1 || self.mes > 12 {
            return false;
        }
        if self.dia < 1 || self.dia > self.dias_en_mes(self.mes, self.anio) {
            return false;
        }
        true
    }

    //qué decisión estúpuida fue no usar un crate para esto :p
   pub fn sumar_dias(&mut self, dias: u8) -> &mut Fecha {
    let mut dias_restantes = dias;

    while dias_restantes > 0 {
        let dias_en_mes_actual = self.dias_en_mes(self.mes, self.anio);

        if self.dia + dias_restantes as u8 <= dias_en_mes_actual {
            self.dia += dias_restantes as u8;
            dias_restantes = 0;
        } else {
            let dias_hasta_fin_mes = dias_en_mes_actual - self.dia; 
            dias_restantes -= dias_hasta_fin_mes;
            self.dia = 1; 
            self.mes += 1;

            if self.mes > 12 {
                self.mes = 1;
                self.anio += 1;
            }
        }
    }

    self
}


    pub fn restar_dias(&mut self, dias: u8) -> &mut Fecha {
        let mut dias_restantes = dias;

        while dias_restantes > 0 {
            if dias_restantes >= self.dia {
                dias_restantes -= self.dia;

                if self.mes == 1 {
                    self.mes = 12;
                    self.anio -= 1;
                } else {
                    self.mes -= 1;
                }

                self.dia = self.dias_en_mes(self.mes, self.anio);
            } else {
                self.dia -= dias_restantes;
                dias_restantes = 0;
            }
        }

        self
    }

    pub fn es_mayor(&self, otra_fecha: &Fecha) -> bool {
        if self.anio > otra_fecha.anio {
            true
        } else if self.anio == otra_fecha.anio {
            if self.mes > otra_fecha.mes {
                true
            } else if self.mes == otra_fecha.mes {
                self.dia > otra_fecha.dia
            } else {
                false
            }
        } else {
            false
        }
    }

    pub fn dias_en_mes(&self, mes: u8, anio: u16) -> u8 {
        match mes {
            1 | 3 | 5 | 7 | 8 | 10 | 12 => 31,
            4 | 6 | 9 | 11 => 30,
            2 => {
                if (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0) {
                    29
                } else {
                    28
                }
            }
            _ => 0,
        }
    }
}

/* fn main() {
    let mut fecha = Fecha::new(31, 12, 2020);
    println!("{}", fecha.print());
    println!("{}", fecha.es_bisiesto());
    println!("{}", fecha.es_fecha_valida());
    fecha.sumar_dias(1);
    println!("{}", fecha.print());
    fecha.restar_dias(1);
    println!("{}", fecha.print());
    let fecha2 = Fecha::new(31, 12, 2021);
    println!("{}", fecha.es_mayor(&fecha2));
} */
