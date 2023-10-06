const { createApp, ref } = Vue

const options = {
    data() {
        return {
            isDraggingX: false,
            containerX: ref(null),
            isDraggingY: false,
            containerY: ref(null),

            isMouseUp: false,

            isSidebarOpen: false,

            theme: localStorage.getItem('theme') || 'light',

            chart: null,

            client: {},
            accounts: [],
            loans: [],

            cards: [],
            creditCards: [],
            debitCards: [],

            isCreatingAccount: false,
            isAccountCreated: false,
            closeModal: false,

            cardDebitCreated: {
                cardType: 'DEBIT',
                cardColor: '',
            },
            cardCreditCreated: {
                cardType: 'CREDIT',
                cardColor: '',
            },

            destinationType: 'owner',

            newTransaction: {
                amount: 0.0,
                accountNumberFrom: '',
                accountNumberTo: '',
                description: '',
            },

            newLoan: {
                loanId: '',
                amount: 0.0,
                payments: Number,
                accountNumberDest: '',
            },

            loansData: [],

            selectedLoan: null,
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
        this.loanData();
    },
    methods: {
        loadData() {
            axios.get('/api/clients/current')
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
                        account.balance = account.balance.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });

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

        loanData() {
            axios.get('/api/loans')
                .then(res => {
                    this.loansData = res.data;
                })
                .catch(err => {
                    console.error(err);
                })
        },

        navigateToAccount(accountId) {
            if (this.isMouseUp) {
                window.location.href = `/assets/pages/account.html?id=${accountId}#`;

            }
        },

        logOut() {
            axios.post('/api/logout')
                .then(response => {
                    window.location.href = "/index.html";
                })
                .catch(err => {
                    console.error(err);
                });
        },

        createAccount() {
            this.isCreatingAccountO = true;
            this.isCreatingAccountC = true;
            this.closeModal = true;

            axios.post('/api/clients/current/accounts')
                .then(response => {
                    if (response.status === 201) {
                        setTimeout(() => {
                            this.isAccountCreated = true;

                            setTimeout(() => {
                                location.reload();
                            }, 500)
                        }, 1000);
                    }
                })
                .catch(err => {
                    console.error(err);
                });
        },

        createDebitCard() {
            this.isCreatingAccountO = true;
            this.isCreatingAccountC = true;
            this.closeModal = true;

            axios.post('/api/clients/current/cards', this.cardDebitCreated, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    if (response.status === 201) {
                        setTimeout(() => {
                            this.isAccountCreated = true;

                            setTimeout(() => {
                                location.reload();
                            }, 500);
                        }, 1000);
                    }
                })
                .catch(err => {
                    console.error(err);
                });
        },

        createCreditCard() {
            this.isCreatingAccountO = true;
            this.isCreatingAccountC = true;
            this.closeModal = true;

            axios.post('/api/clients/current/cards', this.cardCreditCreated, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    if (response.status === 201) {
                        setTimeout(() => {
                            this.isAccountCreated = true;

                            setTimeout(() => {
                                location.reload();
                            }, 500);
                        }, 1000);
                    }
                })
                .catch(err => {
                    console.error(err);
                });
        },

        transfer() {
            this.isCreatingAccountO = true;
            this.isCreatingAccountC = true;
            this.closeModal = true;

            axios.post('/api/transactions', this.newTransaction, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    if (response.status === 201) {
                        setTimeout(() => {
                            this.isAccountCreated = true;

                            setTimeout(() => {
                                location.reload();
                            }, 500);
                        }, 1000);
                    }
                })
                .catch(err => {
                    console.error(err);
                });
        },

        borrow() {
            this.isCreatingAccountO = true;
            this.isCreatingAccountC = true;
            this.closeModal = true;

            console.log(this.newLoan);

            axios.post('/api/loans', this.newLoan, { headers: { 'content-type': 'application/json' } })
                .then(response => {
                    if (response.status === 201) {
                        setTimeout(() => {
                            this.isAccountCreated = true;

                            setTimeout(() => {
                                location.reload();
                            }, 500);
                        }, 1000);
                    }
                })
                .catch(err => {
                    console.error(err);
                });
        },

        renderChart() {
            this.$nextTick(() => {
                const options = {
                    series: [
                        {
                            name: "Amount",
                            data: this.loans.map(l => l.amount),
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
                        categories: this.loans.map(l => l.name),
                    },
                    grid: {
                        show: false,
                    },
                    yaxis: {
                        show: false
                    },
                    toolbar: {
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

            setTimeout(() => {
                this.isMouseUp = true;
            }, 500)
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
        availableDestAccounts() {
            return this.accounts.filter(account => account.number !== this.newTransaction.accountNumberFrom);
        },
    },
    watch: {
        'newLoan.loanId': function (newVal, oldVal) {
            let loan = this.loansData.find(loan => loan.id == newVal);

            if (loan && loan.payments) {
                this.selectedLoan = {
                    ...loan,
                    payments: [...loan.payments].sort((a, b) => a - b)
                };
            } else {
                this.selectedLoan = null;
            }
        }
    }
}

const app = createApp(options);
app.mount('#app');