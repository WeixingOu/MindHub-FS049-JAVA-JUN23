document.addEventListener('DOMContentLoaded', function () {
    // Insert Cards
    const cardContainer = document.querySelector('.row');
    function insertCard(events) {
        let cardHTML = '';
        for (const event of events) {
            if (event.date > data.currentDate) {
                cardHTML += `
                    <div class="col-12 col-sm-6 col-md-6 col-lg-3 mb-4">
                    <div class="card">
                        <img src="${event.image}" class="card-img-top" alt="imagen ${event.name}">
                        <div class="card-body">
                        <h5 class="card-title text-center">${event.name}</h5>
                        <p class="card-text text-center mb-2">${event.description}</p>
                        <div class="d-flex justify-content-around pt-2">
                            <p class="fw-bloder text-primary fs-3 lh-1 pt-1 m-0">$${event.price},00</p>
                            <a href="./details.html?id=${event._id}" class="btn btn-secondary">Details</a>
                        </div>
                        </div>
                    </div>
                    </div>
                `;
            }
        }
        cardContainer.innerHTML = cardHTML;
    }
    insertCard(data.events);

    // Insert Checkboxes
    const checkboxContainer = document.querySelector('.btn-group');
    function insertCheckbox() {
        const categories = [...new Set(data.events.map(event => event.category))];
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
    insertCheckbox();

    // Filter Events
    checkboxContainer.addEventListener('change', filterEvents);
    const searchInput = document.querySelector('input[type="search"]');;
    searchInput.addEventListener('input', filterEvents);

    function filterEvents() {
        const filteredEvents = data.events.filter(event => {
            const checkedCategories = Array.from(checkboxContainer.querySelectorAll('input[type="checkbox"]:checked')).map(checkbox => checkbox.value);
            const searchText = event.name.toLowerCase().includes(searchInput.value.toLowerCase());

            return (checkedCategories.length === 0 || checkedCategories.includes(event.category)) && searchText && event.date > data.currentDate
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
});