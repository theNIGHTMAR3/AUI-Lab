import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayLibraries();
});

/**
 * Fetches all libraries and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayLibraries() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayLibraries(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/libraries', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display libraries.
 *
 * @param {{libraries: string[]}} libraries
 */
function displayLibraries(libraries) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    libraries.libraries.forEach(library => {
        tableBody.appendChild(createTableRow(library));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} library
 * @returns {HTMLTableRowElement}
 */
function createTableRow(library) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(library.name));
    tr.appendChild(createLinkCell('view', '../library_view/library_view.html?library=' + library.libraryId));
    tr.appendChild(createButtonCell('delete', () => deleteLibrary(library.libraryId)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } library to be deleted
 */
function deleteLibrary(library) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayLibraries();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/libraries/' + library, true);
    xhttp.send();
}
