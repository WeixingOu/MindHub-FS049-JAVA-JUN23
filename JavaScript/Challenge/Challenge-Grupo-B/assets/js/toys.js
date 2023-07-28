
const { createApp } = Vue
const options = {
    data() {
        return {
            active: false,
            isScrolled: false,
            arrayJugueteria: [],
            filtroMayor: [],
            filtroMenor: [],
            aleatorio: [],
            arrayCruzado: [],
            filtroCat: [],
            inputs: '',
            carrito: [],
        }
    },
    created() {
        this.carrito = JSON.parse(localStorage.getItem('carrito')) ?? []
        fetch('https://mindhub-xj03.onrender.com/api/petshop')
            .then(respuesta => respuesta.json())
            .then(datosApi => {
                this.arrayJugueteria = datosApi.filter(producto => producto.categoria == 'jugueteria')
                this.arrayJugueteria.sort((a, b) => a.disponibles - b.disponibles)
                console.log(this.arrayJugueteria);
                console.log(this.carrito);
            })
    },
    mounted() {
        window.addEventListener("scroll", this.onScroll);
    },
    beforeDestroy() {
        window.removeEventListener("scroll", this.onScroll);
    },
    methods: {
        toggleActive() {
            this.active = !this.active
        },
        onScroll() {
            this.isScrolled = window.scrollY > 0;
        },
        local(producto, accion) {
            if (accion == 'agregar') {
                this.carrito.push(producto)
            }
            localStorage.setItem('carrito', JSON.stringify(this.carrito))
        },
        filtroPrecioMenor() {
            this.filtroMenor = this.arrayJugueteria.sort((a, b) => a.precio - b.precio)
            this.arrayCruzado = this.filtroMenor

        },
        filtroPrecioMayor() {
            this.filtroMayor = this.arrayJugueteria.sort((a, b) => b.precio - a.precio)
            this.arrayCruzado = this.filtroMayor
        },
    },
    computed: {
        cruzado() {
            this.arrayCruzado = this.arrayJugueteria.filter(producto => {
                return producto.producto.toLowerCase().includes(this.filtroCat)
            })
        }
    }
}

const app = createApp(options);
app.mount('#app');
