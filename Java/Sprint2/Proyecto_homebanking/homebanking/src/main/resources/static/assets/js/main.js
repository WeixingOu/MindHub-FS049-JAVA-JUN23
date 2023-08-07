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
        }
    },
    mounted() {
        this.containerX = this.$refs.containerX;
        this.containerY = this.$refs.containerY;

        this.checkWindowSize();
        window.addEventListener('resize', this.checkWindowSize);

        this.$nextTick(() => {
            const options = {
                series: [
                    {
                        name: "Amount",
                        data: [2500, 2050, 3000, 900, 4000, 1200, 1000,],
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
                    categories: [
                        "Jan",
                        "Feb",
                        "Mar",
                        "Apr",
                        "May",
                        "Jun",
                        "Jul",
                ],
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
    beforeUnmount() {
        window.removeEventListener('resize', this.checkWindowSize);
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get('/api/clients/01')
            .then(res => {
                this.client = res.data;

                this.client.accounts.forEach(account => {
                    account.creationDate = moment(account.creationDate).format('DD/MM/YY');
                    account.balance = account.balance.toLocaleString('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2});
                })
            })
            .catch(err => {
                console.error(err); 
            });
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
}

const app = createApp(options);
app.mount('#app');