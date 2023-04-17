const apiURL = "http://localhost:8080/api/v1/photos";
const contentElement = document.getElementById('content');
const form = document.querySelector('form');
const searchInput = form.querySelector('#search-input');
const toggleForm = document.getElementById('toggle-form');

const apiURLmessages = "http://localhost:8080/api/v1/messages";
const formMessage = document.getElementById('message-form');


const getPhotoList = async () => {
    const response = await fetch(apiURL);
    return response;
}

const createPhotoItem = (item) => {
    let list = "";

    for (let i = 0; i < item.categories.length; i++) {
        const category = item.categories[i];
        list += `<li class="list-group-item text-primary">${category.title}</li>`
    }

    if (item.isVisible) {
        return `<div class="card col-6 my-3 mx-auto" style="width: 18rem;">
        <img src="${item.url}" class="card-img-top" alt="${item.title}">
        <div class="card-body">
          <h5 class="card-title">${item.title}</h5>
          <p class="card-text">${item.description}</p>
          <ul class="list-group list-group-flush">
            ${list}
          </ul>
        </div>
      </div>`
    }
};

const createPhotoList = (data) => {
    console.log(data);
    let list = `<div class="d-flex flex-wrap ">`;
    data.forEach(element => {
        list += createPhotoItem(element)
    });
    list += `</div>`;
    console.log(data);

    return list;
};

const postPhoto = async (jsonData) => {
    const fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: jsonData,
    };
    const response = await fetch(apiURL, fetchOptions);
    return response;
};

const filterPhoto = (event) => {
    event.preventDefault();

    const searchValue = searchInput.value.toLowerCase();

    fetch(apiURL)
        .then(response => response.json())
        .then(items => {
            const filteredItems = items.filter((item) => {
                const itemTitle = item.title.toLowerCase();
                return itemTitle.includes(searchValue);
            });

            console.log(filteredItems);
            contentElement.innerHTML = createPhotoList(filteredItems);


        })
        .catch(error => {
            console.error(error);
        });
}
form.addEventListener('submit', filterPhoto);


const laodData = async () => {
    const response = await getPhotoList();
    console.log(response);
    if (response.ok) {
        const data = await response.json();
        console.log(data);
        contentElement.innerHTML = createPhotoList(data);

    } else {
        console.log("ERROE");
    }
}


//Send Messages


formMessage.addEventListener('submit', (event) => {
    event.preventDefault();
    // sendMessage();

    const formData = new FormData(event.target);
    const formDataObj = Object.fromEntries(formData.entries());
    console.log(formDataObj);
    fetch(apiURLmessages, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formDataObj)
    }).then(response => {
        console.log(response);
        if (response.ok) {
            console.log('Dati inviati con successo!');
            formMessage.reset();

        } else {
            throw new Error('Si è verificato un errore durante l\'invio dei dati.');
        }
    })
        .catch(error => {
            console.error(error);
        });

});

/*CODE*/
laodData();