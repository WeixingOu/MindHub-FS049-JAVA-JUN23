function filtrarCervezasXAlcohol(cervezas, gradoAlcohol) {
    const cervezasFiltradas = cervezas.filter(cerveza => cerveza.abv <= gradoAlcohol).map(cerveza => {
        return {
            nombre: cerveza.nombre,
            alcohol: cerveza.abv,
            amargor: cerveza.ibu
        };
    });
    return cervezasFiltradas;
}
console.log(filtrarCervezasXAlcohol(beers, 5));

function filtrarCervezasMasAlcoholicas(cervezas) {
    const cervezasMasAlcoholicas = cervezas.sort((a, b) => b.abv - a.abv).slice(0, 10);
    return cervezasMasAlcoholicas;
}
console.log(filtrarCervezasMasAlcoholicas(beers));

function filtrarCervezsMenosAmargas(cervezas) {
    const cervezasMenosAmargas = cervezas.sort((a, b) => a.ibu - b.ibu).slice(0, 10);
    return cervezasMenosAmargas;
}
console.log(filtrarCervezsMenosAmargas(beers));

function ordenarCervezasXPropiedad(cervezas, propiedad, ascendente) {
    const cervezasOrdenadas = cervezas.sort((a, b) => {
        if (ascendente) {
            return a[propiedad] - b[propiedad];
        } else {
            return b[propiedad] - a[propiedad];
        }
    })
    return cervezasOrdenadas.slice(0, 10);
}
console.log(ordenarCervezasXPropiedad(beers, 'abv', true));

function renderizarTablaCervezas(cervezas, id) {
    const container = document.body;

    const tabla = document.createElement('table');
    tabla.classList.add('table');

    const thead = document.createElement('thead');
    const trHead = document.createElement('tr');
    const thHead = document.createElement('th');
    thHead.textContent = 'Name';
    const thABV = document.createElement('th');
    thABV.textContent = 'ABV';
    const thIBU = document.createElement('th');
    thIBU.textContent = 'IBU';

    trHead.appendChild(thHead);
    trHead.appendChild(thABV);
    trHead.appendChild(thIBU);
    thead.appendChild(trHead);
    tabla.appendChild(thead);

    const tbody = document.createElement('tbody');
    tabla.appendChild(tbody);

    container.appendChild(tabla);

    cervezas.forEach(cerveza => {
        if (cerveza.id === id) {
            const fila = document.createElement('tr');
            const celdaNombre = document.createElement('td');
            celdaNombre.textContent = cerveza.name;
            const celdaABV = document.createElement('td');
            celdaABV.textContent = cerveza.abv;
            const celdaIBU = document.createElement('td');
            celdaIBU.textContent = cerveza.ibu;

            fila.appendChild(celdaNombre);
            fila.appendChild(celdaABV);
            fila.appendChild(celdaIBU);
            tbody.appendChild(fila);
        }
    });
}

renderizarTablaCervezas(beers, 1);