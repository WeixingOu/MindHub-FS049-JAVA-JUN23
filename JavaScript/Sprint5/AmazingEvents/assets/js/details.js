const { createApp } = Vue

const options = {
    data() {
        return {
            event: {},
        }
    },
    created() {
        fetch('https://mindhub-xj03.onrender.com/api/amazing')
            .then(response => response.json())
            .then(data => {
                const events = data.events;
                const event = events.find(evt => evt._id == this.getEventId());
                this.event = event;
                console.log(event)
            })
            .catch(err => console.log(err))
    },
    methods: {
        getEventId() {
            const UrlParams = new URLSearchParams(window.location.search);
            return UrlParams.get('id');
        }
    }
}
const app = createApp(options);
app.mount('#app');