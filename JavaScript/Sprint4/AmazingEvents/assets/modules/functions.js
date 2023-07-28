// Insert Cards
const cardContainer = document.querySelector('.row');
function insertCard(events) {
    const isHomePage = window.location.pathname === '/index.html';
    let cardHTML = '';
    for (const event of events) {
        cardHTML += `
            <div class="col-12 col-sm-6 col-md-6 col-lg-3 mb-4">
            <div class="card">
                <img src="${event.image}" class="card-img-top" alt="imagen ${event.name}">
                <div class="card-body">
                <h5 class="card-title text-center">${event.name}</h5>
                <p class="card-text text-center mb-2">${event.description}</p>
                <div class="d-flex justify-content-around pt-2">
                    <p class="fw-bloder text-primary fs-3 lh-1 pt-1 m-0">$${event.price},00</p>
                    <a href="${isHomePage ? './assets/pages/details.html?id=' + event._id : './details.html?id=' + event._id}" class="btn btn-secondary">Details</a>
                </div>
                </div>
            </div>
            </div>
        `;
    }
    cardContainer.innerHTML = cardHTML;
}

// Insert Checkboxes
const checkboxContainer = document.querySelector('.btn-group');
function insertCheckbox(events) {
    const categories = [...new Set(events.map(event => event.category))];
    categories.forEach(category => {
        const checkboxInput = document.createElement('input');
        checkboxInput.className = 'btn-check';
        checkboxInput.type = 'checkbox';
        checkboxInput.value = category;
        checkboxInput.id = category.toLowerCase().replace(/\s+/g, '-');

        const checkboxLabel = document.createElement('label');
        checkboxLabel.classList.add('btn', 'btn-outline-secondary', 'me-2', 'rounded-0');
        checkboxLabel.htmlFor = category.toLowerCase().replace(/\s+/g, '-');
        checkboxLabel.textContent = category;

        checkboxContainer.appendChild(checkboxInput);
        checkboxContainer.appendChild(checkboxLabel);
    })
}

// Filter Events
function filterEvents(events) {
    const cardContainer = document.querySelector('.row');
    const checkboxContainer = document.querySelector('.btn-group');
    const searchInput = document.querySelector('input[type="search"]');

    const filteredEvents = events.filter(event => {
        const checkedCategories = Array.from(checkboxContainer.querySelectorAll('input[type="checkbox"]:checked')).map(checkbox => checkbox.value);
        const searchText = event.name.toLowerCase().includes(searchInput.value.toLowerCase());

        return (checkedCategories.length === 0 || checkedCategories.includes(event.category)) && searchText;
    })

    if (filteredEvents.length) {
        insertCard(filteredEvents);
    } else {
        while (cardContainer.firstChild) {
            cardContainer.removeChild(cardContainer.firstChild);
        }
        // Insert No Result Message
        const noResultsMessage = document.createElement('div');
        noResultsMessage.classList.add('no-result-message', 'container', 'mx-auto', 'text-center', 'mb-5');

        const noResultsImage = document.createElement('img');
        noResultsImage.src = 'https://i.pinimg.com/564x/d5/0e/05/d50e05271b9ee2e37471d4aa25bede29.jpg';
        noResultsImage.alt = 'No result found';
        noResultsImage.classList.add('img-fluid', 'w-25');
        noResultsMessage.appendChild(noResultsImage);

        const noResultsText = document.createElement('p');
        noResultsText.textContent = 'Sorry! No results found :(';
        noResultsText.classList.add('fs-5')
        noResultsMessage.appendChild(noResultsText);
        cardContainer.appendChild(noResultsMessage);
    }
}

// Generate UrlParams Details
function insertDetails(event) {
    // Create HTML elements
    const detailsContainer = document.getElementById('detailsContainer');

    const rowDiv = document.createElement('div');
    rowDiv.classList.add('row', 'flex-column', 'flex-md-row', 'justify-content-md-center', 'rounded-4');
    // <div>
    const imgDiv = document.createElement('div');
    imgDiv.classList.add('col-md-5', 'p-0', 'details_img');
    imgDiv.style.height = '600px';

    const img = document.createElement('img');
    img.classList.add('img-fluid', 'h-100', 'object-fit-cover', 'rounded-start-4');
    img.src = event.image;
    img.alt = event.name;
    imgDiv.appendChild(img);
    // <div>
    const detailsDiv = document.createElement('div');
    detailsDiv.classList.add('col-md-7', 'd-flex', 'flex-column', 'px-md-3', 'px-lg-5', 'pt-4', 'justify-content-center', 'bg-body-secondary', 'rounded-end-4', 'position-relative');

    const heading = document.createElement('h2');
    heading.classList.add('text-center', 'fw-bold', 'fs-1');
    heading.textContent = 'Event Details';

    const icon = document.createElement('img');
    icon.classList.add('u-image', 'img-fluid', 'my-3', 'mx-auto');
    icon.src = "../images/details_icon.png";
    icon.setAttribute("data-image-width", "262");
    icon.setAttribute("data-image-height", "170");

    const pTitle = document.createElement("p");
    pTitle.className = "u-text m-0 fs-2 text-body-emphasis";
    pTitle.textContent = event.name;

    const pDate = document.createElement("p");
    pDate.className = "text-primary m-0";
    pDate.textContent = event.date;

    const pDescriptionTitle = document.createElement("p");
    pDescriptionTitle.className = "u-text fs-3 m-0 text-body-emphasis";
    pDescriptionTitle.textContent = "Description: ";

    const pDescription = document.createElement("p");
    pDescription.className = "u-text m-0 mb-2 text-body";
    pDescription.textContent = event.description;

    const pDiv = document.createElement('div')
    pDiv.className = "d-flex justify-content-md-between flex-column flex-md-row"

    const pPlaceTitle = document.createElement("p");
    pPlaceTitle.className = "u-text text-body-emphasis";
    pPlaceTitle.textContent = "Place: ";

    const spanPlace = document.createElement("span");
    spanPlace.className = "text-body";
    spanPlace.textContent = event.place;

    const pPlace = document.createElement("p");
    pPlace.appendChild(pPlaceTitle);
    pPlace.appendChild(spanPlace);

    const pCapacityTitle = document.createElement("p");
    pCapacityTitle.className = "u-text text-body-emphasis";
    pCapacityTitle.textContent = "Capacity: ";

    const spanCapacity = document.createElement("span");
    spanCapacity.className = "text-body";
    spanCapacity.textContent = event.capacity;

    const pCapacity = document.createElement("p");
    pCapacity.appendChild(pCapacityTitle);
    pCapacity.appendChild(spanCapacity);

    const pAssistanceTitle = document.createElement("p");
    pAssistanceTitle.className = "u-text text-body-emphasis";
    pAssistanceTitle.textContent = "Assistance: ";

    const spanAssistance = document.createElement("span");
    spanAssistance.className = "text-body";
    spanAssistance.textContent = event.assistance;

    const pAssistance = document.createElement("p");
    pAssistance.appendChild(pAssistanceTitle);
    pAssistance.appendChild(spanAssistance);

    const pPrice = document.createElement("p");
    pPrice.className = "fw-bolder text-primary fs-1 text-center mb-4 mb-md-0 mt-4";
    pPrice.textContent = `---- $ ${event.price},00 ----`;

    const pEstimateTitle = document.createElement("p");
    pEstimateTitle.className = "u-text text-body-emphasis";
    pEstimateTitle.textContent = "Estimate: ";

    const spanEstimate = document.createElement("span");
    spanEstimate.className = "text-body";
    spanEstimate.textContent = event.estimate;

    const pEstimate = document.createElement("p");
    pEstimate.appendChild(pEstimateTitle);
    pEstimate.appendChild(spanEstimate);

    detailsDiv.appendChild(heading);
    detailsDiv.appendChild(icon);
    detailsDiv.appendChild(pTitle);
    detailsDiv.appendChild(pDate);
    detailsDiv.appendChild(pDescriptionTitle);
    detailsDiv.appendChild(pDescription);
    detailsDiv.appendChild(pDiv);
    if (event.assistance !== undefined) {
        pDiv.appendChild(pAssistance);
    } else {
        pDiv.appendChild(pEstimate);
    }
    detailsDiv.appendChild(pPrice);

    pDiv.appendChild(pPlace);
    pDiv.appendChild(pCapacity);



    rowDiv.appendChild(imgDiv);
    rowDiv.appendChild(detailsDiv);

    detailsContainer.appendChild(rowDiv);
}

export { insertCard, insertCheckbox, filterEvents, insertDetails };

function calculateAssistancePercentage(assistance, capacity) {
    return ((assistance / capacity) * 100);
}

function filterEventsStatsTable(events) {
    let highestAssistancePercentage = 0;
    let lowestAssistancePercentage = 100;
    let largestCapacity = 0;

    let eventHighestAssistancePercentage = {};
    let eventLowestAssistancePercentage = {};
    let eventLargestCapacity = {};

    events.forEach((event) => {
        const percentage = calculateAssistancePercentage(event.assistance, event.capacity);

        if (percentage > highestAssistancePercentage) {
            highestAssistancePercentage = percentage;
            eventHighestAssistancePercentage = event;
        }

        if (percentage < lowestAssistancePercentage) {
            lowestAssistancePercentage = percentage;
            eventLowestAssistancePercentage = event;
        }

        if (event.capacity > largestCapacity) {
            largestCapacity = event.capacity;
            eventLargestCapacity = event;
        }
    });

    const eventsStatisticsBody = document.getElementById("eventsStatisticsBody");
    const row = document.createElement("tr");

    const tdHighest = document.createElement("td");
    tdHighest.textContent = `${eventHighestAssistancePercentage.name} ${highestAssistancePercentage.toFixed(2)}%`;
    row.appendChild(tdHighest);

    const tdLowest = document.createElement("td");
    tdLowest.textContent = `${eventLowestAssistancePercentage.name} ${lowestAssistancePercentage.toFixed(2)}%`;
    row.appendChild(tdLowest);

    const tdLargestCapacity = document.createElement('td');
    tdLargestCapacity.textContent = `${eventLargestCapacity.name} ${eventLargestCapacity.capacity}`;
    row.appendChild(tdLargestCapacity);

    eventsStatisticsBody.appendChild(row);
}

function filterUpcomingEventsTable(events, currentDate) {
    const upcomingEvents = events.filter(event => event.date > currentDate);

    const eventsByCategories = upcomingEvents.reduce((acc, event) => {
        const category = event.category;
        if (!acc[category]) {
            acc[category] = {
                totalRevenue: 0,
                totalCapacity: 0,
                totalEstimate: 0,
            };
        }
        acc[category].totalRevenue += event.price * event.estimate;
        acc[category].totalEstimate += event.estimate;
        acc[category].totalCapacity += event.capacity;
        return acc;
    }, {});

    const upcomingEventsTableBody = document.getElementById("upcomingEventsTableBody");
    upcomingEventsTableBody.innerHTML = ""; // Clear table body before adding rows

    for (const category in eventsByCategories) {
        const { totalRevenue, totalEstimate, totalCapacity } = eventsByCategories[category];
        const percentageAssistance = calculateAssistancePercentage(totalEstimate, totalCapacity);

        const row = document.createElement("tr");

        const tdCategories = document.createElement('td');
        tdCategories.textContent = `${category}`;
        row.appendChild(tdCategories);

        const tdRevenues = document.createElement('td');
        tdRevenues.textContent = `${totalRevenue.toLocaleString('en-EN', { style: 'currency', currency: 'USD', minimumFractionDigits: 0 })}`;
        row.appendChild(tdRevenues);

        const tdpercentageAssistance = document.createElement('td');
        tdpercentageAssistance.textContent = `${percentageAssistance.toFixed(2)}%`;
        row.appendChild(tdpercentageAssistance);

        upcomingEventsTableBody.appendChild(row);
    }
}


function filterPastEventsTable(events, currentDate) {
    const pastEvents = events.filter(event => event.date < currentDate);
    // Group
    const eventsByCategories = {};
    pastEvents.forEach(event => {
        if (!eventsByCategories[event.category]) {
            eventsByCategories[event.category] = {
                totalRevenue: 0,
                totalCapacity: 0,
                totalAssistance: 0,
            };
        }
        eventsByCategories[event.category].totalRevenue += event.price * event.assistance;
        eventsByCategories[event.category].totalAssistance += event.assistance;
        eventsByCategories[event.category].totalCapacity += event.capacity;
    })

    for (const category in eventsByCategories) {
        const { totalRevenue, totalAssistance, totalCapacity } = eventsByCategories[category];
        const percentageAssistance = calculateAssistancePercentage(totalAssistance, totalCapacity);

        const pastEventsTableBody = document.getElementById("pastEventsTableBody");
        const row = document.createElement("tr");

        const tdCategories = document.createElement('td');
        tdCategories.textContent = `${category}`;
        row.appendChild(tdCategories);

        const tdRevenues = document.createElement('td');
        tdRevenues.textContent = `${totalRevenue.toLocaleString('en-EN', { style: 'currency', currency: 'USD', minimumFractionDigits: 0 })}`;
        row.appendChild(tdRevenues);

        const tdpercentageAssistance = document.createElement('td');
        tdpercentageAssistance.textContent = `${percentageAssistance.toFixed(2)}%`
        row.appendChild(tdpercentageAssistance);

        pastEventsTableBody.appendChild(row);
    }

}

export { calculateAssistancePercentage, filterEventsStatsTable, filterUpcomingEventsTable, filterPastEventsTable };