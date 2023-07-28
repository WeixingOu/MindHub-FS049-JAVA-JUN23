const { createApp } = Vue

const options = {
    data() {
        return {
            events: [],
            categories: [],
            isHomePage: Boolean,
            categoriesChecked: [],
            searchText: "",
        }
    },
    created() {
        fetch('https://mindhub-xj03.onrender.com/api/amazing')
            .then(response => response.json())
            .then(data => {
                const events = data.events;

                this.categories = [...new Set(events.map(event => event.category))];
                this.events = events;
            })
            .catch(err => console.log(err))
    },
    methods: {
        getDetailsLink(event) {
            const isHomePage = window.location.pathname === '/index.html';
            return isHomePage ? './assets/pages/details.html?id=' + event._id : './details.html?id=' + event._id;
        },
    },
    computed: {
        filteredEvents() {
            const checkedCategories = this.categoriesChecked.map(category => category.toLowerCase());
            const searchText = this.searchText.toLowerCase();

            return this.events.filter((event) => {
                const eventCategory = event.category.toLowerCase();
                const eventName = event.name.toLowerCase();

                return (
                    (checkedCategories.length === 0 ||
                        checkedCategories.includes(eventCategory)) &&
                    eventName.includes(searchText)
                );
            });
        },
    }
}
const app = createApp(options);
app.mount('#app');