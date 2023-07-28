import { insertCard, insertCheckbox, filterEvents } from '../modules/functions.js';

let events = [];

fetch('https://mindhub-xj03.onrender.com/api/amazing')
    .then(response => response.json())
    .then(data => {
        events = data.events.filter(event => event.date < data.currentDate);

        insertCard(events);
        insertCheckbox(events);

        const checkboxContainer = document.querySelector('.btn-group');
        checkboxContainer.addEventListener('change', () => filterEvents(events));

        const searchInput = document.querySelector('input[type="search"]');
        searchInput.addEventListener('input', () => filterEvents(events));
    })
    .catch(error => console.log(error))