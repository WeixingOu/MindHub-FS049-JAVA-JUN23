import {filterEventsStatsTable, filterUpcomingEventsTable, filterPastEventsTable} from '../modules/functions.js';

fetch("https://mindhub-xj03.onrender.com/api/amazing")
    .then((response) => response.json())
    .then((data) => {
        filterEventsStatsTable(data.events);
        filterUpcomingEventsTable(data.events, data.currentDate);
        filterPastEventsTable(data.events, data.currentDate);
    });
