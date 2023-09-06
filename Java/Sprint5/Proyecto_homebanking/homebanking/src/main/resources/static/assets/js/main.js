const { createApp, ref } = Vue

const options = {
    data() {
        return {
            isDraggingX: false,
            containerX: ref(null),
            isDraggingY: false,
            containerY: ref(null),

            isSidebarOpen: false,

            theme: localStorage.getItem('theme') || 'light',

            chart: null,

            client: {},
            accounts: [],
            loans: [],

            cards: [],
            creditCards: [],
            debitCards: [],
        }
    },
    mounted() {
        this.containerX = this.$refs.containerX;
        this.containerY = this.$refs.containerY;

        this.checkWindowSize();
        window.addEventListener('resize', this.checkWindowSize);
    },
    beforeUnmount() {
        window.removeEventListener('resize', this.checkWindowSize);
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get('/api/clients/1')
            .then(res => {
                this.client = res.data;
                this.accounts = this.client.accounts;
                this.loans = this.client.loans;
                this.cards = this.client.cards;

                this.creditCards = this.cards.filter(card => card.type === "CREDIT");
                this.debitCards = this.cards.filter(card => card.type === "DEBIT");

                this.creditCards.filter(card => card.type === "CREDIT").forEach(card => {
                    card.truDate = moment(card.truDate).format('MM-YY');
                });
                this.debitCards.filter(card => card.type === "DEBIT").forEach(card => {
                    card.truDate = moment(card.truDate).format('MM-YY');
                });

                this.client.accounts.forEach(account => {
                    account.creationDate = moment(account.creationDate).format('DD/MM/YY');
                    account.balance = account.balance.toLocaleString('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2});

                    account.transactions.forEach(transaction => {
                        transaction.date = moment(transaction.date).format('DD/MM/YY');
                    });
                });

                this.renderChart();
            })
            .catch(err => {
                console.error(err); 
            });
        },

        navigateToAccount(accountId) {
            window.location.href = `/assets/pages/account.html?id=${accountId}#`;
        },

        renderChart(){
            this.$nextTick(() => {
                const options = {
                    series: [
                        {
                            name: "Amount",
                            data: this.clientTransactions.map(t => t.amount),
                        },
                    ],
                    chart: {
                        type: "area",
                        height: "auto",
                        width: "340px"
                    },
                    fill: {
                        colors: ["#fff"],
                        type: "gradient",
                    },
                    dataLabels: {
                        enabled: false
                    },
                    stroke: {
                        curve: "smooth",
                        colors: ["#429cd4"],
                    },
                    xaxis: {
                        type: 'category',
                        categories: this.clientTransactions.map(t => moment(t.date).format('DD/MM')),
                    },
                    tooltip: {
                        x: {
                            format: 'dd/MM'
                        },
                    },
                    grid: {
                        show: false,
                    },
                    yaxis: {
                        show: false
                    },
                    toolbar:{
                        show: false
                    }
                };
                this.chart = new ApexCharts(this.$refs.canvas, options);
                this.chart.render();
        })
        },

        mouseMoveHandlerX(e) {
            if (!this.isDraggingX) return;
            this.containerX.scrollLeft -= e.movementX;
        },
        mouseDownHandlerX(e) {
            this.isDraggingX = true;
        },
        mouseUpHandlerX(e) {
            this.isDraggingX = false;
        },

        mouseMoveHandlerY(e) {
            if (!this.isDraggingY) return;
            this.containerY.scrollTop -= e.movementY;
        },
        mouseDownHandlerY(e) {
            this.isDraggingY = true;
        },
        mouseUpHandlerY(e) {
            this.isDraggingY = false;
        },
        wheelHandlerY(e) {
            e.preventDefault();
            this.containerY.scrollTop += e.deltaY;
        },

        checkWindowSize() {
            this.isSidebarOpen = window.innerWidth > 768;
        },

        toggleSidebar() {
            this.isSidebarOpen = !this.isSidebarOpen;
        },

        setTheme(theme) {
            this.theme = theme;
            localStorage.setItem('theme', theme);
        },
    },
    computed: {
        clientTransactions() {
            return this.accounts.flatMap(account => {
                return account.transactions.map(transaction => ({
                    ...transaction,
                    accountNumber: account.number
                }))
            });
        },
    }
}

const app = createApp(options);
app.mount('#app');