const { createApp } = Vue
const options = {
    data() {
        return {
            active: false,
            isScrolled: false,
        }
    },
    created() {
        fetch('https://mindhub-xj03.onrender.com/api/petshop')
        .then(res => res.json())
        .then(data => {
        
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
        }
    },
}
const app = createApp(options);
app.mount('#app');