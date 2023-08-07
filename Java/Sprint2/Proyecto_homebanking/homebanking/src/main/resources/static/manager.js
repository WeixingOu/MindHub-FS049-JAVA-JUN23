const { createApp } = Vue;

const options = {
    data() {
        return {
            clients: [],
            newClient: {
                firstName: '',
                lastName: '',
                email: ''
            },
        }
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get('/clients')
            .then(res => {
                this.clients = res.data._embedded.clients;
            })
            .catch(err => console.log(err));
        },
        addClient() {
            if (this.newClient.firstName && this.newClient.lastName && this.newClient.email) {
                this.postClient();
                this.newClient = {
                    firstName: '',
                    lastName: '',
                    email: ''
                };
            }
        },
        postClient() {
            axios.post('/clients', this.newClient)
                .then(res => {
                    this.loadData();
                })
                .catch(err => console.log(err));
        },
    },
}

const app = createApp(options);
app.mount('#app');