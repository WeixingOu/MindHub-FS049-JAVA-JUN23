document.addEventListener('DOMContentLoaded', function () {
    const cardContainer = document.querySelector('.row');

    function insertCard(events) {
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
                        <p class="fw-bloder text-primary fs-3 lh-1 pt-1 m-0">$${event.price}</p>
                        <a href="./assets/pages/details.html" class="btn btn-secondary">Details</a>
                    </div>
                    </div>
                </div>
                </div>
            `;
        }

        cardContainer.innerHTML = cardHTML;
    }
    insertCard(data.events);
});