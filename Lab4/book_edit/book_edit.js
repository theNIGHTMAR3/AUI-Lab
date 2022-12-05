import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayBook();
});

/**
 * Fetches currently logged library's books and updates edit form.
 */
function fetchAndDisplayBook() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/libraries/' + getParameterByName('library') + '/books/'
        + getParameterByName('book'), true);
    xhttp.send();
}

/**
 * Action event handled for updating book info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayBook();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/libraries/' + getParameterByName('library') + '/books/'
        + getParameterByName('book'), true);

    const request = {
        'title': document.getElementById('title').value,
        'author': document.getElementById('author').value,
        'year': parseInt(document.getElementById('year').value),
        'genre': parseFloat(document.getElementById('genre').value),

    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    alert("Book info updated successfully!")
}

