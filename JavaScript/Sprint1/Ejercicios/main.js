function saludar(nombre) { console.log(`¡Hola, ${nombre}! ¡Bienvenido/a!`) };
saludar('Weixing');

function multiplicar(num1, num2) { console.log(num1 * num2) };
multiplicar(2,2);

function areaTriangulo(base, altura) { console.log('El area del triangulo es : ' + (base * altura) / 2) };
areaTriangulo(5, 3);

function perimetroTriangulo(lado1, lado2, lado3) { console.log('El perimetro del triangulo es : ' + (lado1 + lado2 + lado3))};
perimetroTriangulo(4, 5, 6);

function calcularPrecio(precio, cantidad) {
    let total = precio * cantidad;

    if (cantidad >= 10 && cantidad < 20) {
        total -= total * 0.1;
    } else if (cantidad >= 20) {
        total -= total * 0.2;
    }

    return total;
}
console.log(calcularPrecio(10, 5));
console.log(calcularPrecio(10, 10));
console.log(calcularPrecio(10, 15));

function esMayorDeEdad(edad) {
    if (edad >= 18) {
        console.log('Eres mayor de edad');
    } else {
        console.log('Eres menor de edad');
    }
}
esMayorDeEdad(18);
esMayorDeEdad(17);

function calcularImpuesto(ingresoAnual) {
    let impuesto;

    if (ingresoAnual <= 10000) {
        impuesto = ingresoAnual * 0.1; 
    } else if (ingresoAnual <= 20000) {
        impuesto = ingresoAnual * 0.15; 
    } else {
        impuesto = ingresoAnual * 0.2;
    }

    return impuesto;
}
console.log(calcularImpuesto(5000));
console.log(calcularImpuesto(12000));
console.log(calcularImpuesto(25000));

function verificarDia(diaSemanal) {
    switch (diaSemanal) {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            console.log("Es un día laboral");
            break;
        case 6:
        case 7:
            console.log(mensaje = "Es fin de semana");
            break;
        default:
            console.log(mensaje = "Número de día inválido");
    }
}
verificarDia(1);
verificarDia(4);
verificarDia(8);