import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayLibrary();
    fetchAndDisplayBooks();
});

/**
 * Fetches all libraries and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayBooks() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayStudents(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/libraries/' + getParameterByName('library') + '/books/'
        + getParameterByName('book'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display boks.
 *
 * @param {{books: {id: number, title:string}[]}} books
 */
function displayStudents(books) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    books.books.forEach(book => {
        tableBody.appendChild(createTableRow(book));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, title: string}} book
 * @returns {HTMLTableRowElement}
 */
function createTableRow(book) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(book.title));
    tr.appendChild(createLinkCell('edit', '../book_edit/book_edit.html?library='
        + getParameterByName('library') + '&book=' + book.id));
    tr.appendChild(createButtonCell('delete', () => deleteBook(book.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} book to be deleted
 */
function deleteBook(book) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayBooks();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/libraries/' + getParameterByName('libarry')
        + '/books/' + book, true);
    xhttp.send();
}


/**
 * Fetches single library and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayLibrary() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTeacher(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/libraries/' + getParameterByName('library'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display library.
 *
 * @param {{libraryId: number, name: string, address:string}} library
 */
function displayTeacher(library) {

    setTextNode('libraryId', library.libraryId);
    setTextNode('name', library.name);
    setTextNode('address', library.address);
    setTextNode('establishedYear', library.establishedYear);
    addAddingButton(library.libraryId)
}



function addAddingButton(libraryId) {
    let tableBody = document.getElementById('addingButton');
    clearElementChildren(tableBody);
    tableBody.appendChild(createTableRowAddingButton(libraryId));

}

/**
 * Creates single table row for entity.
 *
 * @param {number} libraryId
 * @returns {HTMLTableRowElement}
 */
function createTableRowAddingButton(libraryId) {
    let tr = document.createElement('tr');
    tr.appendChild(createLinkCell('add book to this library', '../book_add/book_add.html?library=' + libraryId));
    return tr;
}
