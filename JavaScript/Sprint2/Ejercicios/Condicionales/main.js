function siEsMayor(num1, num2) {
    return num1 > num2;
}
console.log(siEsMayor(5, 3));
console.log(siEsMayor(3, 5));

function siSonIguales(num1, num2) {
    return num1 == num2;
}
console.log(siSonIguales(3, 3));
console.log(siSonIguales(3, 5));

function compararNumeros(num1, num2) {
    return siEsMayor(num1, num2) ? num1 : siSonIguales(num1, num2) ? true : num2;
}
console.log(compararNumeros(3, 5));
console.log(compararNumeros(3, 3));

function numMin(num1, num2, num3) {
    return num1 < num2 ? (num1 < num3 ? num1 : num3) : (num2 < num3 ? num2 : num3);
}
console.log(numMin(1, 2, 3));

const juan = {
    nombre: 'Juan',
    edad: 30,
    altura: 175
};

const martin = {
    nombre: 'Martin',
    edad: 25,
    altura: 160
};

function quienEsMasAlta(persona1, persona2) {
    return persona1.altura > persona2.altura ? `${persona1.nombre} ${persona1.altura}` : `${persona2.nombre} ${persona2.altura}`;
}
console.log(quienEsMasAlta(juan, martin));

function verificarCapacidadConducir() {
    const persona = {
        nombre: prompt('Ingrese tu nombre:'),
        edad: Number(prompt('Ingresa tu edad:')),
        altura: Number(prompt('Ingresa tu altura en cm:')),
        vision: Number(prompt('Ingresa tu visión (de 1 a 10):'))
    }

    return persona.edad >= 18 && persona.altura > 150 && persona.vision >= 8;
}
// console.log(verificarCapacidadConducir());

function verificarPaseCliente() {
    const cliente = {
        nombre: prompt('Ingrese el nombre del cliente:'),
        pase: prompt('Ingrese el tipo de pase (VIP o normal):'),
        entrada: prompt("¿Posee entrada? (si o no):") === 'si'
    }

    return cliente.nombre === 'Franco' || cliente.pase === 'VIP' ?
        console.log('¡Bienvenido/a! Puede ingresar libremente.') : cliente.entrada ?
            prompt('¿Desea utilizar la entrada? (si o no):') === 'si' ?
                console.log('¡Bienvenido/a! Puede ingresar libremente.') : prompt('¿Desea comprar? (si o no):') === 'si' ?
                    Number(prompt("Ingrese el dinero disponible:")) >= 1000 ?
                        console.log('¡Compra exitosa! ¡Bienvenido/a!') : console.log('Lo sentimos, no puede realizar la compra.')
                    : console.log('¡Hasta luego! Gracias por su visita.')
            : console.log('¡Hasta luego! Gracias por su visita.')
}
// verificarPaseCliente();

function adivinanza() {
    const numeroIncognita = Math.floor((Math.random() * 10) + 1);
    let intentos = 3;
    while (intentos > 0) {
        const numeroIngresado = prompt("Ingrese un número del 1 al 10:");
        if (numeroIncognita == numeroIngresado) {
            console.log('¡Ganaste! Has adivinado el número.');
            return;
        } else if (numeroIncognita > numeroIngresado) {
            console.log('El número ingresado es menor. Vuelve a intentarlo.');
        } else {
            console.log('El número ingresado es mayor. Vuelve a intentarlo.');
        }
        intentos--;
    }
    console.log("Perdiste! No has logrado adivinar el número.");
}
// adivinanza();

function determinarCategoriaEdad() {
    const edad = parseInt(prompt("Ingrese su edad:"));
    if (edad <= 12) {
        console.log("Eres un infante.");
    } else if (edad <= 18) {
        console.log("Eres un adolescente.");
    } else if (edad <= 45) {
        console.log("Eres un mayor joven.");
    } else if (edad < 100) {
        console.log("Eres un anciano.");
    } else {
        const confirmacion = prompt("¿Estás seguro/a de que tienes esa edad? (si o no)");
        if (confirmacion === "si") {
            console.log("Eres un anciano.");
        } else {
            console.log("Ingrese una edad válida.");
            determinarCategoriaEdad();
        }
    }
}
// determinarCategoriaEdad();

function jugarPiedraPapelTijera() {
    const jugador1 = prompt("Jugador 1, ingresa tu elección (PIEDRA, PAPEL o TIJERA):").toUpperCase();
    const jugador2 = prompt("Jugador 2, ingresa tu elección (PIEDRA, PAPEL o TIJERA):").toUpperCase();

    if (jugador1 === jugador2) {
        console.log('Empate.');
    } else if ( 
        (jugador1 === "PIEDRA" && jugador2 === "TIJERA") ||
        (jugador1 === "PAPEL" && jugador2 === "PIEDRA") ||
        (jugador1 === "TIJERA" && jugador2 === "PAPEL")
        ) {
            console.log('Ganó Jugador 1');
    } else if (
        (jugador2 === "PIEDRA" && jugador1 === "TIJERA") ||
        (jugador2 === "PAPEL" && jugador1 === "PIEDRA") ||
        (jugador2 === "TIJERA" && jugador1 === "PAPEL")
        ) {
            console.log('Ganó Jugador 2');
    } else if (
        (  
            (jugador1 !== "PIEDRA") &&
            (jugador1 !== "PAPEL") &&
            (jugador1 !== "TIJERA")
            ) &&
        (
            (jugador2 !== "PIEDRA") &&
            (jugador2 !== "PAPEL") &&
            (jugador2 !== "TIJERA")
        )
    ) {
        console.log("Ambos jugadores han hecho trampa.")
    } else if (
        (jugador1 !== "PIEDRA") &&
        (jugador1 !== "PAPEL") &&
        (jugador1 !== "TIJERA")
    ) {
        console.log("Jugador 1 ha hecho trampa.");
    } else if (
        (jugador2 !== "PIEDRA") &&
        (jugador2 !== "PAPEL") &&
        (jugador2 !== "TIJERA")
    ) {
        console.log("Jugador 2 ha hecho trampa.");
    } 
}
// jugarPiedraPapelTijera();

function selecionarColor(color) {
    switch (color.toLowerCase()) {
        case 'blanco':
        case 'negro':
            console.log('Falta de color.');
            break;
        case 'verde':
            console.log('El color de la naturaleza.');
            break;
        case 'azul':
            console.log('El color del agua.');
            break;
        case 'amarillo':
            console.log('El color del sol.');
            break;
        case 'rojo':
            console.log('El color del fuego.');
            break;
        case 'marrón':
            console.log('El color de la tierra.');
            break;
        default:
            console.log('Excelente elección, no lo teníamos pensado.');
    }
}
selecionarColor('Blanco'); 
selecionarColor('Marrón');  
selecionarColor('Violeta');

function calculosDosNumeros(num1, num2, operacion) {
    switch (operacion) {
        case 'suma':
            console.log(`La suma de ${num1} y ${num2} es ${num1 + num2}.`);
            break;
        case 'resta':
            console.log(`La resta de ${num1} y ${num2} es ${num1 - num2}.`);
            break;
        case 'multiplicacion':
            console.log(`La multiplicación de ${num1} y ${num2} es ${num1 * num2}.`);
            break;
        case 'division':
            if (num2 !== 0) {
                console.log(`La división de ${num1} entre ${num2} es ${num1 / num2}.`);
            } else {
                console.log('No se puede dividir por 0.');
            }
            break;
        default:
            console.log('Operación inválida.');
    }
}
calculosDosNumeros(5, 3, 'suma');
calculosDosNumeros(10, 4, 'resta');
calculosDosNumeros(6, 2, 'multiplicacion');
calculosDosNumeros(8, 0, 'division');
calculosDosNumeros(15, 3, 'otra_operacion');

function ingresarDNI() {
    for (let i = 1; i <= 5; i++) {
        console.log('*'.repeat(i));
    }

    const datosIngresados = {
        nombre: prompt('Ingrese su nombre:'),
        apellido: prompt('Ingrese su apellido:'),
        dni: prompt('Ingrese su número de DNI:'),
        fechaNacimiento: prompt('Ingrese su fecha de nacimiento:')
    };
    console.log(datosIngresados);
    if (confirm('¿Los datos ingresados son correctos?')) {
        const dni = {
            ...datosIngresados
        };
        console.log('Registro exitoso:');
        console.table(dni);
    } else {
        console.log('Vuelva a intentarlo en 1 mes.');
    }
}
ingresarDNI();