const { createApp } = Vue;

const options = {
    data() {
        return {
            highestAssistanceEvent: {},
            highestAssistancePercentage: Number,
            eventLowestAssistancePercentage: {},
            lowestAssistancePercentage: Number,
            eventLargestCapacity: {},
            eventLargestCapacity: {},
            upcomingEventsTable: {},
            pastEventsTable: {},
        }
    },
    created() {
        fetch('https://mindhub-xj03.onrender.com/api/amazing')
            .then(response => response.json())
            .then(data => {
                const events = data.events.sort((a, b) => {
                    const percentageA = this.calculateAssistancePercentage(a.assistance || a.estimate, a.capacity);
                    const percentageB = this.calculateAssistancePercentage(b.assistance || b.estimate, b.capacity);
                    return percentageB - percentageA;
                });

                let lowestAssistancePercentage = 100;
                events.forEach((event) => {
                    const percentage = this.calculateAssistancePercentage(event.assistance || event.estimate, event.capacity);
                    if (!isNaN(percentage) && percentage < lowestAssistancePercentage) {
                        this.lowestAssistancePercentage = percentage.toFixed(2);
                        this.eventLowestAssistancePercentage = event;
                    }
                });

                const highestAssistanceEvent = events[0];
                const highestAssistancePercentage = this.calculateAssistancePercentage(highestAssistanceEvent.assistance || highestAssistanceEvent.estimate, highestAssistanceEvent.capacity);
                this.highestAssistanceEvent = highestAssistanceEvent;
                this.highestAssistancePercentage = highestAssistancePercentage;

                const eventsByCapacity = data.events.sort((a, b) => {
                    return b.capacity - a.capacity;
                })
                const eventLargestCapacity = eventsByCapacity[0];
                this.eventLargestCapacity = eventLargestCapacity;

                const upcomingEvents = data.events.filter(event => event.date > data.currentDate);
                this.upcomingEventsTable = upcomingEvents.reduce((acc, event) => {
                    const category = event.category;
                    if (!acc[category]) {
                        acc[category] = {
                            totalRevenue: 0,
                            totalCapacity: 0,
                            totalEstimate: 0,
                        };
                    }
                    acc[category].totalRevenue += event.price * event.estimate;
                    acc[category].totalRevenue = acc[category].totalRevenue
                    acc[category].totalEstimate += event.estimate;
                    acc[category].totalCapacity += event.capacity;
                    return acc;
                }, {})

                const pastEvents = data.events.filter(event => event.date < data.currentDate);
                this.pastEventsTable = pastEvents.reduce((acc, event) => {
                    const category = event.category;
                    if (!acc[category]) {
                        acc[category] = {
                            totalRevenue: 0,
                            totalCapacity: 0,
                            totalEstimate: 0,
                        };
                    }
                    acc[category].totalRevenue += event.price * event.assistance;
                    acc[category].totalRevenue = acc[category].totalRevenue
                    acc[category].totalEstimate += event.assistance;
                    acc[category].totalCapacity += event.capacity;
                    return acc;
                }, {})
            })
            .catch(err => console.log(err))
    },
    methods: {
        calculateAssistancePercentage(assistance, capacity) {
            return ((assistance / capacity) * 100);
        }
    },
    filters: {
        currencyFormat(value) {
            return value;
        },
    }
};
const app = createApp(options);
app.mount('#app');
