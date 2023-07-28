function generarTablaMulptiplicar(num) {
    for (let i = 1; i <= 10; i++) {
        console.log(num + 'x' + i + '=' + num * i);
    }
}
generarTablaMulptiplicar(5);

function acumularNumeros() {
    let sum = 0;
    let num;
    do {
        num = parseInt(prompt("Ingrese un número (ingrese 0 para terminar):"));
        sum += num;
        if (num === 0) {
            break;
        }
    } while (num != 0)
    return sum;
}
// acumularNumeros();

function adivinarNumero() {
    let numSecreto = acumularNumeros();
    console.log(numSecreto);
    if (numSecreto > 100) {
        alert('El número secreto debe estar en el rango de 1 a 100. Intenta nuevamente.');
        adivinarNumero();
        return;
    }

    let intentos = 0;
    let numIngresado;
    while (numIngresado != numSecreto) {
        numIngresado = parseInt(prompt("Adivina el número secreto (entre 1 y 100):"));
        intentos++;

        if (numIngresado > numSecreto) {
            alert("El número ingresado es mayor al número secreto. Intenta nuevamente.");
        } else if (numIngresado < numSecreto) {
            alert("El número ingresado es menor al número secreto. Intenta nuevamente.");
        }
    }
    alert(`Felicitaciones! Adivinaste el número secreto en ${intentos} intentos.`);
}
// adivinarNumero();

function divisores(num) {
    for (let i = 1; i <= num; i++) {
        if (num % i === 0) {
            console.log(i);
        }
    }
}
divisores(8);
divisores(11);

let colores = ["Rojo", "Azul", "Verde", "Amarillo", "Naranja", "Rosa", "Morado", "Gris", "Blanco", "Negro"];
function mostrarElementos(array) {
    for (let i = 0; i < array.length; i++) {
        console.log(array[i]);
    }
}
mostrarElementos(colores);

let numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
function mostrarDoble(array) {
    for (let i = 0; i < array.length; i++) {
        console.log(array[i] * 2);
    }
}
mostrarDoble(numeros);

let familia = [
    {
        nombre: "Juan",
        edad: 35,
        relacion: "Padre",
        profesion: "Ingeniero"
    },
    {
        nombre: "Maria",
        edad: 32,
        relacion: "Madre",
        profesion: "Abogada"
    },
    {
        nombre: "Carlos",
        edad: 10,
        relacion: "Hijo",
        profesion: "Estudiante"
    },
    {
        nombre: "Laura",
        edad: 8,
        relacion: "Hija",
        profesion: "Estudiante"
    },
    {
        nombre: "Pedro",
        edad: 60,
        relacion: "Abuelo",
        profesion: "Jubilado"
    }
];

function presentacionFamilia(familia) {
    for (let persona of familia) {
        console.log(`¡Hola! Mi nombre es ${persona.nombre}, tengo ${persona.edad} años y soy ${persona.relacion}. Mi profesión es ${persona.profesion}.`);
    }
}
presentacionFamilia(familia);

function numImpares(numeros) {
    for (let numero of numeros) {
        if (numero % 2 !== 0) {
            console.log(numero);
        }
    }
}
numImpares(numeros);

function calcularSumaParesImpares() {
    let num;
    let sumPares = 0;
    let sumImpares = 0;

    do {
        num = parseInt(prompt("Ingrese un número (ingrese 0 para terminar):"));
        if (num === 0) {
            break;
        }
        if (num % 2 === 0) {
            sumPares += num;
        } else {
            sumImpares += num;
        }
    } while (num != 0)
    console.log(`Suma de los números pares: ${sumPares}`);
    console.log(`Suma de los números impares: ${sumImpares}`);
}
// calcularSumaParesImpares();

function numMax(numeros) {
    let numMax = numeros[0]
    for (let i = 1; i < numeros.length; i++) {
        if (numeros[i] > numMax ) {
            numMax = numeros[i];
        }
    }
    return numMax;
}
console.log(numMax(numeros));