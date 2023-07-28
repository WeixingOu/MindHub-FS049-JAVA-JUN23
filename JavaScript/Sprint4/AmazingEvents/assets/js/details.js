import { insertDetails } from '../modules/functions.js';

let events = [];

fetch('https://mindhub-xj03.onrender.com/api/amazing')
    .then(response => response.json())
    .then(data => {
        events = data.events;

        const UrlParams = new URLSearchParams(window.location.search);
        const eventID = UrlParams.get('id');
        const eventFound = events.find(evt => evt._id == eventID);
        
        insertDetails(eventFound);
    })
    .catch(error => console.log(error))