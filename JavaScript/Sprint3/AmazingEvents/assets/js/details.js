// Generate UrlParams Details
const UrlParams = new URLSearchParams(window.location.search);
const eventID = UrlParams.get('id');
console.log(eventID);

const eventFound = data.events.find( evt => evt._id === eventID);

const detailsContainer = document.getElementById('detailsContainer');

function insertDetails(event) {
    // Create HTML elements
    const rowDiv = document.createElement('div');
    rowDiv.classList.add('row', 'flex-column', 'flex-md-row', 'justify-content-md-center', 'rounded-4');
        // <div>
        const imgDiv = document.createElement('div');
        imgDiv.classList.add('col-md-5', 'p-0', 'details_img');
        imgDiv.style.height = '750px';

            const img = document.createElement('img');
            img.classList.add('img-fluid', 'h-100', 'object-fit-cover', 'rounded-start-4');
            img.src = event.image;
            img.alt = event.name;
        imgDiv.appendChild(img);
        // <div>
        const detailsDiv = document.createElement('div');
        detailsDiv.classList.add('col-md-7', 'd-flex', 'flex-column', 'px-md-3', 'px-lg-5', 'pt-4', 'pt-md-0', 'justify-content-center', 'bg-body-secondary', 'rounded-end-4', 'position-relative');

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
            pDate.className = "text-primary";
            pDate.textContent = event.date;

            const pDescriptionTitle = document.createElement("p");
            pDescriptionTitle.className = "u-text fs-3 m-0 text-body-emphasis";
            pDescriptionTitle.textContent = "Description: ";

            const pDescription = document.createElement("p");
            pDescription.className = "u-text m-0 mb-2 text-body";
            pDescription.textContent = event.description;

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
            pPrice.className = "fw-bolder text-primary fs-1";
            pPrice.textContent = `$ ${event.price},00`;

            detailsDiv.appendChild(heading);
            detailsDiv.appendChild(icon);
            detailsDiv.appendChild(pTitle);
            detailsDiv.appendChild(pDate);
            detailsDiv.appendChild(pDescriptionTitle);
            detailsDiv.appendChild(pDescription);
            detailsDiv.appendChild(pPlace);
            detailsDiv.appendChild(pCapacity);
            if (event.assistance !== undefined) {
                detailsDiv.appendChild(pAssistance);
            }
            detailsDiv.appendChild(pPrice);

    rowDiv.appendChild(imgDiv);
    rowDiv.appendChild(detailsDiv);

    detailsContainer.appendChild(rowDiv);
}
insertDetails(eventFound);